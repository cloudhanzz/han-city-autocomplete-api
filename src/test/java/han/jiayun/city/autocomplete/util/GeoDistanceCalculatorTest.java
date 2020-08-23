package han.jiayun.city.autocomplete.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import han.jiayun.city.autocomplete.model.GeoName;

/**
 * I rely on this test class to make me less uncomfortable to use the
 * implementation of Haversine algorithm that was implemented by me.
 * 
 * @author Jiayun Han
 *
 */
@DisplayName("Test Haversine algorithm implementation")
public class GeoDistanceCalculatorTest {

	@Test
	@DisplayName("Test Montreal is closer to Quebec City than Miami to Quebec City")
	public void montreal_closer_to_quebec_city_than_miami() {
		
		GeoName quebecCity = new GeoName("Clarion (Quebec City)	46.7636	-71.3015	CA	10");
		GeoName montreal = new GeoName("Montréal-Nord	45.60008	-73.63251	CA	10");
		GeoName washingDC = new GeoName("Washington District of Columbia Houses	38.89178	-77.00831	US	DC");

		double montreal_to_quebec = GeoDistanceCalculator.haversineDistance(montreal, quebecCity);
		double montreal_to_DC = GeoDistanceCalculator.haversineDistance(montreal, washingDC);

		int result = Double.compare(montreal_to_quebec, montreal_to_DC);

		assertTrue(result < 0);
	}
}
