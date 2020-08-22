package han.jiayun.city.autocomplete.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This models a geographical spot.
 * 
 * @author Jiayun Han
 *
 */
@Getter
@Setter
@ToString
public class Coordinate {

	private double latitude;
	private double longitude;

	public Coordinate() {
	}	
}
