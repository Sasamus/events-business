package ae.eventsbusinessassignment4.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An Events entity used by a domain model
 * 
 * @author Albin Engström
 */
@Entity
@Table(name="Events")
public class Event extends EntitySuperClass {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 3927254114998417555L;
	
	/**
	 * An int to hold the the id 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")
	private int id;
	
	/**
	 * A string to hold the title
	 */
	@Column(name="Title")
	private String eventTitle;
	

	/**
	 * A string to hold the city
	 */
	@Column(name="City")
	private String eventCity;
	
	/**
	 * A timestamp to hold the start
	 */
	@Column(name="StartTime")
	private Timestamp eventStart;
	
	/**
	 * A timestamp to hold the end
	 */
	@Column(name="EndTime")
	private Timestamp eventEnd;
	
	/**
	 * A timestamp to hold the last update
	 */
	@Column(name="LastUpdate")
	private Timestamp lastUpdate;
	
	/**
	 * A String to hold the event description
	 */
	@Column(name="Content")
	private String eventDescription;

	/**
	 * @return the eventTitle
	 */
	public String getEventTitle() {
		return eventTitle;
	}

	/**
	 * @param eventTitle the eventTitle to set
	 */
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	/**
	 * @return the eventCity
	 */
	public String getEventCity() {
		return eventCity;
	}

	/**
	 * @param eventCity the eventCity to set
	 */
	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}

	/**
	 * @return the eventStart
	 */
	public Timestamp getEventStart() {
		return eventStart;
	}

	/**
	 * @param eventStart the eventStart to set
	 */
	public void setEventStart(Timestamp eventStart) {
		this.eventStart = eventStart;
	}

	/**
	 * @return the eventEnd
	 */
	public Timestamp getEventEnd() {
		return eventEnd;
	}

	/**
	 * @param eventEnd the eventEnd to set
	 */
	public void setEventEnd(Timestamp eventEnd) {
		this.eventEnd = eventEnd;
	}

	/**
	 * @return the lastUpdate
	 */
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * @param eventDescription the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
