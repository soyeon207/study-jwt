package me.soyeon207.tutorial.controller;

import me.soyeon207.tutorial.dto.UserDto;
import me.soyeon207.tutorial.entity.User;
import me.soyeon207.tutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
        // UserDto를 파라미터로 받아서 회원가입 하는 API
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        // USER, ADMIN 두가지 권한 허용
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        // ADMIN 만 권한 허용
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}
