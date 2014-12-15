package ae.eventsbusinessassignment4.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * An Organizers entity used by a domain model
 * 
 * @author Albin Engström
 * @since 2014-11-09
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
	 * An OrganizerId object to hold the primary key
	 */
	@EmbeddedId
	OrganizerId id;

	/**
	 * @return the id
	 */
	public OrganizerId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(OrganizerId id) {
		this.id = id;
	}
	
	/**
	 * Hold an Event object as a foreign key
	 */
	@OneToOne
	@JoinColumn(table="Events", referencedColumnName="ID")
	@MapsId("eventId")
    private Event event;
	
	/**
	 * Hold an User object as a foreign key
	 */
	@OneToOne
	@JoinColumn(table="Users", referencedColumnName="ID")
	@MapsId("userId")
    private User user;

	/**
	 * @return the event
	 */
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
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
}

