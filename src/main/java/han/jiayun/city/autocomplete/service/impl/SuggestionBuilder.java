package han.jiayun.city.autocomplete.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.CoordinateScoringService;
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
	
	@Value("${name.weight:0.25}")
	private double nameWeight;
	
	@Value("${coordinate.weight:0.75}")
	private double coordinateWeight;
		
	@Autowired
	private SuggestionNamingService suggestionNamingService;

	@Autowired
	private NameOnlyScoringService nameOnlyScoringService;

	@Autowired
	private CoordinateScoringService coordinateScoringService;

	@Override
	public Suggestion toSuggestion(GeoName geoName, String queryTerm, Optional<Coordinate> queryCoordinate) {

		String name = suggestionNamingService.toSuggestionName(geoName);
		
		double score;
		double scoreByName = nameOnlyScoringService.score(queryTerm, geoName.getCity());
		
		if(queryCoordinate.isEmpty()) {
			score = scoreByName;
		}else {
			double scoreByCoordinate = coordinateScoringService.score(geoName.getCoordinate(), queryCoordinate.get());
			score = scoreByName * nameWeight + scoreByCoordinate * coordinateWeight;
		}

		return new Suggestion(geoName.getLatitude(), geoName.getLongitude(), name, score);
	}

}
