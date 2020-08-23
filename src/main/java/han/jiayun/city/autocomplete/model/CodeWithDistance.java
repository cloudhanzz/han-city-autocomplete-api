package han.jiayun.city.autocomplete.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @author Jiayun Han
 *
 */
@Getter
@AllArgsConstructor
@ToString
public class CodeWithDistance implements Comparable<CodeWithDistance> {
	
	private String admin1Code;
	private double distance;
	
	@Override
	public int compareTo(CodeWithDistance o) {
		return Double.compare(distance, o.distance);
	}
}
