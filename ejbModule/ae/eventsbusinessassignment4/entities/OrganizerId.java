package ae.eventsbusinessassignment4.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * A class to act as a composite primary key for the Organizer class
 * 
 * @author Albin Engström
 * @since 2014-11-09
 */
@Embeddable
public class OrganizerId implements Serializable { 
	
	/**
	 * Generated
	 */
	private static final long serialVersionUID = -7781868552034875838L;

	/**
	 * Standard constructor
	 */
	public OrganizerId() {

	}
	
	/**
	 * Hold an Event Id as a foreign key
	 */
    private int eventId;
    
	/**
	 * Hold an User Id as a foreign key
	 */
    private int userId;

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + userId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizerId other = (OrganizerId) obj;
		if (eventId != other.eventId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}