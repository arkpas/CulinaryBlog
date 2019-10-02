package arkpas.culinaryblog.unitTests.serviceUnitTests;

import arkpas.culinaryblog.domain.repository.UserRepository;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.service.AuthorityService;
import arkpas.culinaryblog.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorityService authorityService;

    private UserService userService;

    @Before
    public void setup () {
        userService = new UserService(userRepository, authorityService);
    }

    //addUser method tests

    @Test
    public void addUserShouldReturnMessageWhenUsernameIsTaken () {
        String username = "usernameTaken";
        User user = new User();
        user.setUsername(username);

        doReturn(user).when(userRepository).getUser(username);
        assertNotNull(userService.addUser(username, "pass", "pass"));

    }

    @Test
    public void addUserShouldReturnMessageWhenPasswordsDoNotMatch () {
        List<User> usersSavedToDatabase = new ArrayList<>();
        doReturn(null).when(userRepository).getUser(anyString());

        assertNotNull(userService.addUser("my passwords do not match :(", "pass1", "pass2"));

    }

    @Test
    public void addUserShouldAddUserToDatabaseOnSuccess () {
        List<User> usersSavedToDatabase = new ArrayList<>();
        doReturn(null).when(userRepository).getUser(anyString());
        doReturn(null).when(authorityService).addAuthority(anyString(), anyString());
        doAnswer(answer -> {
            usersSavedToDatabase.add(answer.getArgument(0));
            return answer;
        }).when(userRepository).saveUser(any(User.class));

        userService.addUser("user", "pass", "pass");
        assertEquals(1, usersSavedToDatabase.size());

    }

    @Test
    public void addUserShouldCallAuthorityServiceMethodToCreateAuthority () {
        List<User> usersSavedToDatabase = new ArrayList<>();
        doReturn(null).when(userRepository).getUser(anyString());
        doNothing().when(userRepository).saveUser(any(User.class));
        doReturn(null).when(authorityService).addAuthority(anyString(), anyString());

        String username = "user";
        String password = "pass";
        String authority = "user";
        userService.addUser(username, password, password);

        verify(authorityService, times(1)).addAuthority(username, authority);

    }

    @Test
    public void addUserShouldReturnNullOnSuccess () {
        List<User> usersSavedToDatabase = new ArrayList<>();
        doReturn(null).when(userRepository).getUser(anyString());
        doNothing().when(userRepository).saveUser(any(User.class));
        doReturn(null).when(authorityService).addAuthority(anyString(), anyString());

        assertNull(userService.addUser("success", "pass", "pass"));

    }
}
