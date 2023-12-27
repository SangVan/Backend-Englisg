package com.example.apienglish.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.Level;
import com.example.apienglish.entity.UserAccess;
import com.example.apienglish.exception.ResourceNotFoundException;
import com.example.apienglish.repository.AccountRepository;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.repository.RoleRepository;
import com.example.apienglish.repository.UserAccessRepository;
import com.example.apienglish.response.MessageResponse;
import com.example.apienglish.service.AccountService;
import com.example.apienglish.service.UserAccessService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private LevelRepository levelRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    private UserAccessService accessService;
    
    @Autowired
    private UserAccessRepository accessRepository;
    
    @PostMapping("levels/{levelId}/accounts/{accountId}/saveUserAccess") // Updated path variable name here
	public ResponseEntity<UserAccess> saveUserAccess(@RequestBody UserAccess userAccess, @PathVariable Long accountId,
			@PathVariable Long levelId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
		userAccess.setAccount(account);
		
		Level level = levelRepository.findById(levelId)
				.orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + levelId));
		userAccess.setLevel(level);

		UserAccess saveAccess = accessService.saveUserAccess(userAccess);

		return new ResponseEntity<>(saveAccess, HttpStatus.CREATED);
	}

//    @PostMapping("/createUser")
//    public ResponseEntity<Account> createAccount(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("username") String username,
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            @RequestParam("gender") String gender,
//            @RequestParam("birthday") String birthday,
//            @RequestParam("phoneNumber") String phoneNumber,
//            @RequestParam("address") String address,
//            @RequestParam("fullname") String fullname,
//            @RequestParam("levelNames") Set<String> levelNames) throws IOException {
//        Account account = accountService.createAccount(file, username, email, encoder.encode(password), gender, birthday, phoneNumber, address, fullname, levelNames);
//        return new ResponseEntity<>(account, HttpStatus.CREATED);
//    }
    
    @PostMapping("/createUser")
    public ResponseEntity<?> createAccount(
            @RequestParam("file") MultipartFile file,
            @Valid @RequestParam("username") String username,
            @Valid @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("gender") String gender,
            @RequestParam("birthday") String birthday,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("fullname") String fullname,
            @RequestParam("levelname") Set<String> levelNames
            ) throws IOException {
    	
    	if (accountRepository.existsByUsername(username)) {
    		return ResponseEntity
    		.badRequest()
    		.body(new MessageResponse("Error: Username is already taken!"));
    		}
    		
    		if (accountRepository.existsByEmail(email)) {
    		return ResponseEntity
    		.badRequest()
    		.body(new MessageResponse("Error: Email is already in use!"));
    		}
        Account account = accountService.createAccount(file, username, email, encoder.encode(password), gender, birthday, phoneNumber, address, fullname,levelNames);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    


    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/getAccountById/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/updateAccount/{accountId}")
    public ResponseEntity<?> updateAccount(
            @PathVariable Long accountId,
            @Valid @RequestParam("username") String username,
            @RequestParam("file") MultipartFile file,
            @Valid @RequestParam("email") String email,
            @RequestParam("gender") String gender,
            @RequestParam("birthday") String birthday,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("fullname") String fullname
            ) throws IOException {
    	Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Account existingAccount = optionalAccount.get();

        // Check if the updated username is already taken by another account
        if (!username.equals(existingAccount.getUsername()) && accountRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Check if the updated email is already in use by another account
        if (!email.equals(existingAccount.getEmail()) && accountRepository.existsByEmail(email)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Account updatedAccount = accountService.updateAccount(accountId, file,username, email, gender, birthday, phoneNumber, address, fullname);
        if (updatedAccount != null) {
        	
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        String result = accountService.deleteAccount(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
    @GetMapping("/countByLevel/{levelId}")
	public ResponseEntity<?> countAccountsByLevelId(@PathVariable Long levelId) {
		return new ResponseEntity<>(accountRepository.countAccountsByLevelId(levelId), HttpStatus.OK);
  }
    
  //Đếm số người dùng đã đăng ký
	  @GetMapping("/countAccounts")
		public ResponseEntity<?> countAccounts() {
			return new ResponseEntity<>(accountRepository.countAccounts(), HttpStatus.OK);
	  }
	  
	//Đếm số truy cập theo ngày
	  @GetMapping("/countAccessByDate")
	    public Long countAccessByDate(@RequestParam("date") String dateString) {
	        
			return accessRepository.countByAccessDate(dateString);
	    }
	  
	  @GetMapping("/countAccessByDateAndLevel")
	    public List<Map<String, Object>> countAccessByDateAndLevel(
	            @RequestParam("startDate") String startDate,
	            @RequestParam("endDate") String endDate,
	            @RequestParam("levelId") Long levelId) {
		  System.out.println(startDate);
		  System.out.println(endDate);
		  System.out.println(levelId);
	        // Gọi phương thức từ repository
	        return accessRepository.countAccessByDateAndLevel(startDate, endDate, levelId);
	    }
	  
	  @GetMapping("/countAccessByLevelId")
		public ResponseEntity<?> countAccessByLevelId(@RequestParam("levelId") Long levelId,
				@RequestParam("today") String today) {
			return new ResponseEntity<>(accessRepository.countAccessByLevelId(levelId, today), HttpStatus.OK);
	  }
}