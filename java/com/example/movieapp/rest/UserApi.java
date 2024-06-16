package com.example.movieapp.rest;


import com.example.movieapp.entity.User;
import com.example.movieapp.model.request.UpdateProfileUserRequest;
import com.example.movieapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/update-profile")
@RequiredArgsConstructor
public class UserApi {
    private final HttpSession session;
    private final UserRepository userRepository;
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileUserRequest updateProfileUserRequest) {
        User currentUser = (User) session.getAttribute("currentUser");

        currentUser.setName(updateProfileUserRequest.getName());
        userRepository.save(currentUser);
        session.setAttribute("currentUser", currentUser);
        return ResponseEntity.ok("Cập nhât thông tin thành công!");
    }
}
