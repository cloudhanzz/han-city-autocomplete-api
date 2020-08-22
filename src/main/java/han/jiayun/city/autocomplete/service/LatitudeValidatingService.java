package han.jiayun.city.autocomplete.service;

import java.util.Optional;

import han.jiayun.city.autocomplete.exceptions.InvalidLatitudeException;

/**
 * Responsible for validating the latitude query parameter
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface LatitudeValidatingService {

	/**
	 * 
	 * @param latitude The latitude to be validated
	 * @throws InvalidLatitudeException if the latitude is present but out of the
	 *                                  range of -90 and 90, both inclusive
	 */
	void validate(Optional<Double> latitude) throws InvalidLatitudeException;

}
