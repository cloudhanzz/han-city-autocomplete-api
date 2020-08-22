package han.jiayun.city.autocomplete.model;

import lombok.Getter;
import lombok.ToString;

/**
 * This class models a suggestion about a search result.
 * 
 * @author Jiayun Han
 *
 */
@Getter
@ToString
public class Suggestion implements Comparable<Suggestion> {

	/*
	 * Format: city-level-name, state/province-name, country-name. e.g. London,
	 * Ontario, Canada; London, Ohio, USA
	 */
	private String name;
	private double latitude;
	private double longitude;
	
	// 0 - 1, both inclusive
	private double score;

	public Suggestion name(String name) {
		this.name = name;
		return this;
	}

	public Suggestion latitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public Suggestion longitude(double longitude) {
		this.longitude = longitude;
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

}
