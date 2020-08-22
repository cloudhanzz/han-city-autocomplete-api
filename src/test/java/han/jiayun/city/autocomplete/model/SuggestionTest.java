package han.jiayun.city.autocomplete.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Suggestion model")
public class SuggestionTest {

	private Suggestion suggestion_point_3;
	private Suggestion suggestion_point_5;
	private Suggestion suggestion_point_6;
	private Suggestion suggestion_point_9;

	@BeforeEach
	public void createSuggestions() {

		suggestion_point_3 = new Suggestion().name("Londontowne, MD, USA").latitude(38.93345).longitude(-76.54941)
				.score(0.3);

		suggestion_point_5 = new Suggestion().name("London, KY, USA").latitude(37.12898).longitude(-84.08326)
				.score(0.5);

		suggestion_point_6 = new Suggestion().name("London, OH, USA").latitude(39.88645).longitude(-83.44825)
				.score(0.6);
		
		suggestion_point_9 = new Suggestion().name("London, ON, Canada").latitude(42.98339).longitude(-81.23304)
				.score(0.9);
	}
	
	@Test
	@DisplayName("Suggestions are ordered by their scores in descending manner.")
	public void test_sorted_in_descending_manner() {
		List<Suggestion> suggestions = new ArrayList<>();
		suggestions.add(suggestion_point_3);
		suggestions.add(suggestion_point_5);
		suggestions.add(suggestion_point_6);
		suggestions.add(suggestion_point_9);
		
		Collections.sort(suggestions);
		
		assertAll("All of the following should be true",
				() -> assertEquals(0.9, suggestions.get(0).getScore()),
				() -> assertEquals(0.6, suggestions.get(1).getScore()),
				() -> assertEquals(0.5, suggestions.get(2).getScore()),
				() -> assertEquals(0.3, suggestions.get(3).getScore()));
	}

}
