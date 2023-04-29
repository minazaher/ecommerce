package com.example.ecommerceproject.Service;


import com.example.ecommerceproject.DTO.AuthenticationRequest;
import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.DTO.RegisterRequest;
import com.example.ecommerceproject.Model.Role;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Repository.UserRepository;
import com.example.ecommerceproject.Repository.WishlistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final WishlistRepository wishlistRepository;

    public User getUserByEmail(String email ){
        return userRepository.findUserByEmail(email);
    }

    public User register(RegisterRequest request) {

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return user;
    }

    public AuthenticationResponse registerِAndGetToken(RegisterRequest request) {

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        User user = userRepository.findUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()
        ));
        try {
            User newuser = userRepository.findUserByEmail(user.getEmail());
            String jwtToken = jwtService.generateToken(newuser);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (Exception NoSuchElementException) {
            return new AuthenticationResponse("NO USER");
        }
    }
    public boolean login(User user) {
        User u = userRepository.findUserByEmail(user.getEmail());
        return u != null;
    }

}
