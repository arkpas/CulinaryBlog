package arkpas.culinaryblog.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserTest {

    private User user;

    @Before
    public void setup () {
        user = new User();
    }

    @Test
    public void setUserDetails () {

        //check for nullpointerexception
        user.setUserDetails(null);

        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);

        //both objects should have set references to each other
        assertEquals(userDetails, user.getUserDetails());
        assertEquals(user, userDetails.getUser());

        user.setUserDetails(null);

        //references in both objects should be nulified
        assertNull(user.getUserDetails());
        assertNull(userDetails.getUser());


    }

}
