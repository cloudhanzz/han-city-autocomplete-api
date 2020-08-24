package han.jiayun.city.autocomplete.service;

import han.jiayun.city.autocomplete.model.Coordinate;

/**
 * 
 * Responsble for give a score based on geographic distances
 * 
 * @author Jiayun Han
 *
 */
public interface CoordinateScoringService {

	/**
	 * Calculate the score based on the distance between the two coordinates
	 * 
	 * @param coordinateA
	 * @param coordinateB
	 * @return
	 */
	double score(Coordinate coordinateA, Coordinate coordinateB);

	/**
	 * Returns the score converted from a geographic distance
	 * 
	 * @param distance
	 * @return the score converted from a geographic distance
	 */
	double toScore(double distance);

}
