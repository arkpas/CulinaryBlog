package arkpas.culinaryblog.unitTests.serviceUnitTests;

import arkpas.culinaryblog.domain.repository.AuthorityRepository;
import arkpas.culinaryblog.domain.Authority;
import arkpas.culinaryblog.service.AuthorityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceTest {

    @Mock
    private AuthorityRepository authorityRepository;

    private AuthorityService authorityService;

    @Before
    public void setup() {
        authorityService = new AuthorityService(authorityRepository);
        doAnswer(returnsFirstArg()).when(authorityRepository).saveAuthority(any(Authority.class));
    }

    @Test
    public void addAuthorityShouldReturnObjectWithUsernameSameAsArgument () {
        String username = "sampleUsername";
        Authority authority = authorityService.addAuthority(username, null);
        assertEquals(username, authority.getUsername());
    }

    @Test
    public void addAuthorityShouldReturnObjectWithAuthoritySameAsArgument () {
        String authorityName = "sampleAuthority";
        Authority authority = authorityService.addAuthority(null, authorityName);
        assertEquals(authorityName, authority.getName());
    }


}
