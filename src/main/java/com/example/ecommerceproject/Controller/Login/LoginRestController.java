package com.example.ecommerceproject.Controller.Login;


import com.example.ecommerceproject.DTO.AuthenticationRequest;
import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/login")
@RequiredArgsConstructor
public class LoginRestController {

    private final UserService userService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = userService.authenticate(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  response.getToken());
        return ResponseEntity.ok().headers(headers).body(response);
    }
}