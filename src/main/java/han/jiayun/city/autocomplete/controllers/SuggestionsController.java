package han.jiayun.city.autocomplete.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import han.jiayun.city.autocomplete.model.Suggestion;
import han.jiayun.city.autocomplete.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Jiayun Han
 *
 */
@RestController
@RequestMapping(value = "/cityautocomplete/v1.0", produces = "application/json")
public class SuggestionsController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/suggestions")
	@ApiOperation(value = "Suggest locations based on the query criteria", response = Suggestion.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Missing required query term"),
			@ApiResponse(code = 422, message = "Invalid latitude and/or invalid longitude"),
			@ApiResponse(code = 500, message = "Server failed to perform the operation") })
	public List<Suggestion> getSuggestions(@RequestParam("q") String queryString,
			@RequestParam Optional<Double> latitude,
			@RequestParam Optional<Double> longitude) {
		return locationService.searchLocations(queryString, latitude, longitude);
	}
	
}
