package han.jiayun.city.autocomplete.service;

import java.util.List;

import han.jiayun.city.autocomplete.model.Suggestion;

/**
 * This service is responsible for keeping the best suggestions over the search
 * process.
 * 
 * @author Jiayun Han
 *
 */
public interface EvolutionService {

	/**
	 * Evolve the suggestions to keep the fittest by if the suggestion is fitter
	 * than the poorest of the suggestions. The fitness of a suggestion is judged by
	 * its score.
	 * 
	 * @param int           limit the threshold. If suggestions' size is less than
	 *                      this number, evolution starts unconditionally; otherwise
	 *                      evolution starts only when a fitter one jumps in
	 * @param suggestions
	 * @param newSuggestion
	 * 
	 * @return true if newSuggestion replaced a poor suggestion; false otherwise
	 */
	boolean evolve(int limit, List<Suggestion> suggestions, Suggestion newSuggestion);

}
