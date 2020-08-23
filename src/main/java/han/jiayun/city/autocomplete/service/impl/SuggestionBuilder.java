package han.jiayun.city.autocomplete.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.NameOnlyScoringService;
import han.jiayun.city.autocomplete.service.SuggestionBuildingService;
import han.jiayun.city.autocomplete.service.SuggestionNamingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class SuggestionBuilder implements SuggestionBuildingService {
		
	@Autowired
	private SuggestionNamingService suggestionNamingService;

	@Autowired
	private NameOnlyScoringService nameOnlyScoringService;

	@Override
	public Suggestion toSuggestion(GeoName g, String queryTerm) {

		String name = suggestionNamingService.toSuggestionName(g);		
		double score = nameOnlyScoringService.score(queryTerm, g.getCity());

		return new Suggestion(g.getLatitude(), g.getLongitude(), name, score);
	}

}
