package han.jiayun.city.autocomplete.service;

import java.util.List;

import han.jiayun.city.autocomplete.model.Suggestion;

/**
 * Provides searching service when the request is assisted with the provided
 * latitude and longitude
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface SearchWithCoordinateService {
	/**
	 * Search locations based on the query term. The number of locations is limited
	 * by <code>limit</code>
	 * 
	 * @param queryTerm The query string to base on
	 * @param limit     The maximal number of suggestions to be returned
	 * @param latitude  The provided latitude
	 * @param longitude The provided longitude
	 * @return The <code>limit</code> number of suggestions
	 */
	List<Suggestion> searchLocations(String queryTerm, int limit, double latitude, double longitude);
}
