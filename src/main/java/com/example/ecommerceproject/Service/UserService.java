package com.example.ecommerceproject.Service;


import com.example.ecommerceproject.DTO.AuthenticationRequest;
import com.example.ecommerceproject.DTO.AuthenticationResponse;
import com.example.ecommerceproject.DTO.RegisterRequest;
import com.example.ecommerceproject.Model.Role;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public User getUserByEmail(String email ){
        return userRepository.findUserByEmail(email);
    }
    public User register(RegisterRequest request) {
        User user = User.builder().
                firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).
                role(Role.USER).
                build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return user;
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
