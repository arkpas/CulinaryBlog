package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.User;

public interface UserRepository {

    User getUser (String username);
    void saveUser (User user);


}
