package expense.user_service.service;


import java.util.Optional;

import expense.user_service.dto.AuthRequest;
import expense.user_service.dto.AuthenticationResponse;
import expense.user_service.models.User;
import expense.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;


    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.toString());
        User savedUser = repo.save(user);
        return savedUser;
    }

    public String generateToken(String email) {
        // TODO Auto-generated method stub
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        // TODO Auto-generated method stub
        jwtService.validateToken(token);

    }


    public ResponseEntity<?> authenticate(AuthRequest authRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            if (authenticate.isAuthenticated()) {
                String email = authRequest.getEmail();
                Optional<User> userOptional = repo.findByEmail(email);

                if (userOptional.isPresent()) {
                    User user = userOptional.get(); // Retrieve the User object
                    String token = jwtService.generateToken(user.getEmail());
                    System.out.println(token);
                    return  ResponseEntity.ok(new AuthenticationResponse(token , user.getUserId() , "Logged in Successfully")); // Return structured response
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new AuthenticationResponse(null, null ,"User not found"));
                }
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthenticationResponse(null, null ,"Invalid credentials"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthenticationResponse(null, null ,"Authentication error"));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new AuthenticationResponse(null, null ,"Invalid access"));


    }


    // Business logic here
}

