package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.DTO.RegisterRequest;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class RegisterController {

    private final UserService userService;

//    @PostMapping("/")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/accountCreated")
    public String processCreate(User user, Model model){
        user = userService.register(user);
        model.addAttribute("User", user) ;
        return "redirect:/login/";
    }

}
