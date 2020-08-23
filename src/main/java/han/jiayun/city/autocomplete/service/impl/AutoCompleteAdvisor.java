package han.jiayun.city.autocomplete.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.LatitudeValidatingService;
import han.jiayun.city.autocomplete.service.AutoCompleteService;
import han.jiayun.city.autocomplete.service.LongitudeValidatingService;
import han.jiayun.city.autocomplete.service.QueryTermOnlySearchService;
import han.jiayun.city.autocomplete.service.QueryTermValidingService;
import han.jiayun.city.autocomplete.stub.SuggestionStubber;

@Service
public class AutoCompleteAdvisor implements AutoCompleteService {	

	@Autowired
	private QueryTermValidingService queryTermValidingService;
	
	@Autowired
	private LatitudeValidatingService latitudeValidatingService;
	
	@Autowired
	private LongitudeValidatingService longitudeValidatingService;

	@Autowired
	private QueryTermOnlySearchService queryTermOnlySearchService;

	@Override
	public List<Suggestion> searchLocations(String queryTerm, int limit, Optional<Double> latitude,
			Optional<Double> longitude) {
		
		queryTermValidingService.validate(queryTerm);
		latitudeValidatingService.validate(latitude);
		longitudeValidatingService.validate(longitude);

		List<Suggestion> suggestions;
		
		if (latitude.isEmpty() && longitude.isEmpty()) {
			suggestions = queryTermOnlySearchService.searchLocations(queryTerm, limit);
		} else {
			suggestions = SuggestionStubber.cannedSuggestions();
		}
		
		Collections.sort(suggestions);
		
		return suggestions;
	}

}
