package han.jiayun.city.autocomplete.service;

import java.util.List;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.model.GeoName;

/**
 * 
 * @author Jiayun Han
 *
 */
public interface GeoNameService {

	/**
	 * 
	 * @return The unmodifiable list of all GeoNames
	 */
	List<GeoName> unmodifiableGeoNames();

	/**
	 * Returns the admin1 code given a Coordinate
	 * 
	 * @param coordinate whose admin1 code to be returned
	 * @return The admin1 code or null if not found
	 */
	String getAdmin1ByCoordinate(Coordinate coordinate);

	/**
	 * Returns the list of GeoNames of an admin1 code
	 * 
	 * @param admin1Code whose geoNames to be returned
	 * @return The list of GeoNames of that admin1 code, or null if not found
	 */
	List<GeoName> geoNamesByAdmin1Code(String admin1Code);
}
