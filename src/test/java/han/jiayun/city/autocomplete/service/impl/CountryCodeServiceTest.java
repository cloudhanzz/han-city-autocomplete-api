package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.service.CountryCodeService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test CountryCodeService")
public class CountryCodeServiceTest {
	
	@Autowired
	private CountryCodeService countryCodeService;
	
	@Test
	@DisplayName("The system can find the full name of US and CA can be found")
	public void US_and_CA_names() {
		assertAll(() -> assertEquals("USA", countryCodeService.getCountryByCode("US")),
				() -> assertEquals("Canada", countryCodeService.getCountryByCode("CA")));
	}

}
