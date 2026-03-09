package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.RegisterUserDTO;
import by.sysoev.tourApp.entity.User;

import java.util.Optional;

public interface UserRepository {
    void addUser(RegisterUserDTO registerUserDTO);
    Optional<User> getUserByEmail(String email);
}
