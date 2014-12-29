package ae.eventsbusiness.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * An Comments entity used by a domain model
 * 
 * @author Albin Engström
 */
@Entity(name = "Comments")
public class Comment implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 2840041667801027429L;

	/**
	 * @param event
	 *            The Event the comment is for
	 * @param user
	 *            The user than made the comment
	 * @param comment
	 *            The comment itself
	 * @param submissionTime
	 *            Time the comment was submitted
	 * @param lastUpdateTime
	 *            Time the comment was updated
	 */
	public Comment(Event event, User user, String comment,
			Timestamp submissionTime, Timestamp lastUpdateTime) {
		this.event = event;
		this.user = user;
		this.comment = comment;
		this.submissionTime = submissionTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @param event
	 *            The Event the comment is for
	 * @param user
	 *            The user than made the comment
	 * @param comment
	 *            the comment itself
	 */
	public Comment(Event event, User user, String comment) {
		this.event = event;
		this.user = user;
		this.comment = comment;
		this.submissionTime = new Timestamp(new Date().getTime());
		this.lastUpdateTime = new Timestamp(new Date().getTime());
	}

	/**
	 * Have to exists for dynamic enhancement to work, don't use
	 */
	protected Comment() {

	}

	/**
	 * An int to hold the the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	/**
	 * An Event object as a foreign key
	 */
	private Event event;

	/**
	 * A string to hold the comment
	 */
	@Column(name = "Comment")
	private String comment;

	/**
	 * A timestamp to hold the submission date/time
	 */
	@Column(name = "Time")
	private Timestamp submissionTime;

	/**
	 * A timestamp to hold the last edited date/time
	 */
	@Column(name = "lastUpdateTime")
	private Timestamp lastUpdateTime;

	/**
	 * An User object as a foreign key
	 */
	private User user;

	/**
	 * @return the event
	 */
	@ManyToOne
	@JoinColumn(name = "Events", table = "Events", referencedColumnName = "ID")
	public Event getEvent() {
		return event;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
		setLastUpdateTime(new Timestamp(new Date().getTime()));
	}

	/**
	 * @return the submissionTime
	 */
	public Timestamp getSubmissionTime() {
		return submissionTime;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param time
	 *            the most recent update time
	 */
	private void setLastUpdateTime(Timestamp time) {
		this.lastUpdateTime = time;
	}

	/**
	 * @return the user
	 */
	@ManyToOne
	@JoinColumn(name = "Users", table = "Users", referencedColumnName = "ID")
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}