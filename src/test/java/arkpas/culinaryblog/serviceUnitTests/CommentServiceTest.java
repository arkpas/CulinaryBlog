package arkpas.culinaryblog.serviceUnitTests;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.CommentRepository;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.service.CommentService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    public void addCommentShouldReturnNullWhenArgumentIsNull () {
        doReturn(new User()).when(userService).getCurrentUser();
        Mockito.doReturn(new Recipe()).when(recipeService).getRecipe(anyInt());

        assertNull(commentService.addComment(1, null));
    }

    @Test
    public void addCommentShouldReturnNullIfRecipeDoesNotExist () {
        doReturn(new User()).when(userService).getCurrentUser();
        doReturn(null).when(recipeService).getRecipe(anyInt());

        assertNull(commentService.addComment(1, new Comment()));
    }

    @Test
    public void addCommentShouldReturnNullIfUserIsNull () {
        doReturn(null).when(userService).getCurrentUser();
        doReturn(new Recipe()).when(recipeService).getRecipe(anyInt());

        assertNull(commentService.addComment(1, new Comment()));
    }

    @Test
    public void addCommentShouldReturnNullIfUserHasNoUserDetails () {
        User user = new User();
        user.setUserDetails(null);
        doReturn(user).when(userService).getCurrentUser();
        doReturn(new Recipe()).when(recipeService).getRecipe(anyInt());

        assertNull(commentService.addComment(1, new Comment()));
    }

    @Test
    public void addCommentShouldAddElementToSetInRecipe () {
        User user = new User();
        doReturn(user).when(userService).getCurrentUser();

        Recipe recipe = new Recipe();
        doReturn(recipe).when(recipeService).getRecipe(anyInt());

        commentService.addComment(1, new Comment());

        assertEquals(1, recipe.getComments().size());
    }

    @Test
    public void addCommentShouldAddElementToSetInUserDetails () {
        User user = new User();
        doReturn(user).when(userService).getCurrentUser();

        Recipe recipe = new Recipe();
        doReturn(recipe).when(recipeService).getRecipe(anyInt());

        commentService.addComment(1, new Comment());

        assertEquals(1, user.getUserDetails().getComments().size());
    }

    @Test
    public void addCommentShouldReturnCommentIfSuccess () {
        User user = new User();
        doReturn(user).when(userService).getCurrentUser();

        Recipe recipe = new Recipe();
        doReturn(recipe).when(recipeService).getRecipe(anyInt());

        Comment comment = new Comment();

        assertEquals(comment, commentService.addComment(1, comment));
    }

}
