package by.sysoev.tourApp.service;

import by.sysoev.tourApp.entity.UserPrincipals;
import by.sysoev.tourApp.repository.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails details =  userRepository.getUserByEmail(username)
                .map(UserPrincipals::new)
                .orElseThrow(() -> new UsernameNotFoundException("Uncorrected username or password"));
        log.debug("USERDATEILS ---- {}, {}",details.getUsername(),details.getAuthorities());
        return details;
    }
}
