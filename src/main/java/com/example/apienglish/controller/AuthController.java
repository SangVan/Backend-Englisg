//package com.example.apienglish.controller;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.apienglish.entity.Account;
//import com.example.apienglish.entity.ERole;
//import com.example.apienglish.entity.Level;
//import com.example.apienglish.entity.Role;
//import com.example.apienglish.lmpl.AccountDetailsImpl;
//import com.example.apienglish.repository.AccountRepository;
//import com.example.apienglish.repository.LevelRepository;
//import com.example.apienglish.repository.RoleRepository;
//import com.example.apienglish.request.LoginRequest;
//import com.example.apienglish.request.SignupRequest;
//import com.example.apienglish.response.JwtResponse;
//import com.example.apienglish.response.MessageResponse;
//import com.example.apienglish.security.jwt.JwtUtils;
//import com.example.apienglish.service.EmailService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import jakarta.validation.Valid;
//

package com.example.apienglish.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.ERole;
import com.example.apienglish.entity.Level;
import com.example.apienglish.entity.Role;
import com.example.apienglish.lmpl.AccountDetailsImpl;
import com.example.apienglish.repository.AccountRepository;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.repository.RoleRepository;
import com.example.apienglish.request.LoginRequest;
import com.example.apienglish.request.SignupRequest;
import com.example.apienglish.response.JwtResponse;
import com.example.apienglish.response.MessageResponse;
import com.example.apienglish.security.jwt.JwtUtils;
import com.example.apienglish.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	LevelRepository levelRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
    private EmailService emailService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();

	        if (!userDetails.isEnabled()) {
	            return ResponseEntity.badRequest().body(new MessageResponse("Vui lòng xác nhận email trước khi đăng nhập."));
	        }

	        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
	                userDetails.getEmail(), roles));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Internal server error"));
	    }
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    try {
	        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	        }

	        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	        }

			Account user = new Account(signUpRequest.getUsername(), signUpRequest.getEmail(),
			encoder.encode(signUpRequest.getPassword()));

	Set<String> strRoles = signUpRequest.getRole();
	Set<Role> roles = new HashSet<>();

	Set<String> strLevels = signUpRequest.getLevel();
	Set<Level> levels = new HashSet<>();

	if (strLevels != null && !strLevels.isEmpty()) {
		for (String levelName : strLevels) {
			List<Level> levelList = levelRepository.findByLevelNameContaining(levelName);
			if (!levelList.isEmpty()) {
				levels.addAll(levelList);
			} else {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Level not found for " + levelName));
			}
		}
	}

	if (strRoles == null) {
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
	} else {
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);

				break;

			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
		});
	}
	user.setLevels(levels);
	user.setRoles(roles);
	userRepository.save(user);
	
	 // Generate and send verification code via email
    String verificationCode = RandomStringUtils.randomNumeric(6);
    user.setVerificationCode(verificationCode);
    userRepository.save(user);

    emailService.sendVerificationCode(user.getEmail(), verificationCode);

	        return ResponseEntity.ok(new MessageResponse("User registered successfully! Check your email for verification."));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Internal server error"));
	    }
	}

	@GetMapping("/verifyCode")
	public ResponseEntity<?> verifyEmail(@RequestParam("email") String email, @RequestParam("code") String code) {
	    try {
	        Optional<Account> userOptional = userRepository.findByEmailAndVerificationCode(email, code);

	        if (userOptional.isPresent()) {
	            Account user = userOptional.get();
	            user.setEnabled(true);
	            user.setVerificationCode(null);
	            userRepository.save(user);
	            return ResponseEntity.ok(new MessageResponse("Email verification successful! You can now log in."));
	        } else {
	            return ResponseEntity.badRequest()
	                    .body(new MessageResponse("Email verification failed. Please check your verification code or request a new one."));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Internal server error"));
	    }
	}

}

//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/auth")
//public class AuthController {
//	@Autowired
//	AuthenticationManager authenticationManager;
//
//	@Autowired
//	AccountRepository userRepository;
//
//	@Autowired
//	RoleRepository roleRepository;
//	@Autowired
//	LevelRepository levelRepository;
//
//	@Autowired
//	PasswordEncoder encoder;
//	@Autowired
//    private EmailService emailService;
//
//	@Autowired
//	JwtUtils jwtUtils;
//
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();
//		
//		if (!userDetails.isEnabled()) {
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse("Vui lòng xác nhận email trước khi đăng nhập."));
//        }
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(
//				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
//
//	}
//
//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
////if (userRepository.existsByUsername(signUpRequest.getUsername())) {
////return ResponseEntity
////.badRequest()
////.body(new MessageResponse("Error: Username is already taken!"));
////}
////
////if (userRepository.existsByEmail(signUpRequest.getEmail())) {
////return ResponseEntity
////.badRequest()
////.body(new MessageResponse("Error: Email is already in use!"));
////}
//
//// Create new user's account
//		Account user = new Account(signUpRequest.getUsername(), signUpRequest.getEmail(),
//				encoder.encode(signUpRequest.getPassword()));
//
//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();
//
//		Set<String> strLevels = signUpRequest.getLevel();
//		Set<Level> levels = new HashSet<>();
//
//		if (strLevels != null && !strLevels.isEmpty()) {
//			for (String levelName : strLevels) {
//				List<Level> levelList = levelRepository.findByLevelNameContaining(levelName);
//				if (!levelList.isEmpty()) {
//					levels.addAll(levelList);
//				} else {
//					return ResponseEntity.badRequest()
//							.body(new MessageResponse("Error: Level not found for " + levelName));
//				}
//			}
//		}
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}
//		user.setLevels(levels);
//		user.setRoles(roles);
//		userRepository.save(user);
//		
//		 // Generate and send verification code via email
//        String verificationCode = RandomStringUtils.randomNumeric(6);
//        user.setVerificationCode(verificationCode);
//        userRepository.save(user);
//
//        emailService.sendVerificationCode(user.getEmail(), verificationCode);
//
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}
//	
//	 @GetMapping("/verifyCode")
//	    public ResponseEntity<?> verifyEmail(@RequestParam("email") String email, @RequestParam("code") String code) {
//	        try {
//	            Optional<Account> userOptional = userRepository.findByEmailAndVerificationCode(email, code);
//
//	            if (userOptional.isPresent()) { 	
//	                Account user = userOptional.get();
//	                user.setEnabled(true);
//	                user.setVerificationCode(null);
//	                userRepository.save(user);
//	                return ResponseEntity.ok(new MessageResponse("Email verification successful! You can now log in."));
//	            } else {
//	                return ResponseEntity.badRequest()
//	                        .body(new MessageResponse("Email verification failed. Please check your verification code or request a new one."));
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Internal server error"));
//	        }
//	    }
//}
