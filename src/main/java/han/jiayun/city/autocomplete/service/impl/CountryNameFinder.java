package han.jiayun.city.autocomplete.service.impl;

import static han.jiayun.city.autocomplete.util.Constants.TAB_REGEX;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.io.Files;

import han.jiayun.city.autocomplete.service.CountryCodeService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
@Slf4j
public class CountryNameFinder implements CountryCodeService {

	private static Map<String, String> countryCodeMap;
	static {
		countryCodeMap = new HashMap<>();

		try {
			Files.readLines(new File("src/main/resources/country-codes.txt"), Charset.forName("UTF-8")) //
					.stream() //
					.map(s -> s.split(TAB_REGEX)) //
					.forEach(ss -> countryCodeMap.put(ss[0], ss[1]));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public String getCountryByCode(String countryCode) {
		return countryCodeMap.get(countryCode);
	}

}
