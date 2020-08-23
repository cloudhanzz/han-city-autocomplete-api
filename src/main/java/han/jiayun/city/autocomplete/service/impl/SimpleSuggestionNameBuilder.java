package han.jiayun.city.autocomplete.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.service.Admin1CodeService;
import han.jiayun.city.autocomplete.service.CountryCodeService;
import han.jiayun.city.autocomplete.service.SuggestionNamingService;

/**
 * A simple implementation
 * 
 * @author Jiayun Han
 *
 */
@Service
public class SimpleSuggestionNameBuilder implements SuggestionNamingService {

	@Autowired
	private CountryCodeService countryCodeService;

	@Autowired
	private Admin1CodeService admin1CodeService;

	@Override
	public String toSuggestionName(GeoName geoName) {
		
		String admin1Name = admin1CodeService.getAdmin1NameByCode(geoName.getAdmin1());
		String countryName = countryCodeService.getCountryByCode(geoName.getCountry());

		return geoName.getCity() + ", " + admin1Name + ", " + countryName;
	}
}
