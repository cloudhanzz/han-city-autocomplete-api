package han.jiayun.city.autocomplete.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception of this class is triggered if an invalid longitude is provided.
 * 
 * <p>
 * Only the format of signed degrees is supported.
 * <P>
 * A longitude can be absent but once present, it must be in the range of -180 ~ 180 (both inclusive)
 * 
 * @author Jiayun Han
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidLongitudeException extends RestException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Invlid latitude";
	private static final String NOTE = "A valid latitude ranges from -180 to 180, both inclusive, and it must be in the format of signed degrees";

	private static InvalidLongitudeException INSTANCE = new InvalidLongitudeException(MESSAGE);

	private InvalidLongitudeException(String message) {
		super(message, HttpStatus.UNPROCESSABLE_ENTITY, NOTE);
	}

	public static InvalidLongitudeException instance() {
		return INSTANCE;
	}
}
