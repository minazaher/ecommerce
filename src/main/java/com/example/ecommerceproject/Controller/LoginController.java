package com.example.ecommerceproject.Controller;


import com.example.ecommerceproject.DTO.AuthenticationRequest;
import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Repository.UserRepository;
import com.example.ecommerceproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(userService.authenticate(request).token);
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @GetMapping("/")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/processLogin")
    public String processLogin(User user , Model model){
        model.addAttribute("user", userRepository.findUserByEmail(user.getEmail()));
        if (userService.login(user)){
            return "home";
        }
        return "login";
    }


}
