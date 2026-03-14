package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.RegisterUserDTO;
import by.sysoev.tourApp.entity.Role;
import by.sysoev.tourApp.entity.User;
import by.sysoev.tourApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void addUser(RegisterUserDTO registerUserDTO) {
        log.debug("addding user ad repository level");
        Long userId = jdbcTemplate.queryForObject(
                "INSERT INTO users (first_name,last_name,email,phone,password_hash) VALUES (?,?,?,?,?) RETURNING id",
                Long.class,
                registerUserDTO.getFirstName(),
                registerUserDTO.getLastName(),
                registerUserDTO.getUsername(),
                registerUserDTO.getPhone(),
                encoder.encode(registerUserDTO.getPassword())
        );
        jdbcTemplate.update("INSERT INTO users_roles (user_id,role_id) VALUES (?,?)",userId,registerUserDTO.getRoleId());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String sql = """
                SELECT 
                    u.id,
                    u.first_name,
                    u.last_name,
                    u.phone,
                    u.email,
                    u.password_hash,
                    ARRAY_AGG(r.name) AS roles
                FROM users u
                JOIN users_roles ur ON u.id = ur.user_id
                JOIN roles r ON r.id = ur.role_id
                WHERE u.email = ?
                GROUP BY u.id;
                """;



        User user  = jdbcTemplate.queryForObject(sql,(rs,rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("password_hash"),
                Arrays.stream((String[]) rs.getArray("roles").getArray())
                        .map(Role::new)
                        .collect(Collectors.toSet())
        ),email);
        return Optional.ofNullable(user);
    }
}
