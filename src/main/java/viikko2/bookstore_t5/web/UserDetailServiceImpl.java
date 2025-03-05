package viikko2.bookstore_t5.web;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import viikko2.bookstore_t5.domain.AppUser;
import viikko2.bookstore_t5.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    AppUserRepository repository;


    public UserDetailServiceImpl(AppUserRepository appUserRepository){
        this.repository =appUserRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AppUser currentuser = repository.findByUsername(username);
        if (currentuser == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        UserDetails user = new org.springframework.security.core.userdetails
        .User(username, currentuser.getPasswordHash(),
        AuthorityUtils.createAuthorityList(currentuser.getRole()));
    
        return user;

    }
}
