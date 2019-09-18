package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Authority;

public interface AuthorityRepository {

    public Authority getAuthority (int id);
    public Authority getAuthority (String name);
    public void saveAuthority (Authority authority);
    public void updateAuthority (Authority authority);
    public void removeAuthority (Authority authority);
}
