package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Signup successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null && passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            session.setAttribute("user", dbUser.getUsername());
            return "Login successful";
        }
        return "Invalid credentials";
    }

    @GetMapping("/check")
    public String checkSession(HttpSession session) {
        Object user = session.getAttribute("user");
        return user != null ? "Authenticated as " + user : "Not authenticated";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }
}
