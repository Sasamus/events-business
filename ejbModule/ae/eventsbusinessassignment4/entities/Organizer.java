package ae.eventsbusinessassignment4.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * An Organizers entity used by a domain model
 * 
 * @author Albin Engström
 */
@Entity(name = "Organizers")
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
	 * Hold an Event object as a foreign key
	 */
	@Id
	private Event event;

	/**
	 * Hold an User object as a foreign key
	 */
	@Id
	private User user;

	/**
	 * @return the event
	 */
	@OneToOne
	@JoinColumn(table = "Events", referencedColumnName = "ID")
	public Event getEvent() {
		return event;
	}

	/**
	 * @return the user
	 */
	@OneToOne
	@JoinColumn(table = "Users", referencedColumnName = "ID")
	public User getUser() {
		return user;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
