package han.jiayun.city.autocomplete.service;

import han.jiayun.city.autocomplete.model.CodeWithDistance;
import han.jiayun.city.autocomplete.model.Coordinate;

/**
 * 
 * @author Jiayun Han
 *
 */
public interface DistanceEstimateService {

	/**
	 * Calculate the estimated distance to an coordinate from the area represented
	 * by the admin1Code
	 * 
	 * @param coordinate The reference coordinate to calculate the distance
	 * @param admin1Code Representing an Area
	 * @return The admin1Code plus the estimated distance
	 */
	CodeWithDistance estimateDistance(Coordinate coordinate, String admin1Code);

}
