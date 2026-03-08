package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.RegisterUserDTO;
import by.sysoev.tourApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(RegisterUserDTO registerUserDTO) {
        jdbcTemplate.update("INSERT INTO users (first_name,last_name,email,phone) VALUES(?,?,?,?)",
                registerUserDTO.getFirstName(),
                registerUserDTO.getLastName(),
                registerUserDTO.getEmail(),
                registerUserDTO.getPhone());
    }
}
