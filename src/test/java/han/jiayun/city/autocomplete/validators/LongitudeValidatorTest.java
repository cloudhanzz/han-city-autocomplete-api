package han.jiayun.city.autocomplete.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.exceptions.InvalidLongitudeException;
import han.jiayun.city.autocomplete.service.LongitudeValidatingService;

/**
 * Class for testing LongitudeValidator
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test the Longitude Validator")
public class LongitudeValidatorTest {
	
	@Autowired
	private LongitudeValidatingService longitudeValidatingService;
	
	@Test
	@DisplayName("It is OK if longitude is absent")
	public void ok_if_longitude_is_absent () {
		Optional<Double> longitude = Optional.empty();
		longitudeValidatingService.validate(longitude);
	}
	

	@Test
	@DisplayName("It is OK if longitude is exclusively between -180 and 180")
	public void ok_if_longitude_is_within_range () {
		Optional<Double> longitude = Optional.of(132.322);
		longitudeValidatingService.validate(longitude);
	}
	
	@Test
	@DisplayName("Once present, longitude must be ranged between -180 and 180, both inclusive")
	public void longitude_cannot_be_out_of_range() {
		Throwable throwable = assertThrows(InvalidLongitudeException.class, () -> longitudeValidatingService.validate(Optional.of(-180.01)));
		assertEquals("Invalid longitude", throwable.getMessage());
	}

}
