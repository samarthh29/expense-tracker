package expense.user_service.controllers;


import java.util.Optional;

import expense.user_service.dto.AuthRequest;
import expense.user_service.models.User;
import expense.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User addedd successfully..");
    }

//	@PostMapping("/token")
//	public String getToken(@RequestBody AuthRequest authRequest) {
//		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
//	    if(authenticate.isAuthenticated()) {
//	    	return userService.generateToken(authRequest.getEmail());
//	    }else {
//	    	throw new RuntimeException("Invalid Exception.");
//	    }
//
//	}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        System.out.println("Inside Authenticate");
        System.out.println(authRequest.getEmail());

        return userService.authenticate(authRequest);
    }

    @GetMapping("/validate")
    public String validToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }



}
