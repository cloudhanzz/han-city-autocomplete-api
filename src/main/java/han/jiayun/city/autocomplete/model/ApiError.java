package han.jiayun.city.autocomplete.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class facilitates displaying a meaningful message to the client when
 * something went wrong.
 * 
 * @author cloud
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiError {
	private HttpStatus status;
	private String message;
	private List<String> notes;

	public ApiError(HttpStatus status, String message, String note) {
		this(status, message, Arrays.asList(note));
	}
}
