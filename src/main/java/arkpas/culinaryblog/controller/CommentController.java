package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/przepis/dodajKomentarz", method = RequestMethod.POST)
    public String addComment (int recipeId, Comment comment) {

        commentService.addComment(recipeId, comment);

        return "redirect:/przepis/" + comment.getRecipe().getName();
    }
}
