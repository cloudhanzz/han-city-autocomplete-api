package han.jiayun.city.autocomplete.service;

import han.jiayun.city.autocomplete.model.GeoName;

/**
 * Responsible for building the name of a Suggestion out of a GeoName
 * 
 * @author Jiayun Han
 *
 */
public interface SuggestionNamingService {

	String toSuggestionName(GeoName geoName);

}
