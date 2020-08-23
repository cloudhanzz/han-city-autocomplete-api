package han.jiayun.city.autocomplete.service;

import java.util.List;
import java.util.Optional;

import han.jiayun.city.autocomplete.model.Suggestion;

/**
 * Service that suggests the locations that are relevant to the search criteria.
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface AutoCompleteService {

	/**
	 * Returns a list of suggested locations based on the query string and the
	 * optional latitude and longitude
	 * 
	 * @param queryTerm The required query term. For the sake of simplicity, this
	 *                    project does not allow it to start with a punctuation or
	 *                    contain white spaces only
	 * @param latitude    It can be absent; but once present it must be in the range
	 *                    of -90 and 90, both inclusive, and be in the format of
	 *                    signed degrees.
	 * @param longitude   It can be absent; but once present it must be in the range
	 *                    of -180 and 180, both inclusive, and be in the format of
	 *                    signed degrees.
	 * @return
	 */
	List<Suggestion> searchLocations(String queryTerm, Optional<Double> latitude, Optional<Double> longitude);
}
