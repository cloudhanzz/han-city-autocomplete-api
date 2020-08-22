package han.jiayun.city.autocomplete.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
 * An exception of this class is triggered if no non-empty query string is
 * provided.
 * 
 * @author Jiayun Han
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingQueryStringException extends RestException {

	private static final long serialVersionUID = 1L;
	private static final String NOTE = "A non-empty query string must be provided, e.g. q=Washingt";

	private static final MissingQueryStringException INSTANCE = new MissingQueryStringException();

	private MissingQueryStringException() {
		super("Missing non-empty query string", HttpStatus.BAD_REQUEST, NOTE);
	}

	public static MissingQueryStringException instance() {
		return INSTANCE;
	}
}
