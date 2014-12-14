package ae.eventsbusinessassignment4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * A Users entity used by a domain model
 * @author Albin Engström
 * @since 2014-11-09
 */
@Entity(name = "Users")
public class User extends EntitySuperClass {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 4439601980436248257L;
	
	/**
	 * An int to hold the the id 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")
	private int id;
	
	/**
	 * A string to hold the first name
	 */
	@Column(name="FirstName")
	private String firstName;
	

	/**
	 * A string to hold the last name
	 */
	@Column(name="LastName")
	private String lastName;
	
	/**
	 * Holds a picture
	 */
	@Lob
	@Column(name="Picture")
	private byte[] picture;
	
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the picture
	 */
	public byte[] getPicture() {
		return picture;
	}


	/**
	 * @param picture the picture to set
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
}
