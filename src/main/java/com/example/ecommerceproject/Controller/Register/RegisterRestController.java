package com.example.ecommerceproject.Controller.Register;


import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.DTO.RegisterRequest;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Service.UserService;
import com.example.ecommerceproject.Service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/register")
public class RegisterRestController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

}
