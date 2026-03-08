package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.RegisterUserDTO;
import by.sysoev.tourApp.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepositoryImpl userRepository;

    public void addUser(RegisterUserDTO registerUserDTO){
        userRepository.addUser(registerUserDTO);
    }
}
