package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public User getUser (String username) {
        return userRepository.getUser(username);
    }

    public User getCurrentUser () {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return null;
        }
        else {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            return getUser(name);
        }

    }

    //returns null when user is successfully created
    public String addUser (String username, String password, String passwordRepeat) {
      return addUser(username, password, passwordRepeat, "user");
    }

    public String addUser (String username, String password, String passwordRepeat, String authority) {
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
            authorityService.addAuthority(username, authority);
            return null;
        }
        else {
            return "Wprowadzone hasła nie są identyczne!";
        }
    }
}
