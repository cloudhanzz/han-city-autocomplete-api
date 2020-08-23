package han.jiayun.city.autocomplete.service;

import java.util.Optional;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.model.Suggestion;

/**
 * Provide service for building a Suggestion
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface SuggestionBuildingService {

	/**
	 * Transform a GeoName into a Suggestion
	 * 
	 * @param geoName       The GeoName to be transformed into a Suggestion
	 * @param queryTerm     used to calculate similarity score
	 * @param queryCoordinate The optional coordinate that enhance the suggestion
	 * @return The Suggestion transformed from the given GeoName
	 */
	Suggestion toSuggestion(GeoName geoName, String queryTerm, Optional<Coordinate> queryCoordinate);
}
