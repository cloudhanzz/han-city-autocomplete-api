package han.jiayun.city.autocomplete.service;

/**
 * A service calculating the score based on name only
 * 
 * @author Jiayun Han
 *
 */
@FunctionalInterface
public interface NameOnlyScoringService {

	/**
	 * Calculates the string similarity score
	 * 
	 * @param str1
	 * @param str2
	 * @return The similarity score of the two strings
	 */
	double score(String str1, String str2);
}
