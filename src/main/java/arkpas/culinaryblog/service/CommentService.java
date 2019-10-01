package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.UserDetails;
import arkpas.culinaryblog.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private RecipeService recipeService;
    private UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, RecipeService recipeService, UserService userService) {
        this.commentRepository = commentRepository;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    public Comment addComment (int recipeId, Comment comment) {
        User user = userService.getCurrentUser();
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (user != null && recipe != null && comment != null) {
            UserDetails userDetails = user.getUserDetails();
            if (userDetails != null) {
                recipe.addComment(comment);
                userDetails.addComment(comment);
                commentRepository.saveComment(comment);
                return comment;
            }
        }
        return null;
    }


}
