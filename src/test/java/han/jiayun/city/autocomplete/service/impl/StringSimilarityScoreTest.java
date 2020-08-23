package han.jiayun.city.autocomplete.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.service.NameOnlyScoringService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test string similarity score implementation")
public class StringSimilarityScoreTest {
	
	@Autowired
	private NameOnlyScoringService nameOnlyScoringService;
	
	@Test
	@DisplayName("Lond is more similar to London than Lad to London")
	public void Lond_closer_to_London_than_Lad_to_London() {
		
		String london = "London";
		String still_london = "London";
		
		String lond = "Lond";
		String lad = "Lad";
		
		double londonScore = nameOnlyScoringService.score(still_london, london);
		double londScore = nameOnlyScoringService.score(lond, london);
		double ladScore = nameOnlyScoringService.score(lad, london);
		
		System.out.println("London to London = " + londonScore);
		System.out.println("Lond to London = " + londScore);
		System.out.println("Lad to London = " + ladScore);
	}

}
