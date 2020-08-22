package han.jiayun.city.autocomplete.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
 * An exception of this class is triggered if query string is provided that starts with a punctuation mark.
 * 
 * @author Jiayun Han
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQueryStringException extends RestException {

	private static final long serialVersionUID = 1L;
	private static final String NOTE = "A query string should not start with a punctuation mark.";
	
	public InvalidQueryStringException(String queryTerm) {
		super("Invalid query string: " + queryTerm, HttpStatus.BAD_REQUEST, NOTE);
	}
}
