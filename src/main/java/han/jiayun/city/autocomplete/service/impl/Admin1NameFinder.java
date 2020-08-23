package han.jiayun.city.autocomplete.service.impl;

import static han.jiayun.city.autocomplete.util.Constants.TAB_REGEX;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.io.Files;

import han.jiayun.city.autocomplete.service.Admin1CodeService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
@Slf4j
public class Admin1NameFinder implements Admin1CodeService {

	private static Map<String, String> admin1CodeMap;

	static {
		
		admin1CodeMap = new HashMap<>();

		try {
			Files.readLines(new File("src/main/resources/US-CA-admin1-codes.txt"), Charset.forName("UTF-8")) //
					.stream() //
					.map(s -> s.split(TAB_REGEX)) //
					.forEach(ss -> admin1CodeMap.put(ss[0], ss[1]));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAdmin1NameByCode(String admin1Code) {
		return admin1CodeMap.get(admin1Code);
	}
	
	@Override
	public List<String> allAdmin1Codes() {
		return new ArrayList<>(admin1CodeMap.keySet());
	}

}
