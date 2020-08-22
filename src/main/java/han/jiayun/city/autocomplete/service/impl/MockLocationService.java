package han.jiayun.city.autocomplete.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.LocationService;
import han.jiayun.city.autocomplete.stub.SuggestionStubber;

@Service
public class MockLocationService implements LocationService {

	@Override
	public List<Suggestion> searchLocations(String queryString, Optional<Double> latitude,
			Optional<Double> longitude) {
		
		List<Suggestion> suggestions = SuggestionStubber.cannedSuggestions();
		Collections.sort(suggestions);
		return suggestions;
	}

}
