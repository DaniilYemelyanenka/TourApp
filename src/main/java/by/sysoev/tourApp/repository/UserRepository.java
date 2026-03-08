package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.RegisterUserDTO;

public interface UserRepository {
    void addUser(RegisterUserDTO registerUserDTO);
}
