package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Comment;

import java.util.List;

public interface CommentRepository {

    public Comment getComment (int id);
    public List<Comment> getCommentsByRecipe (int recipeId);
    public void saveComment(Comment comment);
    public void updateComment (Comment comment);
    void removeComment(Comment comment);
}
