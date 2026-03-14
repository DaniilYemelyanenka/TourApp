package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.RegisterUserDTO;
import by.sysoev.tourApp.DTO.UserLoginDTO;
import by.sysoev.tourApp.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepositoryImpl userRepository;

    public Map<String,String> addUser(RegisterUserDTO registerUserDTO){
        log.debug("Adding user from user servicce");
        userRepository.addUser(registerUserDTO);
        return authenticate(registerUserDTO.getUsername(), registerUserDTO.getPassword());
    }

    public Map<String,String> loginUser(UserLoginDTO userLoginDTO ){
       userRepository.getUserByEmail(userLoginDTO.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Uncorrected email or password"));
        return authenticate(userLoginDTO.getUsername(),userLoginDTO.getPassword());
    }

    private Map<String,String> authenticate(String username, String password) throws AuthenticationException {
        log.debug("authenticated user from user service");
        String token = null;
        String roles = null;
        Map<String,String> map = new HashMap<>();

        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(username,password)
                );

        if(auth.isAuthenticated()){
            log.debug("TOKEN: {}, ROLES: {}",token,roles);
            token = jwtService.generateToken(username);
            roles = auth.getAuthorities().toString();
            map.put("token", token);
            map.put("roles",roles);
        }

        return map;
    }
}
