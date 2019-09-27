package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.UserDetails;
import arkpas.culinaryblog.domain.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private RecipeService recipeService;
    @Mock
    private UserService userService;

    private CommentService commentService;

    @Before
    public void setup () {
        commentService = new CommentService(commentRepository, recipeService, userService);
    }
    @Test
    public void addComment () {
        User user = new User();
        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);

        Recipe recipe = new Recipe();

        doReturn(user).when(userService).getCurrentUser();
        doReturn(recipe).when(recipeService).getRecipe(anyInt());
        doNothing().when(commentRepository).saveComment(any(Comment.class));

        Comment comment = new Comment();
        comment.setText("sample comment");

        Comment result = commentService.addComment(0, comment);

        //result should not be null
        assertNotNull(result);

        //comment should now have references to recipe and userDetails
        assertEquals(userDetails, comment.getUserDetails());
        assertEquals(recipe, comment.getRecipe());

        //comment should be added to collection in recipe object
        assertEquals(1, recipe.getComments().size());
        //comment should be added to collection in userDetails object
        assertEquals(1, userDetails.getComments().size());

    }

    @Test
    public void addCommentWithNulls () {
        User user = null;
        Recipe recipe = null;

        doReturn(user).when(userService).getCurrentUser();
        doReturn(recipe).when(recipeService).getRecipe(anyInt());

        Comment comment = null;

        Comment result = commentService.addComment(0, comment);
        //result should be null, because user, recipe and comment are null
        assertNull(result);

        recipe = new Recipe();
        result = commentService.addComment(0, comment);
        //result should still be null, because user and comment are null
        assertNull(result);

        comment = new Comment();
        result = commentService.addComment(0, comment);
        //result should still be null, because user is null
        assertNull(result);

        user = new User();
        result = commentService.addComment(0, comment);
        //result should still be null, because user has no userDetails set
        assertNull(result);

    }
}
