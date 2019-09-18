package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.User;

public interface UserRepository {

    public User getUser (int userId);
    public User getUser (String username);
    public void saveUser (User user);
    public void updateUser (User user);
    public void removeUser (User user);

}
