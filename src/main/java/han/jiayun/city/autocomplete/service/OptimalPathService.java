package han.jiayun.city.autocomplete.service;

import java.util.List;

/**
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface OptimalPathService {

	/**
	 * Returns the heuristically optimal path of admin1Codes of the given
	 * admin1Code. They are ordered in the ascending manner on the distance to the
	 * given admin1Code
	 * 
	 * @param admin1Code
	 * @return The heuristically optimal path of admin1Codes of the given one
	 */
	List<String> getAdmin1CodePath(String admin1Code);

}
