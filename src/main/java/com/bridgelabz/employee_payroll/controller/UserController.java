package com.bridgelabz.employee_payroll.controller;

import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import com.bridgelabz.employee_payroll.service.UserService;
import com.bridgelabz.employee_payroll.utility.JwtUserDetailsService;
import com.bridgelabz.employee_payroll.utility.JwtUtility;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllEmployees();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long id, @RequestBody RegisterDTO request) {
        return userService.updateUserById(id, request);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }


//    @PostMapping("/register")
//    public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegisterDTO request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            ResponseDTO response = new ResponseDTO("user already exists", HttpStatus.NOT_FOUND);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//
//        User user = new User();
//        user.setFullName(request.getFullName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        userRepository.save(user);
//
//        ResponseDTO response = new ResponseDTO("User registered successfully", HttpStatus.CREATED);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseDTO response = new ResponseDTO("Invalid Credentials", HttpStatus.UNAUTHORIZED);
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        final String token = jwtUtility.generateToken(userDetails.getUsername());
//
//        ResponseDTO response = new ResponseDTO(token,HttpStatus.OK);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}