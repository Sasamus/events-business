package ae.eventsbusinessassignment4.databasemanaging;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 * Manages the database
 * 
 * @author Albin Engström
 */
@Stateless
public class DatabaseManager {
	
	/**
	 * An EntitymanagerFactory
	 */
	@PersistenceUnit(unitName="EventManagement_alen1200")
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("events");
	
	/**
	 *  An EntityManager
	 */
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();

}
