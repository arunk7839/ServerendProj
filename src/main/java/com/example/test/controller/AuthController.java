package com.example.test.controller;


import com.example.test.model.JwtRequest;
import com.example.test.model.JwtResponse;
import com.example.test.model.User;
import com.example.test.security.JwtTokenHelper;
import com.example.test.sevice.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> generateAuthenticationToken(@RequestBody JwtRequest request)
            throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsServiceImpl
                .loadUserByUsername(request.getUsername());

        final String token = jwtTokenHelper.generateToken(userDetails);

        System.out.println("token:"+token );

        JwtResponse jwtAuthResponse = new JwtResponse();
        jwtAuthResponse.setToken(token);

        return new ResponseEntity<JwtResponse>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        //Objects.requireNonNull(username);
        //Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
    }
}
