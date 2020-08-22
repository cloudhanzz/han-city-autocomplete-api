package han.jiayun.city.autocomplete.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.exceptions.InvalidLatitudeException;
import han.jiayun.city.autocomplete.service.LatitudeValidatingService;

/**
 * Class for testing LatitudeValidator
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test the Latitude Validator")
public class LatitudeValidatorTest {
	
	@Autowired
	private LatitudeValidatingService latitudeValidatingService;
	
	@Test
	@DisplayName("It is OK if latitude is absent")
	public void ok_if_latitude_is_absent () {
		Optional<Double> latitude = Optional.empty();
		latitudeValidatingService.validate(latitude);
	}
	

	@Test
	@DisplayName("It is OK if latitude is exclusively between -90 and 90")
	public void ok_if_latitude_is_within_range () {
		Optional<Double> latitude = Optional.of(32.322);
		latitudeValidatingService.validate(latitude);
	}
	
	@Test
	@DisplayName("Once present, latitude must be ranged between -90 and 90, both inclusive")
	public void latitude_cannot_be_out_of_range() {
		Throwable throwable = assertThrows(InvalidLatitudeException.class, () -> latitudeValidatingService.validate(Optional.of(-90.01)));
		assertEquals("Invalid latitude", throwable.getMessage());
	}

}
