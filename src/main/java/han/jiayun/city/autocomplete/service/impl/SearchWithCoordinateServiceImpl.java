package han.jiayun.city.autocomplete.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.Admin1CodeService;
import han.jiayun.city.autocomplete.service.EvolutionService;
import han.jiayun.city.autocomplete.service.GeoNameService;
import han.jiayun.city.autocomplete.service.OptimalPathService;
import han.jiayun.city.autocomplete.service.SearchWithCoordinateService;
import han.jiayun.city.autocomplete.service.SuggestionBuildingService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
@Slf4j
public class SearchWithCoordinateServiceImpl implements SearchWithCoordinateService {

	@Autowired
	private GeoNameService geoNameService;

	@Autowired
	private OptimalPathService optimalPathService;

	@Autowired
	private SuggestionBuildingService suggestionBuildingService;

	@Autowired
	private EvolutionService evolutionService;
	
	@Autowired
	private Admin1CodeService admin1CodeService;

	@Override
	public List<Suggestion> searchLocations(String queryTerm, int limit, double latitude, double longitude) {

		List<Suggestion> suggestions = new ArrayList<>();

		Coordinate cordinate = new Coordinate(latitude, longitude);

		String bestAdmin1Code = geoNameService.getAdmin1ByCoordinate(cordinate);
		List<String> admin1Codes = optimalPathService.getAdmin1CodePath(bestAdmin1Code);
		admin1Codes.add(0, bestAdmin1Code);

		Optional<Coordinate> optCoordinate = Optional.of(cordinate);
		for (String admin1Code : admin1Codes) {
			
			String admin1Name = admin1CodeService.getAdmin1NameByCode(bestAdmin1Code);
			log.info("Search {} area ...", admin1Name);
			
			suggestByAdmin1Code(queryTerm, limit, suggestions, admin1Code, optCoordinate);
		}

		return suggestions;
	}

	private void suggestByAdmin1Code(String queryTerm, int limit, List<Suggestion> suggestions,
			String admin1Code, Optional<Coordinate> optCordinate) {
		List<GeoName> geoNames = geoNameService.geoNamesByAdmin1Code(admin1Code);

		for (GeoName geoName : geoNames) {
			if (geoName.cityStartsWith(queryTerm)) {
				Suggestion suggestion = suggestionBuildingService.toSuggestion(geoName, queryTerm, optCordinate);
				evolutionService.evolve(limit, suggestions, suggestion);
			}
		}
	}

}
