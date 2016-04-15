package ca.krystasalera.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostBuilder {

	private int uid = 100;

	private int parentId = 0;

	private int displayOrder = 1;

	private int indentLevel = 1;

	private String content = "Hello World";
	private String subject = "Hello";

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss zzz", timezone = "DEFAULT_TIMEZONE")
	private Date created = new Date();

	private String userAcctName = "Test Account";

	private String city = "Toronto";

	private String longtitude = "-79.42";

	private String latitude = "43.7";

	private String coordinates = "Latitude: 43.7,Longitude: -79.42";

	private String temperature = "7.4 C";

	public Post Build() {
		return new Post(uid, parentId, displayOrder, indentLevel, content, subject, created, userAcctName, city,
				longtitude, latitude, coordinates, temperature);
	}

	// /**
	// * @param uid the uid to set
	// */
	// public void withUid(int uid) {
	// this.uid = uid;
	// }
	//
	// /**
	// * @param parentId the parentId to set
	// */
	// public void withParentId(int parentId) {
	// this.parentId = parentId;
	// }
	//
	// /**
	// * @param displayOrder the displayOrder to set
	// */
	// public void withDisplayOrder(int displayOrder) {
	// this.displayOrder = displayOrder;
	// }
	//
	// /**
	// * @param indentLevel the indentLevel to set
	// */
	// public void withIndentLevel(int indentLevel) {
	// this.indentLevel = indentLevel;
	// }
	//
	// /**
	// * @param content the content to set
	// */
	// public void withContent(String content) {
	// this.content = content;
	// }
	//
	// /**
	// * @param subject the subject to set
	// */
	// public void withSubject(String subject) {
	// this.subject = subject;
	// }
	//
	// /**
	// * @param created the created to set
	// */
	// public void withCreated(Date created) {
	// this.created = created;
	// }
	//
	// /**
	// * @param userAcctName the userAcctName to set
	// */
	// public void withUserAcctName(String userAcctName) {
	// this.userAcctName = userAcctName;
	// }
	//
	// /**
	// * @param city the city to set
	// */
	// public void withCity(String city) {
	// this.city = city;
	// }
	//
	// /**
	// * @param longtitude the longtitude to set
	// */
	// public void withLongtitude(String longtitude) {
	// this.longtitude = longtitude;
	// }
	//
	// /**
	// * @param latitude the latitude to set
	// */
	// public void withLatitude(String latitude) {
	// this.latitude = latitude;
	// }
	//
	// /**
	// * @param coordinates the coordinates to set
	// */
	// public void withCoordinates(String coordinates) {
	// this.coordinates = coordinates;
	// }
	//
	// /**
	// * @param temperature the temperature to set
	// */
	// public void withTemperature(String temperature) {
	// this.temperature = temperature;
	// }

	//////// POST BUILDER/////////

	/**
	 * @param uid
	 *            the uid to set
	 */
	public PostBuilder withUid(int uid) {
		this.uid = uid;
		return this;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public PostBuilder withParentId(int parentId) {
		this.parentId = parentId;
		return this;
	}

	/**
	 * @param displayOrder
	 *            the displayOrder to set
	 */
	public PostBuilder withDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
		return this;
	}

	/**
	 * @param indentLevel
	 *            the indentLevel to set
	 */
	public PostBuilder withIndentLevel(int indentLevel) {
		this.indentLevel = indentLevel;
		return this;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public PostBuilder withContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public PostBuilder withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public PostBuilder withCreated(Date created) {
		this.created = created;
		return this;
	}

	/**
	 * @param userAcctName
	 *            the userAcctName to set
	 */
	public PostBuilder withUserAcctName(String userAcctName) {
		this.userAcctName = userAcctName;
		return this;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public PostBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	/**
	 * @param longtitude
	 *            the longtitude to set
	 */
	public PostBuilder withLongtitude(String longtitude) {
		this.longtitude = longtitude;
		return this;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public PostBuilder withLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public PostBuilder withCoordinates(String coordinates) {
		this.coordinates = coordinates;
		return this;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public PostBuilder withTemperature(String temperature) {
		this.temperature = temperature;
		return this;
	}

	public static final Post convertBuilderToPost(final PostBuilder instance) {
		return instance.Build();
	}

}
