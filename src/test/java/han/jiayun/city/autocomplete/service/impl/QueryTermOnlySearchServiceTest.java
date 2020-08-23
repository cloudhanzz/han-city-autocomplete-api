package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test search when no latitude or longitude is involved")
public class QueryTermOnlySearchServiceTest {

	@Autowired
	private QueryTermOnlySearcher queryTermOnlySearcher;

	@Test
	@DisplayName("Search locations starting with London and should return 100 scored and named suggestions")
	public void test_search_by_query_term_only() {

		var suggestions = queryTermOnlySearcher.searchLocations("London", 100);
		
		for (var suggestion : suggestions) {
			
			double score = suggestion.getScore();
			boolean greaterThanZero = Double.compare(score, 0) > 0;

			assertAll(() -> assertNotNull(suggestion.getName()), //
					() -> assertTrue(greaterThanZero));
		}

	}

}
