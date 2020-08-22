package han.jiayun.city.autocomplete.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.exceptions.InvalidLatitudeException;
import han.jiayun.city.autocomplete.service.LatitudeValidatingService;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class LatitudeValidator implements LatitudeValidatingService {

	@Override
	public void validate(Optional<Double> latitude) throws InvalidLatitudeException {
		if(latitude.isPresent()) {
			double value= latitude.get();
			if((Double.compare(value, -90) < 0) || (Double.compare(value, 90) > 0)){
				throw InvalidLatitudeException.instance();
			}
		}
	}
}
