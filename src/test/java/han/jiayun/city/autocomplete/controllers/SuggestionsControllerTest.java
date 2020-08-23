package han.jiayun.city.autocomplete.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import han.jiayun.city.autocomplete.exceptions.InvalidLatitudeException;
import han.jiayun.city.autocomplete.exceptions.InvalidLongitudeException;
import han.jiayun.city.autocomplete.exceptions.MissingQueryStringException;
import han.jiayun.city.autocomplete.exceptions.InvalidQueryStringException;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test getting location suggestions: focus on error capturing")
public class SuggestionsControllerTest {
private static final String BASE_URL = "/cityautocomplete/v1.0/suggestions";
	
	private final String URL_WITH_LATITUDE = BASE_URL + "?q=London&latitude=";	
	private final String URL_WITH_LONGITUDE = BASE_URL + "?q=London&longitude=";
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("When a non-get method is invoked, it should not be allowed")
	public void test_unsupported_method() throws Exception {
		
		mvc.perform(post(BASE_URL)) //
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpRequestMethodNotSupportedException));		
	}

	@Test
	@DisplayName("Asking for suggestions with no query term, error should be returned.")
	public void missing_query_term() throws Exception {
		mvc.perform(get(BASE_URL)) //
				.andExpect(status().isBadRequest()) //
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException));
	}

	@Test
	@DisplayName("Asking for suggestions with blank query term, error should be returned.")
	public void blank_query_term() throws Exception {
		mvc.perform(get(BASE_URL + "?q=      ")) //
				.andExpect(status().isBadRequest()) //
				.andExpect(result -> assertEquals(MissingQueryStringException.MESSAGE, result.getResolvedException().getMessage()));
	}
	
	@Test
	@DisplayName("Asking for suggestions with query term starting with a punctuation mark, error should be returned.")
	public void invalid_query_term() throws Exception {
		mvc.perform(get(BASE_URL + "?q=,London")) //
		.andExpect(status().isBadRequest()) //
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidQueryStringException));
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-109, -91, 91, 109})
	@DisplayName("Latitude is optional but once present, it must be between -90 and 90, both inclusive")
	public void invalid_latitude_caputured(double latitude)  throws Exception {
		
		mvc.perform(get(URL_WITH_LATITUDE + latitude)) //
		.andExpect(status().isUnprocessableEntity()) //
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidLatitudeException));
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-209, -191, 191, 209})
	@DisplayName("Longitude is optional but once present, it must be between -180 and 180, both inclusive")
	public void invalid_longitude_caputured(double longitude)  throws Exception {
		
		mvc.perform(get(URL_WITH_LONGITUDE + longitude)) //
		.andExpect(status().isUnprocessableEntity()) //
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidLongitudeException));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/good_queryStrings_latitudes_and_longitudes.csv")
	@DisplayName("With valid search term plus valid latitude and longitude, no error should be returned")
	public void with_good_latitude_and_longitude(String term, String latitude, String longitude)  throws Exception {
				
		String url = BASE_URL + "?q=" + term + "&latitude=" + latitude + "&longitude=" + longitude;
		mvc.perform(get(url)) //
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("latitude and longitude are optional")
	public void no_latitude_or_longitude_is_ok() throws Exception {
		String url = BASE_URL + "?q=London";
		mvc.perform(get(url)) //
		.andExpect(status().isOk());
	}
}
