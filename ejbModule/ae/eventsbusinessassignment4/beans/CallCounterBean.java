package ae.eventsbusinessassignment4.beans;

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
	int eventsOverviewCallCounter = 0;

	/**
	 * Counts the calls to show UserProfile
	 */
	int userProfileCallCounter = 0;

	/**
	 * Counts the calls to show AddEvent
	 */
	int addEventCallCounter = 0;

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
	public synchronized void incrementUserProfileCallCounter() {
		userProfileCallCounter++;
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
	public int getUserProfileCallCounter() {
		return userProfileCallCounter;
	}

	/**
	 * @return the addEventCallCounter
	 */
	public int getAddEventCallCounter() {
		return addEventCallCounter;
	}

}
