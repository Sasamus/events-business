package ae.eventsbusiness.beans;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ae.eventsbusiness.entities.Event;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.AclRule.Scope;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.EventDateTime;

/**
 * Manages the Calendars
 */
@Stateless(name = "CalendarManagerBean")
@LocalBean
public class CalendarManagerBean {

	/**
	 * A Calendar Service
	 */
	private Calendar calendarService = null;

	/**
	 * The Client Id
	 */
	private final String CLIENT_ID = "745694979823-hqnj881jlqb0tmfllj0bn8t2bnlo7bhc.apps.googleusercontent.com";

	/**
	 * The Clients Secret
	 */
	private final String CLIENT_SECRET = "Oavf0Mdlrn0RWMMGghqdo_EU";

	/**
	 * The Refresh Token
	 */
	private final String REFRESH_TOKEN = "1/BnfBDYb17Qz2RjZ157BCFsQrkmcE6kTRSKsuyCwf_msMEudVrK5jSpoR30zcRFq6";

	/**
	 * A NetHttpTransport variable
	 */
	private final NetHttpTransport httpTransport;

	/**
	 * A JacksonFactory variable
	 */
	private final JacksonFactory jsonFactory;

	/**
	 * Default constructor.
	 */
	public CalendarManagerBean() {

		// Create a NetHttpTransport object
		httpTransport = new NetHttpTransport();

		// Create a JacksonFactory object
		jsonFactory = new JacksonFactory();

		// Initialize calendarService
		calendarService = initCalendarService();
	}

	/**
	 * Initializes calendarService
	 * 
	 * @return A Calendar object
	 */
	private Calendar initCalendarService() {

		// Create a GoogleCredential object
		GoogleCredential credential = new GoogleCredential.Builder()
				.setClientSecrets(CLIENT_ID, CLIENT_SECRET)
				.setJsonFactory(jsonFactory).setTransport(httpTransport)
				.build().setRefreshToken(REFRESH_TOKEN);

		// Create and return a Calendar object
		return new Calendar.Builder(httpTransport, jsonFactory, credential)
				.build();
	}

	/**
	 * Create a model.Calendar object
	 * 
	 * @throws IOException
	 */
	public void createCalendar(String location) throws IOException {

		// Initialize calendar
		com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();

		// Set Summary and Location to location
		calendar.setSummary(location);
		calendar.setLocation(location);

		// Set time zone
		calendar.setTimeZone("UTC");

		// Insert calendar to calendarService
		calendarService.calendars().insert(calendar).execute();

		// Create AclRule
		AclRule rule = new AclRule();

		// Create Scope
		Scope scope = new Scope();

		// Set scope to default, a public scope. Value to "", since no specific
		// user or group is specified
		scope.setType("default").setValue("");

		// Set rule with scope and Role reader
		rule.setScope(scope).setRole("reader");

		// Insert rule
		calendarService.acl().insert(getCalendarId(location), rule).execute();

	}

	public void createEvent(Event event) throws IOException {

		// Create an calendar Event
		com.google.api.services.calendar.model.Event calendarEvent = new com.google.api.services.calendar.model.Event();

		// Set Summary
		calendarEvent.setSummary(event.getEventTitle());

		// Create Date objects initialized to the start and end dates
		Date startDate = new Date(event.getEventStart().getTime());
		Date endDate = new Date(event.getEventEnd().getTime());

		// Create and initialize a DateTime object for the start of the event
		DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
		calendarEvent.setStart(new EventDateTime().setDateTime(start));

		// Create and initialize a DateTime object for the end of the event
		DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
		calendarEvent.setEnd(new EventDateTime().setDateTime(end));

		// Insert calendarEvent to the calendar
		calendarService.events()
				.insert(getCalendarId(event.getEventCity()), calendarEvent)
				.execute();
	}

	/**
	 * Get a Calendar Id
	 * 
	 * @param location
	 *            the location the Calendar represents
	 * @return the id of the Calendar for location
	 * @throws IOException
	 */
	public String getCalendarId(String location) throws IOException {

		// Get a CalendarList
		com.google.api.services.calendar.model.CalendarList calendarList = getCalendarList();

		// Get items in calendarList
		List<CalendarListEntry> items = calendarList.getItems();

		// Return the id of the CalendarListEntry with Locations being location
		for (CalendarListEntry calendarListEntry : items) {
			if (calendarListEntry.getSummary().equals(location)) {
				return calendarListEntry.getId();
			}
		}

		// Return null
		return null;
	}

	/**
	 * Deletes all Calendars
	 * 
	 * @throws IOException
	 */
	public void deleteAllCalendars() throws IOException {

		// Get a CalendarList
		com.google.api.services.calendar.model.CalendarList calendarList = getCalendarList();

		// Iterate through all Ids in calendarIds
		for (CalendarListEntry calendarListEntry : calendarList.getItems()) {

			// Delete the CalendarListEntry
			calendarService.calendars().delete(calendarListEntry.getId())
					.execute();
		}
	}

	/**
	 * Gets a CalendarList from calendarService
	 * 
	 * @return a CalendarList
	 * @throws IOException
	 */
	private com.google.api.services.calendar.model.CalendarList getCalendarList()
			throws IOException {

		// Get and return a CalendarList
		return calendarService.calendarList().list().execute();
	}
}
