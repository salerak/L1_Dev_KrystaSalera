package ca.krystasalera.domain;

public class ServerLocation {

	private String countryCode;
	private String countryName;
	private String region;
	private String regionName;
	private String city;
	private String postalCode;
	private String latitude;
	private String longitude;

	/**
	 * 
	 */
	public ServerLocation() {
		this.countryCode = null;
		this.countryName = null;
		this.region = null;
		this.regionName = null;
		this.city = null;
		this.postalCode = null;
		this.latitude = null;
		this.longitude = null;
	}

	/**
	 * @param countryCode
	 * @param countryName
	 * @param region
	 * @param regionName
	 * @param city
	 * @param postalCode
	 * @param latitude
	 * @param longitude
	 */
	public ServerLocation(String countryCode, String countryName, String region, String regionName, String city,
			String postalCode, String latitude, String longitude) {

		this.countryCode = countryCode;
		this.countryName = countryName;
		this.region = region;
		this.regionName = regionName;
		this.city = city;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServerLocation [countryCode=" + countryCode + ", countryName=" + countryName + ", region=" + region
				+ ", regionName=" + regionName + ", city=" + city + ", postalCode=" + postalCode + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName
	 *            the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longtitude
	 *            the longtitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
