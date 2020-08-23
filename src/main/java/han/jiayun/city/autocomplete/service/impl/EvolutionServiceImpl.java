package han.jiayun.city.autocomplete.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.EvolutionService;
import han.jiayun.city.autocomplete.util.SortingUtil;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class EvolutionServiceImpl implements EvolutionService {

	@Override
	public boolean evolve(int limit, List<Suggestion> suggestions, Suggestion newSuggestion) {

		boolean evolved = false;

		if (suggestions.size() < limit) {
			evolved = evolveUnconditionally(suggestions, newSuggestion);
		} else {
			evolved = evolueOnlyWithBetterSuggestion(suggestions, newSuggestion);
		}

		return evolved;
	}

	private boolean evolveUnconditionally(List<Suggestion> suggestions, Suggestion newSuggestion) {
		SortingUtil.orderedInsert(suggestions, newSuggestion);
		return true;
	}

	private boolean evolueOnlyWithBetterSuggestion(List<Suggestion> suggestions, Suggestion newSuggestion) {
		
		boolean evolved = false;
		int lastIndex = suggestions.size() - 1;
		Suggestion worstSuggestion = suggestions.get(lastIndex);

		if (newSuggestion.isBetterThan(worstSuggestion)) {

			suggestions.remove(lastIndex);
			evolved = evolveUnconditionally(suggestions, newSuggestion);
		}
		
		return evolved;
	}

}
