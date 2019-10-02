package arkpas.culinaryblog.unitTests.domainUnitTests;

import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.UserDetails;
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
    public void setUserDetailsShouldAssignObjectToField () {
        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);
        assertEquals(userDetails, user.getUserDetails());
    }

    @Test
    public void setUserDetailsShouldAssignReferenceInArgumentObject () {
        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);
        assertEquals(user, userDetails.getUser());
    }

    @Test
    public void setUserDetailsShouldRemoveReferenceFromOldObjectWhenNewObjectIsAssigned () {
        UserDetails oldObject = new UserDetails();
        user.setUserDetails(oldObject);
        user.setUserDetails(new UserDetails());

        assertNull(oldObject.getUser());
    }

    @Test
    public void setUserDetailsShouldRemoveReferenceFromOldObjectWhenNullIsAssigned () {
        UserDetails oldObject = new UserDetails();
        user.setUserDetails(oldObject);
        user.setUserDetails(null);

        assertNull(oldObject.getUser());
    }

    @Test
    public void setUserDetailsShouldNotThrowNullpointerExceptionWithNullArgument () {
        user.setUserDetails(null);
    }

}
