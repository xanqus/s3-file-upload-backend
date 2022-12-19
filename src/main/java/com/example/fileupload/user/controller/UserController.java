package com.example.fileupload.user.controller;

import com.example.fileupload.user.dao.UserRepository;
import com.example.fileupload.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String join(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
