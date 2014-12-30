package ae.eventsbusiness.beans;

import java.io.IOException;
import java.util.Vector;

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
	 * Calendars
	 */
	private Calendar calendars = null;

	/**
	 * Calendar Ids
	 */
	private Vector<String> calendarIds = new Vector<String>();

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

		// Initialize calendars
		calendars = initCalendarService();
	}

	/**
	 * Initializes calendars
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
	public void createCalendar() throws IOException {

		// Initialize calendar
		com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();

		// TODO: Change to be dynamic, below is just for testing
		calendar.setSummary("Hamburg");
		calendar.setTimeZone("Europe/Stockholm");

		// Insert calendar to calendars
		com.google.api.services.calendar.model.Calendar createdCalendar = calendars
				.calendars().insert(calendar).execute();

		// Add the id of calendar to calendarIds
		calendarIds.add(createdCalendar.getId());
	}

	/**
	 * Lists all Calendars
	 * 
	 * @throws IOException
	 */
	public void listAllCalendars() throws IOException {

		// Initialize calendarList
		com.google.api.services.calendar.model.CalendarList calendarList = calendars
				.calendarList().list().execute();

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

		// Iterate through all Ids in calendarIds
		for (String id : calendarIds) {

			// Delete the Calendar with id
			calendars.calendars().delete(id).execute();
		}

	}
}
