package han.jiayun.city.autocomplete.service;

import java.util.Optional;

import han.jiayun.city.autocomplete.exceptions.InvalidLongitudeException;

/**
 * Responsible for validating the longitude query parameter
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface LongitudeValidatingService {	
	/**
	 * 
	 * @param longitude The longitude to be validated
	 * @throws InvalidLongitudeException if the longitude is present but out of the
	 *                                  range of -180 and 180, both inclusive
	 */
	void validate(Optional<Double> longitude) throws InvalidLongitudeException;
}
