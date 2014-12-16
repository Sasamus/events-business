package ae.eventsbusinessassignment4.databasemanaging;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ae.eventsbusinessassignment4.entities.Comment;
import ae.eventsbusinessassignment4.entities.Event;
import ae.eventsbusinessassignment4.entities.Organizer;
import ae.eventsbusinessassignment4.entities.OrganizerId;
import ae.eventsbusinessassignment4.entities.User;

/**
 * Manages the database
 * 
 * @author Albin Engström
 */
@Local(DatabaseManager.class)
@Stateless(name="DatabaseManagerBean")
public class DatabaseManagerBean implements DatabaseManager {

	/**
	 *  An EntityManager
	 */
	@PersistenceContext(unitName="EventManagement_alen1200") 
	private EntityManager entityManager;
	
	/**
	 * Read data from events.txt and adds it to the database
	 */
	@Override
	public void readData() {
		
		//A vector for User objects
		Vector<User> userVector = new Vector<User>();
		

		//A vector for Event objects
		Vector<Event> eventVector = new Vector<Event>();
		

		//A vector for Organizer objects
		Vector<Organizer> organizerVector = new Vector<Organizer>();
		

		//A vector for Comment objects
		Vector<Comment> commentVector = new Vector<Comment>();
		
		//Create a scanner variable
		Scanner scanner = null;
		
		InputStream inputStream = DatabaseManagerBean.class.getResourceAsStream("/Resources/events.txt"); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		//Create a Scanner object for the scanner variable
		scanner = new Scanner(bufferedReader);
		
		//A String to hold the scanned line
		String line = "";		
	
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if(line.equals("Users:")){
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					if(line.isEmpty()){
						break;
					} else {
						//We have a line with a user
						
						//Create a new user object
						User user = new User();
						
						//Separate the first and last name
						String[] nameParts = line.split(" ");
						
						//Set user's first name
						user.setFirstName(nameParts[0].trim());
						
						//Set user's last name
						user.setLastName(nameParts[1].trim());
						
						//Set user's picture field with a "missing picture" image-----------------------------------------------
						
						//Get the image as an InputStream
						InputStream imageInputStream = DatabaseManagerBean.class.getResourceAsStream("/Resources/MissingPicture.jpg"); 
						
						//A ByteArrayOutputStream to convert the stream to a byte array
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						
						
						try {
							
							//Get the first byte
							int next = imageInputStream.read();
							
							//Read until the stream is empty
							while (next > -1) {
								
								//Write next to byteArrayOutputStream
							    byteArrayOutputStream.write(next);
							    
							    //Get next byte
							    next = imageInputStream.read();
							}
							
							//Flush stream
							byteArrayOutputStream.flush();
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						//Convert the stream to an byte array
						byte[] bytes = byteArrayOutputStream.toByteArray();
						
						//Set user's picture field
						user.setPicture(bytes);
						
						//Set user's picture field with a "missing picture" image-----------------------------------------------
						
						//Add the user to the userVector
						userVector.add(user);		
					}
				}
			}
			else if(line.equals("Events:")){
				
				//Variable to hold the Event data being read
				Event readEvent = null;
				
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					if(line.isEmpty()){
						break;
					} else {
						
						//Create a scanner for line
						Scanner stringScanner = new Scanner(line);
						
						//Check if line hold event data, it does if it doesn't start with a tab
						if(!line.startsWith("\t")){
							
							//Create a new Event object
							Event event = new Event();
							
							//Read and set eventTitle
							event.setEventTitle(stringScanner.useDelimiter(",").next().trim());
							
							//Read and set eventCity
							event.setEventCity(stringScanner.useDelimiter(",").next().trim());
							
							//Read and set eventDescription
							event.setEventDescription(stringScanner.useDelimiter(",").next().trim());
							
							//Read the start and end times
							String tmpLine = stringScanner.useDelimiter(",").next().trim();
							
							//Separate the start and end times
							String[] timeParts = tmpLine.split(" - ");
							
							//Set eventStart
							event.setEventStart(Timestamp.valueOf("20" 
									+ timeParts[0].replace("/", "-").trim() + ":00"));
							
							//Set eventEnd
							event.setEventEnd(Timestamp.valueOf("20" 
									+ timeParts[1].replace("/", "-").trim() + ":00"));
							
							//Set lastUpdate
							event.setLastUpdate(new Timestamp(new Date().getTime()));
							
							//Add event to eventVector
							eventVector.add(event);
							
							//Set readEvent
							readEvent = event;
							
							//Read the organizers
							tmpLine = stringScanner.useDelimiter("[|]").next().trim();
							
							//Remove "[" and "]" from tmpLine
							tmpLine = tmpLine.replace("[", "");
							tmpLine = tmpLine.replace("]", "");
							
							//Remove first "," from tmpLine
							tmpLine = tmpLine.replaceFirst(",", "").trim();
							
							//Get each of the organizers
							String[] organizers = tmpLine.split(",");		
							
							//Create Organizer objects for each of the organizers
							for(int i=0; i < organizers.length; i++){		
								
								//Trim the organizers
								organizers[i] = organizers[i].trim();
								
								
								
								//Variable to check if organizers[i] is a user
								boolean isUser = false;
								
								//Iterate through userVector
								for(User user : userVector){
									
									//Check if the user's name matches the organizer's name
									if((user.getFirstName() + " " + user.getLastName()).equals(organizers[i])){
										
										//Create a Organizer
										Organizer organizer = new Organizer();
										
										//Create a OrganizerId
										OrganizerId organizerId = new OrganizerId();
										
										//Set organizer's organizerId field
										organizer.setId(organizerId);
										
										//Set Organizer's member variable event
										organizer.setEvent(event);
										
										//Set Organizer's member variable user
										organizer.setUser(user);
										
										//Add organizer to organizerVector
										organizerVector.add(organizer);
										
										//Set isUser to true
										isUser = true;
									}
								}
								
								//If isUser is false, print error and break
								if(!isUser){
									System.out.println("Organizer: " + organizers[i] 
											+ " isn't a user and wont be added to the event");
									
									break;
								}	
								
								
							}
							
							//Close stringScanner
							stringScanner.close();
						}
						else{ //The line holds comment data
							
							//Variables to hold the data
							User commenter = null;
							String submissionTime;
							String commentString;
							
							//Get user's name
							String firstName = stringScanner.useDelimiter("\t| ").next().trim();
							String lastName = stringScanner.useDelimiter(" ").next().trim();
							
							//Variable to check if organizers[i] is a user
							boolean isUser = false;
							
							//Iterate through userVector
							for(User user : userVector){
								
								//Check if the user's name matches the read name
								if((user.getFirstName() + " " + user.getLastName())
										.equals(firstName + " " + lastName)){
									
									//Set commenter to user
									commenter = user;
									
									//Set isUser to true
									isUser = true;
								}
							}
							
							//If isUser is false, print error and break
							if(!isUser){
								System.out.println("Commenter: " + firstName + " " + lastName 
										+ " isn't a user and the comment wont be added");
								
								break;
							}
							
							//Get comments submission time
							submissionTime = stringScanner.useDelimiter(" \\(|\\):").next().trim();

							//Remove "(" and ")" from submissionTime
							submissionTime = submissionTime.replace("(", "");
							submissionTime = submissionTime.replace(")", "");
							
							//Modify submissionTime to be convertible to a Timestamp
							submissionTime = "20" + submissionTime.replace("/", "-").trim() + ":00";
							
							//Set the Event's lastUpdate field to submissionTime
							eventVector.lastElement().setLastUpdate(Timestamp.valueOf(submissionTime));
							
							//Read and put string in commentString
							commentString = stringScanner.useDelimiter("^ | $").next().trim();
							
							//Remove ")" and ":" from commentString
							commentString = commentString.replace(")", "");
							commentString = commentString.replace(":", "");
							
							//Trim commentString
							commentString = commentString.trim();
							
							//Remove " from commentString
							commentString = commentString.replace("\"", "");
							
							//Create a comment object
							Comment comment = new Comment(readEvent, commenter, commentString,
									Timestamp.valueOf(submissionTime), Timestamp.valueOf(submissionTime));
							
							//Adds comment to commentVector
							commentVector.add(comment);
							
						}
					}
				}
			}	
		}
		
		//Close scanner
		scanner.close();
		
		//Sets all the Users in the userVector to persist
		for(User user : userVector){
			entityManager.persist(user);
		}

		//Sets all the Events in the eventsVector to persist
		for(Event event : eventVector){
			entityManager.persist(event);
		}
		
		//Sets all the Comments in the commentVector to persist
		for(Comment comment : commentVector){
			entityManager.persist(comment);
		}
		
		//Sets all the Organizers in the organizerVector to persist
		for(Organizer organizer : organizerVector){
			entityManager.persist(organizer);
		}
	}
}
