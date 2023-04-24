package com.example.ecommerceproject.Controller.Login;


import com.example.ecommerceproject.Controller.Wishlist.WishlistRestController;
import com.example.ecommerceproject.DTO.AuthenticationRequest;
import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Repository.UserRepository;
import com.example.ecommerceproject.Service.UserService;
import com.example.ecommerceproject.Service.WishlistService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final WishlistService wishlistService;
    private final  AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/processLogin")
    public String processLogin(User user , HttpSession session, Model model){
        user = userService.getUserByEmail(user.getEmail());
        model.addAttribute("user", user );
        session.setAttribute("user", user);
        if (userService.login(user)){
            return "home";
        }
        return "redirect:/login/";
    }




}
