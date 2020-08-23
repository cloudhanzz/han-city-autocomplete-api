package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.EvolutionService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test suggestion evolution service")
public class EvolutionServiceTest {

	@Autowired
	private EvolutionService evolutionService;

	private Suggestion suggestion_point_3;
	private Suggestion suggestion_point_5;
	private Suggestion suggestion_point_6;
	private Suggestion suggestion_point_9;

	private List<Suggestion> suggestions;

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

		suggestions = new ArrayList<>();

		suggestions.add(suggestion_point_3);
		suggestions.add(suggestion_point_5);
		suggestions.add(suggestion_point_6);
		suggestions.add(suggestion_point_9);

		Collections.sort(suggestions);
	}

	@Test
	@DisplayName("Evolution happens unconditionally with small pool of suggestions")
	public void unconditional_evolution_with_small_pool_of_suggestions() {

		int limit = suggestions.size() + 1;
		
		Suggestion bad_suggestion = new Suggestion().name("Londontowne, MD, USA").latitude(38.93345).longitude(-76.54941)
				.score(0.1);

		boolean evovled = evolutionService.evolve(limit, suggestions, bad_suggestion);

		assertTrue(evovled);
		assertEquals(0.1, suggestions.get(suggestions.size() - 1).getScore(), "Evolution took place unconditionally");

	}

	@Test
	@DisplayName("With big pool of suggestions, unhealthy suggestion still does not trigger evolution")
	public void With_big_pool_of_suggestions_unhealthy_suggestion_not_trigger_evolution() {

		int limit = suggestions.size();
		
		Suggestion bad_suggestion = new Suggestion().name("Londontowne, MD, USA").latitude(38.93345).longitude(-76.54941)
				.score(0.1);

		boolean evovled = evolutionService.evolve(limit, suggestions, bad_suggestion);

		assertFalse(evovled);
		assertEquals(0.3, suggestions.get(suggestions.size() - 1).getScore(), "Evolution won't take place with unhealthy suggestion");

	}

	@Test
	@DisplayName("With big enough pool of suggestions, a better suggestion succeeds evolution")
	public void With_big_pool_of_suggestions_evolution_only_when_better_suggestion_appears() {

		int limit = suggestions.size();
		
		Suggestion good_suggestion = new Suggestion().name("Londontowne, MD, USA").latitude(38.93345).longitude(-76.54941)
				.score(0.4);

		boolean evovled = evolutionService.evolve(limit, suggestions, good_suggestion);

		assertTrue(evovled);
		assertEquals(0.4, suggestions.get(suggestions.size() - 1).getScore(), "This one becomes the least healthy in the suggestion pool");

	}
}
