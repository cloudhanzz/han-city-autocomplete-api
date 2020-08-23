package han.jiayun.city.autocomplete.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.service.Admin1CodeService;
import han.jiayun.city.autocomplete.service.OptimalPathService;

/**
 * 
 * @author Jiayun han
 *
 */
@SpringBootTest
@DisplayName("Test optimal path creation")
public class OptimalPathServiceTest {

	@Autowired
	private OptimalPathService optimalPathService;

	@Autowired
	private Admin1CodeService Admin1CodeService;

	@Test
	@DisplayName("Test the optimal path of all admin1 code have been created")
	public void check_optimal_paths_created() {

		for (String code : Admin1CodeService.allAdmin1Codes()) {
			List<String> path = optimalPathService.getAdmin1CodePath(code);

			assertAll("The path should not be empty and should not contain the target code", //
					() -> assertFalse(path.contains(code)), //
					() -> assertTrue(!path.isEmpty()));
		}
	}

}
