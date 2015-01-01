package ae.eventsbusiness.beans;

import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;

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

		// TODO: Set calendar to public?

		// Set Summary and Location to location
		calendar.setSummary(location);
		calendar.setLocation(location);

		// Set time zone
		calendar.setTimeZone("Europe/Stockholm");

		// Insert calendar to calendarService
		calendarService.calendars().insert(calendar).execute();
	}

	public String getCalendarId(String location) throws IOException {

		// Get a CalendarList
		com.google.api.services.calendar.model.CalendarList calendarList = getCalendarList();

		// Get items in calendarList
		List<CalendarListEntry> items = calendarList.getItems();

		// Return the id of the CalendarListEntry with Locations being location
		for (CalendarListEntry calendarListEntry : items) {
			if (calendarListEntry.getLocation().equals(location)) {
				return calendarListEntry.getId();
			}
		}

		// Return null
		return null;
	}

	/**
	 * Lists all Calendars
	 * 
	 * @throws IOException
	 */
	public void listAllCalendars() throws IOException {

		// Get a CalendarList
		com.google.api.services.calendar.model.CalendarList calendarList = getCalendarList();

		// TODO: Adapt to what it should actually do, below is just for testing
		System.out.println("Debug:");
		for (CalendarListEntry calendarListEntry : calendarList.getItems())
			System.out.println(calendarListEntry.getSummary());
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
