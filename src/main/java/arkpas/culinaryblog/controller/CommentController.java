package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/przepis/dodajKomentarz", method = RequestMethod.POST)
    public String addComment (int recipeId, String recipeName, @Valid Comment comment, BindingResult result) {
        if (result.hasErrors())
            return "redirect:/przepis/" + recipeName;

        commentService.addComment(recipeId, comment);
        return "redirect:/przepis/" + recipeName;
    }
}
