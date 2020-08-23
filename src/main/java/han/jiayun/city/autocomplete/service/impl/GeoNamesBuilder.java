package han.jiayun.city.autocomplete.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.GeoName;
import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.service.GeoNameService;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class GeoNamesBuilder implements GeoNameService {

	private static List<GeoName> unmodifiableGeoNames;

	// coordinate --> admin1Code
	private static Map<Coordinate, String> unmodifiableCoordinateMap;
	
	// admin1Code -> list-of--geoNames
	private static Map<String, List<GeoName>> unmodifiableAmdin1GeonamesMap;

	static {
		initGeoNameDataSet();
	}

	/**
	 * Creates the geoName list and coordinate-to-admin1 map
	 */
	private static void initGeoNameDataSet() {
		List<GeoName> geoNames = new ArrayList<>();
		Map<Coordinate, String> coordinateMap = new HashMap<>();

		try (ZipFile zipFile = new ZipFile("src/main/resources/US-CA-places.zip")) {

			ZipEntry zipEntry = zipFile.entries().nextElement();

			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(zipFile.getInputStream(zipEntry), "UTF-8"))) {

				String line = null;

				int counter = 0;
				int interval = 500_000;

				while ((line = bufferedReader.readLine()) != null) {					
					
					GeoName geoName = new GeoName(line);
					
					if(geoName.getAdmin1() != null) {
						geoNames.add(geoName);
						counter++;
					}

					Coordinate coord = new Coordinate(geoName.getLatitude(), geoName.getLongitude());
					String admin1Code = geoName.getAdmin1();
					
					if(admin1Code != null) {
						coordinateMap.put(coord, admin1Code);
					}

					if (counter % interval == 0) {
						log.info("Build {} entries", counter);
					}
				}

				log.info("Build {} entries", counter);
			}
			
			Map<String, List<GeoName>> admin1GeoNames = geoNames.stream().collect(groupingBy(GeoName::getAdmin1));
			unmodifiableAmdin1GeonamesMap = Map.copyOf(admin1GeoNames);
			
			unmodifiableGeoNames = List.copyOf(geoNames);
			
			unmodifiableCoordinateMap = Map.copyOf(coordinateMap);

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<GeoName> unmodifiableGeoNames() {
		return unmodifiableGeoNames;
	}
	
	@Override
	public String getAdmin1ByCoordinate(Coordinate coordinate) {
		return unmodifiableCoordinateMap.get(coordinate);
	}
	
	@Override
	public List<GeoName> geoNamesByAdmin1Code(String admin1Code) {
		return unmodifiableAmdin1GeonamesMap.get(admin1Code);
	}
}
