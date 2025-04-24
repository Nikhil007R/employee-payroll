package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmailDTO;
import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import com.bridgelabz.employee_payroll.utility.JwtUserDetailsService;
import com.bridgelabz.employee_payroll.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
public class UserService {

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

    @Autowired
    private EmailService emailService;


    public List<User> getAllEmployees(){
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> updateUserById(Long id, RegisterDTO request){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        ResponseDTO response = new ResponseDTO("User updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

// *******************    register    *******************

    public ResponseEntity<ResponseDTO> registerUser(RegisterDTO request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            ResponseDTO response = new ResponseDTO("user already exists", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        EmailDTO email = new EmailDTO(
                user.getEmail(),
                "Welcome to our Employee_Roll App",
                "Hi " + user.getFullName() + "! Thanks for Registering here. "
        );
        emailService.sendEmail(email);
        log.info("Successfully working email");

        ResponseDTO response = new ResponseDTO("User registered successfully", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//   ******************* login *******************
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO response = new ResponseDTO("Invalid Credentials", HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtility.generateToken(userDetails.getUsername());

        ResponseDTO response = new ResponseDTO(token,HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
