package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Comment;

public interface CommentRepository {

    public Comment getComment (int id);
    public void saveComment(Comment comment);
    public void updateComment (Comment comment);
}
