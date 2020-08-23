package han.jiayun.city.autocomplete.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class models a suggestion about a search result.
 * 
 * @author Jiayun Han
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Suggestion extends Coordinate implements Comparable<Suggestion> {

	/*
	 * Format: city-level-name, state/province-name, country-name. e.g. London,
	 * Ontario, Canada; London, Ohio, USA
	 */
	private String name;

	// 0 - 1, both inclusive
	private double score;

	public Suggestion(double latitude, double longitude, String name, double score) {
		super(latitude, longitude);
		setName(name);
		setScore(score);
	}

	public Suggestion name(String name) {
		this.name = name;
		return this;
	}

	public Suggestion latitude(double latitude) {
		setLatitude(latitude);
		return this;
	}

	public Suggestion longitude(double longitude) {
		setLongitude(longitude);
		return this;
	}

	public Suggestion score(double score) {
		this.score = score;
		return this;
	}

	@Override
	public int compareTo(Suggestion that) {
		// in descending order
		return -(Double.compare(this.score, that.score));
	}

	/**
	 * Returns true if this suggestion has higher score than that suggestion
	 * 
	 * @param that The suggestion for comparison
	 * @return true if this suggestion has higher score than that suggestion; false
	 *         otherwise
	 */
	public boolean isBetterThan(Suggestion that) {
		int result = Double.compare(this.score, that.score);
		return result > 0;
	}

}
