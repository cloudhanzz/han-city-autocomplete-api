package han.jiayun.city.autocomplete.service;

import java.util.List;

import han.jiayun.city.autocomplete.model.GeoName;

public interface GeoNameService {	
	List<GeoName> unmodifiableGeoNames();
}
