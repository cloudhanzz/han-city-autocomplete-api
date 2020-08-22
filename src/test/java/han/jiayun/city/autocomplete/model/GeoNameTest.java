package han.jiayun.city.autocomplete.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * 
 * @author Jiayun Han
 *
 */
@DisplayName("Test building a GeoName from a geo-name string")
public class GeoNameTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/good_geolines.csv")
	@DisplayName("Test well-formed geolines can be parsed into GeoNames")
	public void test_well_formed_geolines(String geoLine, //
			String city, String strLati, String strLongi, String countryCode, String admin1) {
		GeoName geoName = new GeoName(geoLine);
		double latitude = Double.parseDouble(strLati);
		double longitude = Double.parseDouble(strLongi);

		assertAll(//
				() -> assertEquals(city, geoName.getCity()), //
				() -> assertEquals(latitude, geoName.getLatitude()), //
				() -> assertEquals(longitude, geoName.getLongitude()), //
				() -> assertEquals(countryCode, geoName.getCountry()), //
				() -> assertEquals(admin1, geoName.getAdmin1()));

	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/some-start-with-Par.csv")
	@DisplayName("Test whether a GeoName starts with 'Par'")
	public void test_start_with_Par(String geoLine, String startsWith) {
		
		GeoName geoName = new GeoName(geoLine);
		boolean bool = Boolean.parseBoolean(startsWith);
		
		assertEquals(bool, geoName.cityStartsWith("Par"));
	}

}
