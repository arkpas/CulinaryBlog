package arkpas.culinaryblog.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDetailsTest {

    private UserDetails userDetails;

    @Before
    public void setup () {
        userDetails = new UserDetails();
    }

    @Test
    public void addComment () {
        //set should be initially empty
        assertTrue(userDetails.getComments().isEmpty());

        Comment comment = null;
        userDetails.addComment(comment);

        //set should not save null elements
        assertTrue(userDetails.getComments().isEmpty());

        comment = new Comment();
        userDetails.addComment(comment);

        //set should have 1 element now
        assertEquals(1, userDetails.getComments().size());

        //added element should have reference to his parent
        assertEquals(userDetails, comment.getUserDetails());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkIfCommentsAreUnmodifiable() {
        userDetails.getComments().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkIfUserRatesAreUnmodifiable() {
        userDetails.getUserRates().clear();
    }

}
