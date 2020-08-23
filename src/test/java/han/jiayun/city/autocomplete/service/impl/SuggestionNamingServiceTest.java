package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.service.SuggestionNamingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test the simple suggesiton name builder")
public class SuggestionNamingServiceTest {
	
	@Autowired
	private SuggestionNamingService suggestionNamingService;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/geolines_with_suggestion_names.csv")
	@DisplayName("Test correct suggestion names will be built")
	public void correct_suggestion_name_to_be_built(String geoLine, String sugguestionName) {
		
		GeoName geoName = new GeoName(geoLine);
		
		assertEquals(sugguestionName, suggestionNamingService.toSuggestionName(geoName));
	}

}
