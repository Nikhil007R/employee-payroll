//package com.bridgelabz.employee_payroll.controller;
//
//import com.bridgelabz.employee_payroll.model.User;
//import com.bridgelabz.employee_payroll.repository.UserRepository;
//import com.bridgelabz.employee_payroll.utility.JwtUtility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//public class publicController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtility jwtUtility;
//
//    @Autowired
//    private UserDetailsService userdetails;
//
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody User user){
//        User newUser = new User();
//        newUser.setEmail(user.getEmail());
//        newUser.setName(user.getName());
//        newUser.setPassword(user.getPassword());
//
////        userRepository.save(newUser);
//
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//
//        userRepository.save(newUser);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestBody User reqUser){
////        Optional<User> userExists = userRepository.findByEmail(reqUser.getEmail());
////
////        if(userExists.isPresent()){
////            UserDetails userDetails = userdetails.loadUserByUsername(reqUser.getEmail());
////            String token = jwtUtility.generateToken(userDetails.getUsername());
////
////            return new ResponseEntity<>(token, HttpStatus.BAD_REQUEST);
////        }
////        String noToken = "No token generate";
////        return new ResponseEntity<>(noToken, HttpStatus.BAD_REQUEST);
////
////
////    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User reqUser) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            reqUser.getEmail(),
//                            reqUser.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
//        }
//
//        // Authentication successful
//        UserDetails userDetails = userdetails.loadUserByUsername(reqUser.getEmail());
//        String token = jwtUtility.generateToken(userDetails.getUsername());
//
//        return new ResponseEntity<>(token, HttpStatus.OK);
//    }
//}
