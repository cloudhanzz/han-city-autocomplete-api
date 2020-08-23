package han.jiayun.city.autocomplete.service.impl;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.service.NameOnlyScoringService;

/**
 * Wraps the apache.commons string similarity algorithm to calculate the score
 * 
 * @author Jiayun Han
 *
 */
@Service
public class NameOnlyScoringServiceImpl implements NameOnlyScoringService {

	@Autowired
	private JaroWinklerDistance jaroWinklerDistance;

	@Override
	public double score(String str1, String str2) {
		return jaroWinklerDistance.apply(str1, str2);
	}

}
