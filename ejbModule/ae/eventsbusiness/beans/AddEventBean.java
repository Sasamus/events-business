package ae.eventsbusiness.beans;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import ae.eventsbusiness.entities.Event;
import ae.eventsbusiness.entities.User;

/**
 * Session Bean implementation class AddEventBean
 */
@Stateless
@LocalBean
public class AddEventBean {

	/**
	 * A DatabaseManagerBean object
	 */
	@EJB(beanName = "DatabaseManagerBean")
	private DatabaseManagerBean databaseManagerBean;

	/**
	 * Default constructor.
	 */
	public AddEventBean() {

	}

	public void addEvent(HttpServletRequest request) {

		// An Event object
		Event event = new Event();

		// Set it's values
		event.setEventTitle(request.getParameter("title"));
		event.setEventCity(request.getParameter("city"));
		event.setEventDescription(request.getParameter("description"));

		// Get times
		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");

		// Create Timestamps with the time Strings
		Timestamp timestampStart = Timestamp.valueOf(startTime);
		Timestamp timestampEnd = Timestamp.valueOf(endTime);

		// Create a Timestamp with the current time
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());

		// Set event's time related fields
		event.setEventStart(timestampStart);
		event.setEventEnd(timestampEnd);
		event.setLastUpdate(currentTimestamp);

		// Get Organizer
		String userId = request.getParameter("organizerId");

		// The User
		User user = databaseManagerBean.getUser(Integer.parseInt(userId));

		// Add Event
		databaseManagerBean.addEvent(event, user);

	}

}
