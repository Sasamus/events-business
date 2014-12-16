package ae.eventsbusinessassignment4.databasemanaging;

import javax.ejb.Remote;




/**
 * @author Albin Engström
 */
@Remote
public interface DatabaseManager {
	
	/**
	 * Read data from events.txt and adds it to the database
	 */
	public void readData();
}

//TODO: Page Counter shared?