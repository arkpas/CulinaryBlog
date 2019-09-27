package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Authority;
import arkpas.culinaryblog.domain.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority addAuthority (String username, String authorityName) {
        Authority authority = new Authority();
        authority.setUsername(username);
        authority.setName(authorityName);
        return authorityRepository.saveAuthority(authority);
    }
}
