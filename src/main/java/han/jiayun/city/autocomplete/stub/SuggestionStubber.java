package han.jiayun.city.autocomplete.stub;

import java.util.List;

import han.jiayun.city.autocomplete.model.Suggestion;

/**
 * A class to providing an invariant list of stubbed suggestions for quick
 * testing the controller
 * 
 * @author Jiayun Han
 *
 */
public final class SuggestionStubber {

	private static final List<Suggestion> suggestions = List.of( //
			new Suggestion().name("Londontowne, MD, USA").latitude(38.93345).longitude(-76.54941).score(0.3),
			new Suggestion().name("London, KY, USA").latitude(37.12898).longitude(-84.08326).score(0.5),
			new Suggestion().name("London, OH, USA").latitude(39.88645).longitude(-83.44825).score(0.5),
			new Suggestion().name("London, ON, Canada").latitude(42.98339).longitude(-81.23304).score(0.9));

	private SuggestionStubber() {
	}

	/**
	 * @return An invariant list of stubbed suggestions for quick testing the
	 *         controller
	 */
	public static List<Suggestion> cannedSuggestions() {
		return suggestions;
	}
}
