package han.jiayun.city.autocomplete.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.exceptions.InvalidLongitudeException;
import han.jiayun.city.autocomplete.service.LongitudeValidatingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class LongitudeValidator implements LongitudeValidatingService {

	@Override
	public void validate(Optional<Double> longitude) throws InvalidLongitudeException {
		if(longitude.isPresent()) {
			double value = longitude.get();
			if((Double.compare(value, -180) < 0) || (Double.compare(value, 180) > 0)){
				throw InvalidLongitudeException.instance();
			}
		}
	}
}
