package ae.eventsbusinessassignment4.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * A class to act as a composite primary key for the Organizer class
 * 
 * @author Albin Engström
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		
		//Check if both's OrganizerId fields are equal
		if(this.getEventId() == ((OrganizerId) obj).getEventId()
				&& this.getUserId() == ((OrganizerId) obj).getUserId()){
			
			return true;
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
		
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getEventId());
        hcb.append(this.getUserId());
        return hcb.toHashCode();
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
}