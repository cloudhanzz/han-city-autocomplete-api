package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.service.GeoNameService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
public class GeoNamesServiceTest {
	
	@Autowired
	private GeoNameService geoNameService;

	@Test
	@DisplayName("Test geoname dataset is loaded with 2,552,060 entries")
	public void test_geoname_dataset_loaded() {
		var dataset = geoNameService.unmodifiableGeoNames();
		assertEquals(2_551_416, dataset.size());
	}
	
	@Test
	@DisplayName("Test geoname dataset cannot be modified")
	public void test_geoname_dataset_unmodifiable() {
		var dataset = geoNameService.unmodifiableGeoNames();
		assertThrows(UnsupportedOperationException.class, () -> dataset.remove(0));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/coordinates_admin1_codes.csv")
	@DisplayName("Test admin1 code can be found a coordinate existing in the dataset")
	public void test_admin1_code_found(String strLat, String strLog, String admin1) {
		Coordinate coord = new Coordinate(Double.parseDouble(strLat), Double.parseDouble(strLog));
		var admin1Code = geoNameService.getAdmin1ByCoordinate(coord);
		assertEquals(admin1, admin1Code);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/admin1_codes.csv")
	@DisplayName("Test all admin1 codes are now associated with their GeoName list")
	public void test_all_admin1_codes_have_geonames(String admin1) {
		
		var geoNames = geoNameService.geoNamesByAdmin1Code(admin1);
		assertTrue(!geoNames.isEmpty());
	}

}
