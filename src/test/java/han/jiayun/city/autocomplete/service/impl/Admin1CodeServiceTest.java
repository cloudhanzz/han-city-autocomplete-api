package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.service.Admin1CodeService;

/**
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test Admin1CodeService")
public class Admin1CodeServiceTest {
	
	@Autowired
	private Admin1CodeService admin1CodeService;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/admin1_codes.csv")
	@DisplayName("Test the system can find the full names of US states and Canada provinces")
	public void US_and_CA_names(String admin1Code, String name) {
		assertEquals(name, admin1CodeService.getAdmin1NameByCode(admin1Code));
	}

}
