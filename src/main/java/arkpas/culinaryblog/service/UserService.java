package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private AuthorityService authorityService;

    @Autowired
    public UserService(UserRepository userRepository, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    public User getUser (int userId) {
        return userRepository.getUser(userId);
    }

    public User getUser (String username) {
        return userRepository.getUser(username);
    }

    //returns null when user is successfully created
    public String addUser (String username, String password, String passwordRepeat) {
        if (this.getUser(username) != null)
            return "Nazwa użytkownika zajęta.";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        if (encoder.matches(passwordRepeat, password)) {
            passwordRepeat = null;
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userRepository.saveUser(user);
            authorityService.addAuthority(username, "blogger");
            return null;
        }
        else {
            return "Wprowadzone hasła nie są identyczne!";
        }

    }
    public void updateUser (User user) {
        userRepository.updateUser(user);
    }
    public void deleteUser (User user) {
        userRepository.removeUser(user);
    }

}