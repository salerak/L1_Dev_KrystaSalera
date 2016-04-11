package ca.krystasalera.domain;

import java.io.File;
import java.io.IOException;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;


public class GetLocation {
	
	
	public ServerLocation getLocation(String ipAddress) {

		File file = new File(
		    "C:\\Program Files\\Eclipse_Apache\\workspace\\L1_Dev_KrystaSalera\\L1_Dev_KrystaSalera_SpringBoot\\src\\main\\resources\\GeoLiteCity.dat");
		return getLocation(ipAddress, file);

	  }

	  public ServerLocation getLocation(String ipAddress, File file) {

		ServerLocation serverLocation = null;

		try {

		serverLocation = new ServerLocation();

		LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
		Location locationServices = lookup.getLocation(ipAddress);

		serverLocation.setCountryCode(locationServices.countryCode);
		serverLocation.setCountryName(locationServices.countryName);
		serverLocation.setRegion(locationServices.region);
		serverLocation.setRegionName(regionName.regionNameByCode(
	             locationServices.countryCode, locationServices.region));
		serverLocation.setCity(locationServices.city);
		serverLocation.setPostalCode(locationServices.postalCode);
		serverLocation.setLatitude(String.valueOf(locationServices.latitude));
		serverLocation.setLongitude(String.valueOf(locationServices.longitude));

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return serverLocation;

	  }
	

}
