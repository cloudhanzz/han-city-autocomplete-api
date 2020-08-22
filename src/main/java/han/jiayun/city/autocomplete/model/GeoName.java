package han.jiayun.city.autocomplete.model;

import lombok.Getter;
import lombok.ToString;

import static han.jiayun.city.autocomplete.util.Constants.TAB_REGEX;

/**
 * This models a GeoName,which is a location. For the sake of simplicity, it
 * contains the minimal but core information of a geographical spot.
 * 
 * @author Jiayun Han
 *
 */
@Getter
@ToString
public class GeoName extends Coordinate {

	public static final int CITY_IDX = 0;
	public static final int LATITUDE_IDX = 1;
	public static final int LONGITUDE_IDX = 2;
	public static final int COUNTRY_IDX = 3;
	public static final int ADMIN1_IDX = 4;

	private String city;
	private String admin1;
	private String country;

	public GeoName(String nameLine) {
		String[] parts = nameLine.split(TAB_REGEX);
		
		this.city = parts[CITY_IDX];
		double latitude = Double.parseDouble(parts[LATITUDE_IDX]);
		double longitude = Double.parseDouble(parts[LONGITUDE_IDX]);
		this.country = parts[COUNTRY_IDX];
		this.admin1 = parts[ADMIN1_IDX];		

		setLatitude(latitude);
		setLongitude(longitude);
	}

	public boolean cityStartsWith(String prefix) {
		return city.startsWith(prefix);
	}

	public Suggestion toSuggestion() {
		Suggestion s = new Suggestion();
		return s;
	}
}
