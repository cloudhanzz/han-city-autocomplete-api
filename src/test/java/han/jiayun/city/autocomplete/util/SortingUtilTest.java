package han.jiayun.city.autocomplete.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import han.jiayun.city.autocomplete.model.Suggestion;

/**
 * 
 * @author Jiayun Han
 *
 */
@DisplayName("Test the sorting util")
public class SortingUtilTest {

	@Test
	@DisplayName("A sorted suggestions still is sorted in descending order when a new suggestion is inserted")
	public void suggestions_still_sorted_descending() {
		
		List<Suggestion> suggestions = new ArrayList<>();

		Suggestion s3 = new Suggestion(-13.1, 32.3, "sg", 0.79);
		Suggestion s2 = new Suggestion(23.1, 37.3, "sg", 0.89);
		Suggestion s1 = new Suggestion(23.1, 32.3, "sg", 0.99);  
		Suggestion s4 = new Suggestion(23.1, 32.3, "sg", 0.69);  
		Suggestion s5 = new Suggestion(23.1, 32.3, "sg", 0.59);  
		Suggestion s6 = new Suggestion(23.1, 32.3, "sg", 0.49);  
		Suggestion s7 = new Suggestion(23.1, 32.3, "sg", 0.39);  
		Suggestion s8 = new Suggestion(23.1, 32.3, "sg", 0.29);  

		SortingUtil.orderedInsert(suggestions, s3);
		SortingUtil.orderedInsert(suggestions, s2);
		SortingUtil.orderedInsert(suggestions, s1);
		SortingUtil.orderedInsert(suggestions, s4);
		SortingUtil.orderedInsert(suggestions, s5);
		SortingUtil.orderedInsert(suggestions, s6);
		SortingUtil.orderedInsert(suggestions, s7);
		SortingUtil.orderedInsert(suggestions, s8);
		
		System.out.println(suggestions);
		
		assertAll("Suggestions should be in descending order",
				() -> assertTrue(suggestions.get(0).getScore() > suggestions.get(1).getScore()), //
				() -> assertTrue(suggestions.get(1).getScore() > suggestions.get(2).getScore()), //
				() -> assertTrue(suggestions.get(2).getScore() > suggestions.get(3).getScore()), //
				() -> assertTrue(suggestions.get(3).getScore() > suggestions.get(4).getScore()), //
				() -> assertTrue(suggestions.get(4).getScore() > suggestions.get(5).getScore()), //
				() -> assertTrue(suggestions.get(5).getScore() > suggestions.get(6).getScore()), //
				() -> assertTrue(suggestions.get(6).getScore() > suggestions.get(7).getScore()));
		
	}

}
