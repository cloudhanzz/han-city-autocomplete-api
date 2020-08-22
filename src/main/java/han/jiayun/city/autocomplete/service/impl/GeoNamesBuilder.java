package han.jiayun.city.autocomplete.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.service.GeoNameService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeoNamesBuilder implements GeoNameService {

	private static List<GeoName> unmodifiableGeoNames;
	
	static {		
		initGeoNameDataSet();
	}

	private static void initGeoNameDataSet() {
		List<GeoName> geoNames = new ArrayList<>();

		try (ZipFile zipFile = new ZipFile("src/main/resources/US-CA-places.zip")) {

			ZipEntry zipEntry = zipFile.entries().nextElement();

			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(zipFile.getInputStream(zipEntry), "UTF-8"))) {

				String line = null;

				int counter = 0;
				int interval = 500_000;

				while ((line = bufferedReader.readLine()) != null) {
					counter++;
					GeoName geoName = new GeoName(line);
					geoNames.add(geoName);

					if (counter % interval == 0) {
						log.info("Build {} entries", counter);
					}
				}

				log.info("Build {} entries", counter);
			}
			
			unmodifiableGeoNames = List.copyOf(geoNames);
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<GeoName> unmodifiableGeoNames() {
		return unmodifiableGeoNames;
	}
}
