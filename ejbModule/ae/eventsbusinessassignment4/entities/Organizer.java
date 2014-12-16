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
 * @author Albin Engström
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
	 * Constructor
	 * 
	 * @param event
	 *            the Event
	 * @param user
	 *            the User
	 */
	public Organizer(Event event, User user) {
		this.event = event;
		this.user = user;
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
	@JoinColumn(name = "Events", table = "Events", referencedColumnName = "ID")
	@MapsId
	public Event getEvent() {
		return event;
	}

	/**
	 * @return the user
	 */
	@OneToOne
	@JoinColumn(name = "Users", table = "Users", referencedColumnName = "ID")
	@MapsId
	public User getUser() {
		return user;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}
}
