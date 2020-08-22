package han.jiayun.city.autocomplete.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the parent of all REST exception classe, responsible for centralizing
 * logging the error message descriptively.
 * 
 * @author Jiayun Han
 *
 */
@Getter
@Slf4j
public class RestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String note;

	/**
	 * 
	 * @param message    The message provided by inherited class
	 * @param httpStatus The status for response
	 * @param note       More detailed explanation or suggestion on how to fix the
	 *                   exception
	 */
	public RestException(String message, HttpStatus httpStatus, String note) {
		super(message);
		this.httpStatus = httpStatus;
		this.note = note;
		log.error(message);
	}
}
