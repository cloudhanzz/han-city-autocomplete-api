package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		assertEquals(2_552_060, dataset.size());
	}
	
	@Test
	@DisplayName("Test geoname dataset cannot be modified")
	public void test_geoname_dataset_unmodifiable() {
		var dataset = geoNameService.unmodifiableGeoNames();
		assertThrows(UnsupportedOperationException.class, () -> dataset.remove(0));
	}

}
