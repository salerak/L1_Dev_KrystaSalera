package ca.krystasalera.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Post {

	//Configuration of post
	@Id
	@NotNull
	@Size(max=64)
	@Column(name="uid",nullable=false,updatable=false)
	private int uid; //the unique identifier for each record, cannot be null
	
	@Size(max=64)
	@Column(name="parentId",nullable=true,updatable=false)
	private int parentId; //the UID of the parent to this object, NULL for the top level of the tree, can be null
	
	@Size(max=64)
	@Column(name="displayOrder",nullable=true,updatable=false)
	private int displayOrder; //the order to display all records,can be null
	
	@Size(max=64)
	@Column(name="indentLevel",nullable=true,updatable=false)
	private int indentLevel; //how much to indent this item,can be null
	
	//Information about post
	@NotNull
	@Size(max=64)
	@Column(name="content",nullable=false,updatable=true)
	private String content; // data of specific post,cannot be null
	
	@NotNull
	@Size(max=64)
	@Column(name="subject",nullable=false,updatable=true)
	private String subject; //thread of post, all post with same subject is in one thread,cannot be null
	
	@NotNull
	@Size(max=64)
	@Column(name="created",nullable=false,updatable=true)
	private Date created; //date of post,cannot be null
	
	@NotNull
	@Size(max=64)
	@Column(name="userAcctName",nullable=false,updatable=true)
	private String userAcctName; //user of post,cannot be null
	
	
	

	/* (non-Javadoc)
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
	 */
	public Post(int uid, int parentId, int displayOrder, int indentLevel, String content, String subject, Date created,
			String userAcctName) {
		super();
		this.uid = uid;
		this.parentId = parentId;
		this.displayOrder = displayOrder;
		this.indentLevel = indentLevel;
		this.content = content;
		this.subject = subject;
		this.created = created;
		this.userAcctName = userAcctName;
	}




	public Post() {
		// TODO Auto-generated constructor stub
	}




	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}




	/**
	 * @param uid the uid to set
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
	 * @param parentId the parentId to set
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
	 * @param displayOrder the displayOrder to set
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
	 * @param indentLevel the indentLevel to set
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
	 * @param content the content to set
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
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}




	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}




	/**
	 * @param created the created to set
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
	 * @param userAcctName the userAcctName to set
	 */
	public void setUserAcctName(String userAcctName) {
		this.userAcctName = userAcctName;
	}


	

}
