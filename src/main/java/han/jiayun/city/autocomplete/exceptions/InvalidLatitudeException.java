package han.jiayun.city.autocomplete.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception of this class is triggered if an invalid latitude is provided.
 * 
 * <p>
 * Only the format of signed degrees is supported.
 * <P>
 * A latitude can be absent but once present, it must be in the range of -90 ~ 90 (both inclusive)
 * 
 * @author Jiayun Han
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidLatitudeException extends RestException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Invalid latitude";
	private static final String NOTE = "A valid latitude ranges from -90 to 90, both inclusive, and it must be in the format of signed degrees";

	private static InvalidLatitudeException INSTANCE = new InvalidLatitudeException(MESSAGE);

	private InvalidLatitudeException(String message) {
		super(message, HttpStatus.UNPROCESSABLE_ENTITY, NOTE);
	}

	public static InvalidLatitudeException instance() {
		return INSTANCE;
	}
}
