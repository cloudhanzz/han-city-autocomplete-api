package han.jiayun.city.autocomplete.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import han.jiayun.city.autocomplete.exceptions.InvalidQueryStringException;
import han.jiayun.city.autocomplete.exceptions.MissingQueryStringException;
import han.jiayun.city.autocomplete.service.QueryTermValidingService;

/**
 * Class for testing QueryTermValidator
 * 
 * @author Jiayun Han
 *
 */
@SpringBootTest
@DisplayName("Test the Query Term Validator")
public class QueryTermValidatorTest {
	
	@Autowired
	private QueryTermValidingService queryTermValidingService;
	
	@Test
	@DisplayName("Query term cannot be empty, it must contain at least one non-whitespace character")
	public void query_term_cannot_be_empty () {
		assertThrows(MissingQueryStringException.class, () -> queryTermValidingService.validate(" 	     "));
	}
	
	@Test
	@DisplayName("Query term should not start with a punctuation mark")
	public void query_term_should_not_start_with_a_punc() {
		Throwable throwable = assertThrows(InvalidQueryStringException.class, () -> queryTermValidingService.validate("*London"));
		assertTrue(throwable.getMessage().startsWith("Invalid query string"));
	}

}
