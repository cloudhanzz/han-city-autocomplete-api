package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.service.DistanceEstimateService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test the distance estimate service")
public class DistanceEstimateServiceTest {

	@Autowired
	private DistanceEstimateService distanceEstimateService;	

	@Test
	@DisplayName("An estimated distance should be returned")
	public void test_distance_calculated() {
		
		Coordinate coordinate = new Coordinate(43.08339, -81.29975);
		String admin1Code = "08";
		
		var result = distanceEstimateService.estimateDistance(coordinate, admin1Code);
		assertTrue(result.getDistance() > 0.0);
	}
}
