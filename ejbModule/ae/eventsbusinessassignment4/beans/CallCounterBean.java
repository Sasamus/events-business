package ae.eventsbusinessassignment4.beans;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Handles counting calls to show different pages
 */
@Singleton(name = "CallCounterBean")
@LocalBean
public class CallCounterBean {

	/**
	 * Counts the calls to show EventsOverview
	 */
	private int eventsOverviewCallCounter = 0;

	/**
	 * Maps the call counter of each user to their Id
	 */
	private Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();

	/**
	 * Counts the calls to show AddEvent
	 */
	private int addEventCallCounter = 0;

	/**
	 * Default constructor.
	 */
	public CallCounterBean() {

	}

	/**
	 * Increments eventOverviewCallCounter
	 */
	public synchronized void incrementEventsOverviewCallCounter() {
		eventsOverviewCallCounter++;
	}

	/**
	 * Increments addEventsCallCounter
	 */
	public synchronized void incrementAddEventCallCounter() {
		addEventCallCounter++;
	}

	/**
	 * Increments userProfileCallCounter
	 */
	public synchronized void incrementUserProfileCallCounter(int id) {
		
		if (userMap.containsKey(id)) {
			int count = userMap.get(id);
			userMap.put(id, count + 1);
		} else {
			userMap.put(id, 1);
		}

	}

	/**
	 * @return the eventsOverviewCallCounter
	 */
	public int getEventsOverviewCallCounter() {
		return eventsOverviewCallCounter;
	}

	/**
	 * @return the userProfileCallCounter
	 */
	public int getUserProfileCallCounter(int id) {
		if(userMap.get(id) == null){
			return 0;
		}
		else{
			return userMap.get(id);
		}
	}

	/**
	 * @return the addEventCallCounter
	 */
	public int getAddEventCallCounter() {
		return addEventCallCounter;
	}

}
