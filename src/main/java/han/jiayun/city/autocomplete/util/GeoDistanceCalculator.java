package han.jiayun.city.autocomplete.util;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;

/**
 * <p>
 * This class uses Haversine distance algorithm to measure the distance between
 * 2 coordinates
 * 
 * <p>
 * The implementation is not by me.
 * 
 * <p>
 * I only did a little optimization by extracted the constants
 * <code>RADIANS_FACTOR = Math.PI / 180</code> so that it won't be calculated
 * again and again, hoping this can save some computations.
 * 
 * <p>
 * Disclaimer: I didn't verify the implementation in details. Instead, I wrote
 * some tests to have some rough gauge.
 * 
 * <p>
 * For more details of the this famous algorithm, see the provided link to a
 * Wikipedia page.
 * 
 * @author Not Me
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Haversine_formula">Wikipedia:
 *      Haversine formula</a>
 * 
 */
public class GeoDistanceCalculator {

	private final static double RADIANS_FACTOR = Math.PI / 180;
	private final static double EARTH_RADIUS_IN_KM = 6372.8;

	public static double haversineDistance(GeoName geoA, GeoName geoB) {
		Coordinate a = new Coordinate(geoA.getLatitude(), geoA.getLongitude());
		Coordinate b = new Coordinate(geoB.getLatitude(), geoB.getLongitude());
		return haversineDistance(a, b);
	}

	/**
	 * Returns the distance between two coordinates
	 * 
	 * @param geoA The first coordinate
	 * @param geoB The second coordinate
	 * @return The Haversine distance between these two points
	 */
	public static double haversineDistance(Coordinate geoA, Coordinate geoB) {

		double latitudeDelta = toRadian(geoB.getLatitude() - geoA.getLatitude());
		double longitudeDelta = toRadian(geoB.getLongitude() - geoA.getLongitude());

		double latitudeRadianA = toRadian(geoA.getLatitude());
		double latitudeRadianB = toRadian(geoB.getLatitude());

		double d = Math.pow(Math.sin(latitudeDelta / 2), 2)
				+ Math.pow(Math.sin(longitudeDelta / 2), 2) * Math.cos(latitudeRadianA) * Math.cos(latitudeRadianB);

		return 2 * Math.atan2(Math.sqrt(d), Math.sqrt(1 - d)) * EARTH_RADIUS_IN_KM;
	}

	/**
	 * Returns the radians value of a degree value
	 * 
	 * @param degree
	 * @return the Radians representation
	 */
	private static double toRadian(double degree) {
		return degree * RADIANS_FACTOR;
	}
}
