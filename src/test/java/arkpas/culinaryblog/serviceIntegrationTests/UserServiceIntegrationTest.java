package arkpas.culinaryblog.serviceIntegrationTests;

import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.repository.UserRepository;
import arkpas.culinaryblog.service.AuthorityService;
import arkpas.culinaryblog.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AuthorityService authorityService;

    private UserService userService;

    @Before
    public void setup () {
        userService = new UserService(userRepository, authorityService);
    }

    private final String username = "user";

    @Test
    @WithMockUser(username = username)
    public void getCurrentUserShouldReturnUserObject () {
        User user = new User();
        user.setUsername(username);
        doReturn(user).when(userRepository).getUser(username);
        assertEquals(user, userService.getCurrentUser());
    }

    @Test
    @WithAnonymousUser
    public void getCurrentUserShouldReturnNull () {
        assertNull(userService.getCurrentUser());
    }
}
