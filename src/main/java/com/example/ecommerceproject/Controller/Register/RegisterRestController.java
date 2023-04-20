package com.example.ecommerceproject.Controller.Register;


import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.DTO.RegisterRequest;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/register")
public class RegisterRestController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }


}
