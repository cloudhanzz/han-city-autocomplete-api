package han.jiayun.city.autocomplete.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.CodeWithDistance;
import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.service.Admin1CodeService;
import han.jiayun.city.autocomplete.service.DistanceEstimateService;
import han.jiayun.city.autocomplete.service.GeoNameService;
import han.jiayun.city.autocomplete.service.OptimalPathService;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class OptimalPathFinder implements OptimalPathService {

	private static Map<String, List<CodeWithDistance>> pathMap;

	@Autowired
	private OptimalPathFinder(Admin1CodeService admin1CodeService, DistanceEstimateService distanceEstimateService, GeoNameService geoNameService) {

		Random random = new Random();

		pathMap = new HashMap<>();

		List<String> codes = admin1CodeService.allAdmin1Codes();

		for (String code : codes) {

			List<GeoName> geoNames = geoNameService.geoNamesByAdmin1Code(code);
			int index = random.nextInt(geoNames.size());

			GeoName geoName = geoNames.get(index);
			Coordinate coordinate = geoName.getCoordinate();

			List<CodeWithDistance> path = calculatePath(distanceEstimateService, code, coordinate, codes);
			pathMap.put(code, path);
		}

	}

	/**
	 * Build the heuristically optimal path of an amind1Code
	 * 
	 * @param targetAdmin1Code
	 * @param targetCoordinate
	 * @param pathAdmin1Codes
	 * @return The heuristically optimal path of an amind1Code
	 */
	private List<CodeWithDistance> calculatePath(DistanceEstimateService distanceEstimateService, String targetAdmin1Code, Coordinate targetCoordinate,
			List<String> pathAdmin1Codes) {

		List<CodeWithDistance> list = new ArrayList<>();

		for (String code : pathAdmin1Codes) {
			if (targetAdmin1Code.equals(code)) {
				continue;
			}

			CodeWithDistance cd = distanceEstimateService.estimateDistance(targetCoordinate, code);
			list.add(cd);
		}

		Collections.sort(list);

		return list;
	}

	@Override
	public List<String> getAdmin1CodePath(String admin1Code) {

		List<CodeWithDistance> list = pathMap.get(admin1Code);

		if (list != null) {
			return list.stream().map(CodeWithDistance::getAdmin1Code).collect(toList());
		}

		return null;
	}
}
