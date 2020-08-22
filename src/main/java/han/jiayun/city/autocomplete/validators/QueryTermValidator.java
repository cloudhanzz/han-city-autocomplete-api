package han.jiayun.city.autocomplete.validators;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.exceptions.InvalidQueryStringException;
import han.jiayun.city.autocomplete.exceptions.MissingQueryStringException;
import han.jiayun.city.autocomplete.service.QueryTermValidingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class QueryTermValidator implements QueryTermValidingService {

	@Override
	public void validate(String queryTerm) throws MissingQueryStringException, InvalidQueryStringException {

		checkBlankQuryTerm(queryTerm);
		checkInvalidQueryTerm(queryTerm);
	}

	private void checkBlankQuryTerm(String queryTerm) {
		if (queryTerm.isBlank()) {
			throw MissingQueryStringException.instance();
		}
	}

	private void checkInvalidQueryTerm(String queryTerm) {
		queryTerm = queryTerm.strip();
		if (queryTerm.matches("\\p{Punct}.*")) {
			throw new InvalidQueryStringException(queryTerm);
		}
	}

}
