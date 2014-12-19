package ae.eventsbusinessassignment4.beans;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Handles counting calls to show different pages
 */
@Singleton
@LocalBean
public class CallCounterBean {

    /**
     * Default constructor. 
     */
    public CallCounterBean() {
        
    }

}
