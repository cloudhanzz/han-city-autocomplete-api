package han.jiayun.city.autocomplete.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.SearchWithCoordinateService;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class SearchWithCoordinateServiceImpl implements SearchWithCoordinateService {

	@Override
	public List<Suggestion> searchLocations(String queryTerm, int limit, double latitude, double longitude) {
		// TODO Auto-generated method stub
		return null;
	}

}
