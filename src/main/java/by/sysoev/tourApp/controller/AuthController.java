package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.RegisterUserDTO;
import by.sysoev.tourApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor()
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO registerUserDTO){
        log.info("Request to register user with email {}",registerUserDTO.getEmail());
        userService.addUser(registerUserDTO);
        return ResponseEntity.ok("success");
    }
}
