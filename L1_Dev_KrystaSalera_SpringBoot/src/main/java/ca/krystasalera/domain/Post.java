package ca.krystasalera.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@NamedQueries({ @NamedQuery(name = "Post.findAllPosts", query = "from Post order by created desc "),
		@NamedQuery(name = "Post.findById", query = " from Post p where p.uid= ?1")

})
public class Post {

	


	// Configuration of post
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)


	@Column(name = "uid")
	private int uid; // the unique identifier for each record, cannot be null

	@Column(name = "parentId", nullable = true, updatable = false)
	private int parentId; // the UID of the parent to this object, NULL for the
							// top level of the tree, can be null

	@Column(name = "displayOrder", nullable = true, updatable = false)
	private int displayOrder; // the order to display all records,can be null

	@Column(name = "indentLevel", nullable = true, updatable = false)
	private int indentLevel; // how much to indent this item,can be null

	// Information about post
	
	@Size(max = 64)
	@Column(name = "content", nullable = true, updatable = true)
	private String content; // data of specific post,cannot be null

	
	@Size(max = 64)
	@Column(name = "subject", nullable = true, updatable = true)
	private String subject; // thread of post, all post with same subject is in
							// one thread,cannot be null

	
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat (pattern="EEE, dd MMM yyyy HH:mm:ss zzz")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE, dd MMM yyyy HH:mm:ss zzz", timezone="DEFAULT_TIMEZONE")
	@Column(name = "created", nullable = true, updatable = true)
	private Date created; // date of post,cannot be null


	@Size(max = 64)
	@Column(name = "userAcctName", nullable = true, updatable = true)
	private String userAcctName; // user of post,cannot be null

	
	@Size(max = 64)
	@Column(name = "city", nullable = true, updatable = true)
	private String city; // location of post,cannot be null

	
	@Size(max = 64)
	@Column(name = "longtitude", nullable = true, updatable = true)
	private String longtitude; // location of post,cannot be null

	
	@Size(max = 64)
	@Column(name = "latitude", nullable = true, updatable = true)
	private String latitude; // location of post,cannot be null

	
	@Size(max = 64)
	@Column(name = "temperature", nullable = true, updatable = true)
	private String temperature; // location of post,cannot be null

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Post [uid=" + uid + ", parentId=" + parentId + ", displayOrder=" + displayOrder + ", indentLevel="
				+ indentLevel + ", content=" + content + ", subject=" + subject + ", created=" + created
				+ ", userAcctName=" + userAcctName + "]";
	}

	/**
	 * @param uid
	 * @param parentId
	 * @param displayOrder
	 * @param indentLevel
	 * @param content
	 * @param subject
	 * @param created
	 * @param userAcctName
	 * @param city
	 * @param longtitude
	 * @param latitude
	 * @param temperature
	 */
	public Post(int parentId, int displayOrder, int indentLevel, String content, String subject, Date created,
			String userAcctName, String city, String longtitude, String latitude, String temperature) {

		this.parentId = parentId;
		this.displayOrder = displayOrder;
		this.indentLevel = indentLevel;
		this.content = content;
		this.subject = subject;
		this.created = created;
		this.userAcctName = userAcctName;
		this.city = city;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.temperature = temperature;
	}

	public Post() {
		
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the displayOrder
	 */
	public int getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder
	 *            the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return the indentLevel
	 */
	public int getIndentLevel() {
		return indentLevel;
	}

	/**
	 * @param indentLevel
	 *            the indentLevel to set
	 */
	public void setIndentLevel(int indentLevel) {
		this.indentLevel = indentLevel;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the created
	 */
	public String getCreated() {
		DateFormat df= new SimpleDateFormat( "EEE, dd MMM yyyy HH:mm:ss zzz");
		return df.format(created);
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the userAcctName
	 */
	public String getUserAcctName() {
		return userAcctName;
	}

	/**
	 * @param userAcctName
	 *            the userAcctName to set
	 */
	public void setUserAcctName(String userAcctName) {
		this.userAcctName = userAcctName;
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
	 * @return the longtitude
	 */
	public String getLongtitude() {
		return longtitude;
	}

	/**
	 * @param longtitude
	 *            the longtitude to set
	 */
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
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
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
