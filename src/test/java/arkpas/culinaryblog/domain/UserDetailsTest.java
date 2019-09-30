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


    //addComment method tests

    @Test
    public void addCommentShouldAddObjectToSet () {

        Comment comment = new Comment();
        userDetails.addComment(comment);
        assertTrue(userDetails.getComments().contains(comment));
    }

    @Test
    public void addCommentShouldNotAddNullObjectToSet () {
        userDetails.addComment(null);
        assertTrue(userDetails.getComments().isEmpty());
    }

    @Test
    public void addCommentShouldSetReferenceInArgumentObject () {
        Comment comment = new Comment();
        userDetails.addComment(comment);
        assertEquals(userDetails, comment.getUserDetails());
    }

   //collection getters tests

    @Test(expected = UnsupportedOperationException.class)
    public void getCommentsShouldReturnUnmodifiableSet () {
        userDetails.getComments().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getUserRatesShouldReturnUnmodifiableSet() {
        userDetails.getUserRates().clear();
    }

}
