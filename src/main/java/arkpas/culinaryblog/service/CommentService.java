package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private RecipeService recipeService;

    @Autowired
    public CommentService(CommentRepository commentRepository, RecipeService recipeService) {
        this.commentRepository = commentRepository;
        this.recipeService = recipeService;
    }

    public Comment getComment (int id) {
        return commentRepository.getComment(id);
    }

    public void updateComment (Comment comment) {
        commentRepository.updateComment(comment);
    }

    public void addComment (int recipeId, Comment comment) {

        Recipe recipe = recipeService.getRecipe(recipeId);

        comment.setRecipe(recipe);
        recipe.addComment(comment);

        recipeService.updateRecipe(recipe);
        commentRepository.updateComment(comment);
    }
}