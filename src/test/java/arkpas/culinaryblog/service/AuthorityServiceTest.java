package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Authority;
import arkpas.culinaryblog.domain.repository.AuthorityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void addAuthority() {
        String username = "sampleUsername";
        String authorityName = "sampleAuthorityName";

        Authority authority = authorityService.addAuthority(username, authorityName);

        //check if authority consists of the same data
        assertEquals(username, authority.getUsername());
        assertEquals(authorityName, authority.getName());
    }



}
