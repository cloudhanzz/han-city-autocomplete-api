package han.jiayun.city.autocomplete.service;

import java.util.List;

/**
 * Service responsible for checking level-1 admin codes.
 * 
 * @author Jiayun Han
 *
 */
public interface Admin1CodeService {

	/**
	 * Returns the admin name, given an admin code of level 1
	 * 
	 * @param admin1Code The level-1 admin code. For USA, this refers to the names
	 *                   of the states whereas for Canada, this refers to the names
	 *                   of the provinces
	 * @return The admin name, given an admin code of level 1; null if not found
	 */
	String getAdmin1NameByCode(String admin1Code);

	/**
	 * 
	 * @return All admin1Codes
	 */
	List<String> allAdmin1Codes();

}
