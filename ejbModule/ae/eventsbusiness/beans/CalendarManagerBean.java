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
	private Calendar calendarService = null;

	/**
	 * Calendar Ids
	 */
	private Vector<String> calendarIds = new Vector<String>();

	/**
	 * The Client Id
	 */
	private final String clientId;

	/**
	 * The Clients Secret
	 */
	private final String clientSecret;

	/**
	 * The Refresh Token
	 */
	private final String refreshToken;

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

		clientId = "745694979823-hqnj881jlqb0tmfllj0bn8t2bnlo7bhc.apps.googleusercontent.com";
		clientSecret = "Oavf0Mdlrn0RWMMGghqdo_EU";
		refreshToken = "1/BnfBDYb17Qz2RjZ157BCFsQrkmcE6kTRSKsuyCwf_msMEudVrK5jSpoR30zcRFq6";

		httpTransport = new NetHttpTransport();
		jsonFactory = new JacksonFactory();

		calendarService = initCalendarService();
	}

	private Calendar initCalendarService() {
		GoogleCredential credential = new GoogleCredential.Builder()
				.setClientSecrets(clientId, clientSecret)
				.setJsonFactory(jsonFactory).setTransport(httpTransport)
				.build().setRefreshToken(refreshToken);

		return new Calendar.Builder(httpTransport, jsonFactory, credential)
				.build();
	}

	public void createCalendar() throws IOException {

		com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();

		calendar.setSummary("testCalendar");
		calendar.setTimeZone("America/Los_Angeles");

		com.google.api.services.calendar.model.Calendar createdCalendar = calendarService
				.calendars().insert(calendar).execute();

		calendarIds.add(createdCalendar.getId());
	}

	public void listAllCalendars() throws IOException {
		com.google.api.services.calendar.model.CalendarList calendarList = calendarService.calendarList().list()
				.execute();

		System.out.println("Debug:");
		for (CalendarListEntry calendarListEntry : calendarList.getItems())
			System.out.println(calendarListEntry.getSummary());
	}

	public void deleteAllCalendars() throws IOException {
		for(String id : calendarIds){
			calendarService.calendars().delete(id).execute();
		}
		
	}
}
