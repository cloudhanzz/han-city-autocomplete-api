package han.jiayun.city.autocomplete.model;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static han.jiayun.city.autocomplete.util.Constants.TAB_REGEX;

/**
 * This models a GeoName,which is a location. For the sake of simplicity, it
 * contains the minimal but core information of a geographical spot.
 * 
 * @author Jiayun Han
 *
 */
@Getter
@Slf4j
@ToString(callSuper = true)
public class GeoName extends Coordinate {

	public static final int CITY_IDX = 0;
	public static final int LATITUDE_IDX = 1;
	public static final int LONGITUDE_IDX = 2;
	public static final int COUNTRY_IDX = 3;
	public static final int ADMIN1_IDX = 4;

	private String city;
	private String admin1;
	private String country;

	/**
	 * Parses a geoLine into a GeoName.
	 * 
	 * @param geoLine is in the format of
	 *                place_tab_latitude_tab_longitude_country-code_admin1-code",
	 *                the following are two examples:
	 * 
	 *                <pre>
	 *                Virgin Rocks	46.42886	-50.81995	CA	05
	 *                Diblee Dyke Light	46.11783	-122.98728	US	OR
	 *                </pre>
	 */
	public GeoName(String geoLine) {
		String[] parts = geoLine.split(TAB_REGEX);

		try {
			this.city = parts[CITY_IDX];
			double latitude = Double.parseDouble(parts[LATITUDE_IDX]);
			double longitude = Double.parseDouble(parts[LONGITUDE_IDX]);
			this.country = parts[COUNTRY_IDX];
			this.admin1 = parts[ADMIN1_IDX];

			setLatitude(latitude);
			setLongitude(longitude);
		} catch (Exception e) {
			log.debug("Skipped malformated: {}", geoLine);
		}
	}

	/**
	 * Returns an instance's city-level name starts with the provided prefix
	 * 
	 * @param prefix The prefix to check the city-level name against
	 * @return
	 */
	public boolean cityStartsWith(String prefix) {
		return city.startsWith(prefix);
	}
}
