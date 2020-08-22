package han.jiayun.city.autocomplete.service;

import han.jiayun.city.autocomplete.exceptions.InvalidQueryStringException;
import han.jiayun.city.autocomplete.exceptions.MissingQueryStringException;

/**
 * Responsible for validating the query term
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface QueryTermValidingService {

	/**
	 * Validating the query term
	 * 
	 * @param queryTerm the query string to be validated
	 * @throws MissingQueryStringException if the query term is virtually blank,
	 *                                     i.e. just containing white spaces
	 * @throws InvalidQueryStringException if the query term starts with a
	 *                                     punctuation mark
	 */
	void validate(String queryTerm) throws MissingQueryStringException, InvalidQueryStringException;
}
