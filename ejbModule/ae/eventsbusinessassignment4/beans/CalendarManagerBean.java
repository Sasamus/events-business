package ae.eventsbusinessassignment4.beans;

import java.io.IOException;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.Calendar;

/**
 * Manages the Calendars
 */
@Singleton(name = "CalendarManagerBean")
@LocalBean
public class CalendarManagerBean {

	/**
	 * Calendars
	 */
	private Calendar calendarService = null;

	/**
	 * Calendar Ids
	 */
	private Vector<String> calendarIds;

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
	public CalendarManagerBean(String clientId, String clientSecret,
			String refreshToken) {

		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.refreshToken = refreshToken;

		httpTransport = new NetHttpTransport();
		jsonFactory = new JacksonFactory();

		calendarService = initCalendar();
	}

	private Calendar initCalendar() {
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

}
