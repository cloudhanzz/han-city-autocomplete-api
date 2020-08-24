package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.service.CoordinateScoringService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test scoring based on geographic distance")
public class CoordinateScoringServiceTest {
	
	@Autowired
	private CoordinateScoringService coordinateScoringService;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/geo-distance-sores.csv")
	@DisplayName("Test distances can be coverted to score correctly")
	public void test(String strDistance, String strScore) {
		
		double distance = Double.parseDouble(strDistance);
		double expectedScore = Double.parseDouble(strScore);
		double score = coordinateScoringService.toScore(distance);
		assertEquals(expectedScore, score);
		
	}

}
