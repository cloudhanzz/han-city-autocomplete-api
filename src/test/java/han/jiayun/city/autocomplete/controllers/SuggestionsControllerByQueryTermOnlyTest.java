package han.jiayun.city.autocomplete.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test getting location suggestions: Search by query term only")
public class SuggestionsControllerByQueryTermOnlyTest {
private static final String BASE_URL = "/cityautocomplete/v1.0/suggestions";
	
	@Autowired
	private MockMvc mvc;
	
	@Value("${suggestion.limit}")
	private int limit;

	@Test
	@DisplayName("Should return required number of locations starting with Londo with scores")
	public void return_required_suggestions_for_Londo() throws Exception {
		mvc.perform(get(BASE_URL+ "?q=Londo")) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$", hasSize(limit)));
	}

	@Test
	@DisplayName("Should return zero suggestions for a bogus query term")
	public void no_suggestion_returned_for_a_bogus_query_term() throws Exception {
		String queryTerm = "a-really-wild-bogus-query-term";
		mvc.perform(get(BASE_URL+ "?q=" + queryTerm)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$", hasSize(0)));
	}
}
