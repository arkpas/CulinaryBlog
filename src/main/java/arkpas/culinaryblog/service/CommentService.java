package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.UserDetails;
import arkpas.culinaryblog.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Comment getComment (int id) {
        return commentRepository.getComment(id);
    }

    public void updateComment (Comment comment) {
        commentRepository.updateComment(comment);
    }

    public void saveComment (Comment comment) { commentRepository.saveComment(comment); }

    public void addComment (int recipeId, Comment comment) {
        User user = userService.getCurrentUser();
        if (user != null) {
            UserDetails userDetails = user.getUserDetails();
            Recipe recipe = recipeService.getRecipe(recipeId);

            recipe.addComment(comment);
            userDetails.addComment(comment);

            commentRepository.saveComment(comment);
        }
    }

    public void deleteComment (Comment comment) { commentRepository.removeComment(comment); }

}
