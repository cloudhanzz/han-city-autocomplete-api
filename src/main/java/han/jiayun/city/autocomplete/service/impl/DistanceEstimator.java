package han.jiayun.city.autocomplete.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.CodeWithDistance;
import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.service.DistanceEstimateService;
import han.jiayun.city.autocomplete.service.GeoNameService;
import han.jiayun.city.autocomplete.util.GeoDistanceCalculator;

/**
 * The implementation is very crude, just using a random GeoName of the provided
 * admin1Code. In real situation, a more sophisticated method should be used but
 * that is out of the scope here.
 * 
 * @author Jiayun Han
 *
 */
@Service
public class DistanceEstimator implements DistanceEstimateService {

	@Autowired
	private GeoNameService geoNameService;

	@Override
	public CodeWithDistance estimateDistance(Coordinate coordinate, String admin1Code) {

		List<GeoName> geoNames = geoNameService.geoNamesByAdmin1Code(admin1Code);

		Random random = new Random();
		int index = random.nextInt(geoNames.size());

		GeoName geoName = geoNames.get(index);
		double distance = GeoDistanceCalculator.haversineDistance(coordinate, geoName.getCoordinate());

		return new CodeWithDistance(admin1Code, distance);
	}
}
