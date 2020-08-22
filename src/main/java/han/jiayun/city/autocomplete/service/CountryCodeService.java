package han.jiayun.city.autocomplete.service;

/**
 * Service responsible for checking country names
 * 
 * @author Jiayun Han
 *
 */
public interface CountryCodeService {

	/**
	 * Returns the country name given a country code
	 * 
	 * @param countryCode The country code whose corresponding name to be found
	 * @return The country name given a country code; null if not found
	 */
	String getCountryByCode(String countryCode);

}
