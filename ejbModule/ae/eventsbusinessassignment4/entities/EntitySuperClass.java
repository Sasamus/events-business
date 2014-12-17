package ae.eventsbusinessassignment4.entities;

import java.io.Serializable;

/**
 * @author Albin Engström
 * @since
 */
@SuppressWarnings("serial")
abstract public class EntitySuperClass implements Serializable {

	/**
	 * Standard constructor
	 */
	public EntitySuperClass() {

	}

	/**
	 * An int to hold the the id
	 */
	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
