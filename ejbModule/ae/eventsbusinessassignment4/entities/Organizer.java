package ae.eventsbusinessassignment4.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * An Organizers entity used by a domain model
 * 
 * @author Albin Engström b  
 */
@Entity(name = "Organizers")
@IdClass(OrganizerId.class)
public class Organizer implements Serializable {
	
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 7467784906483550844L;

	/**
	 * Standard constructor
	 */
	public Organizer() {

	}
	
	
	/**
	 * Holds the User Id
	 */
	@Id
	int userId;
	
	/**
	 * Holds the Event Id
	 */
	@Id
	int eventId;
	
	/**
	 * Hold an Event object as a foreign key
	 */
    private Event event;
	
	/**
	 * Hold an User object as a foreign key
	 */
    private User user;

	/**
	 * @return the event
	 */
	@OneToOne
	@JoinColumn(name="Events", table="Events", referencedColumnName="ID")
	@MapsId
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}


	/**
	 * @return the user
	 */
	@OneToOne
	@JoinColumn(name="Users", table="Users", referencedColumnName="ID")
	@MapsId
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	
}

