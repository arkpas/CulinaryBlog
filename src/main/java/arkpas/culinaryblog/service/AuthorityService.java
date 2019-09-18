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

    public Authority getAuthority (int id) {
        return authorityRepository.getAuthority(id);
    }
    public Authority getAuthority (String name) {
        return authorityRepository.getAuthority(name);
    }
    public void addAuthority (String username, String authorityName) {
        Authority authority = new Authority();
        authority.setUsername(username);
        authority.setName(authorityName);
        authorityRepository.saveAuthority(authority);
    }
    public void updateAuthority (Authority authority) {
        authorityRepository.updateAuthority(authority);
    }
    public void deleteAuthority (Authority authority) {
        authorityRepository.removeAuthority(authority);
    }
}
