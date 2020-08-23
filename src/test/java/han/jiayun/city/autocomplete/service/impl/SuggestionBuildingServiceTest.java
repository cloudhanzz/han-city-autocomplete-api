package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.SuggestionBuildingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test Suggestion Building Service")
public class SuggestionBuildingServiceTest {

	@Autowired
	private SuggestionBuildingService suggestionBuildingService;

	@ParameterizedTest
	@CsvFileSource(resources = "/geolines_starting_with_London.csv")
	@DisplayName("Test a GeoName object can be built into a Suggestion with similarity score and suggestion name")
	public void can_be_built(String geoLine) {
		
		GeoName geoName = new GeoName(geoLine);
		Suggestion suggestion = suggestionBuildingService.toSuggestion(geoName, "Londo");
		
		double score = suggestion.getScore();
		boolean greaterThanZero = Double.compare(score, 0) > 0;
		
		assertAll(() -> assertNotNull(suggestion.getName()), //
				() -> assertTrue(greaterThanZero));
	}

}
