package han.jiayun.city.autocomplete.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.GeoNameService;
import han.jiayun.city.autocomplete.service.QueryTermOnlySearchService;
import han.jiayun.city.autocomplete.service.SuggestionBuildingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class QueryTermOnlySearcher implements QueryTermOnlySearchService {

	@Autowired
	private GeoNameService geoNameService;
	
	@Autowired
	private SuggestionBuildingService suggestionBuildingService;

	@Override
	public List<Suggestion> searchLocations(String queryTerm, int limit) {

		int counter = 0;
		List<Suggestion> suggestions = new ArrayList<>();
		
		for (var geoName : geoNameService.unmodifiableGeoNames()) {
			
			if(geoName.cityStartsWith(queryTerm)) {
				
				counter++;
				
				Suggestion suggestion = suggestionBuildingService.toSuggestion(geoName, queryTerm, Optional.empty());
				suggestions.add(suggestion);
			}
			
			if (counter == limit) {
				break;
			}
		}
		return suggestions;
	}

}
