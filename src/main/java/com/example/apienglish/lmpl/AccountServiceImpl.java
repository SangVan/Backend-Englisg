package com.example.apienglish.lmpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.ERole;
import com.example.apienglish.entity.Level;
import com.example.apienglish.entity.Role;
import com.example.apienglish.repository.AccountRepository;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.repository.RoleRepository;
import com.example.apienglish.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private LevelRepository levelRepository;

    @Override
    public Account createAccount(MultipartFile file, String username, String email, String password, String gender, String birthday, String phoneNumber, String address, String fullname,Set<String> levelNames) throws IOException {
    	 Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	        

        Account account = new Account();
        account.setUsername(username);
        account.setFullname(fullname);
        account.setEmail(email);
        account.setPassword(password);
        account.setAvatarUrl(cloudinaryResponse.get("url").toString());
        account.setGender(gender);
        account.setBirthday(birthday);
        account.setPhoneNumber(phoneNumber);
        account.setAddress(address);
        
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        roles.add(adminRole);
        account.setRoles(roles);
        
        Set<Level> levels = new HashSet<>();
        if (levelNames != null && !levelNames.isEmpty()) {
            for (String levelName : levelNames) {
                List<Level> levelList = levelRepository.findByLevelNameContaining(levelName);
                if (!levelList.isEmpty()) {
                    levels.addAll(levelList);
                } else {
                    // Handle level not found
                    throw new RuntimeException("Error: Level not found for " + levelName);
                }
            }
        }
        account.setLevels(levels);
        
        accountRepository.save(account);       
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

//    @Override
//    public Account updateAccount(Account account, Long accountId) {
//        Account existingAccount = accountRepository.findById(accountId).orElse(null);
//        if (existingAccount != null) {
//            // Thực hiện các thay đổi trên existingAccount dựa trên thông tin từ tham số account
//            // Ví dụ: existingAccount.setUsername(account.getUsername());
//            // Làm tương tự cho các trường cần thay đổi
//            return accountRepository.save(existingAccount);
//        }
//        return null;
//    }
    @Override
    public Account updateAccount(Long accountId, MultipartFile file,String username, String email, String gender, String birthday, String phoneNumber, String address, String fullname) throws IOException {
   	 
   	Account existingAccount = accountRepository.findById(accountId).orElse(null);
       if (existingAccount != null) {
           existingAccount.setUsername(username);
           existingAccount.setFullname(fullname);
           existingAccount.setEmail(email);
//           existingAccount.setPassword(password);
           if (file != null) {
        	   Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
           existingAccount.setAvatarUrl(cloudinaryResponse.get("url").toString());
           }
           existingAccount.setGender(gender);
           existingAccount.setBirthday(birthday);
           existingAccount.setPhoneNumber(phoneNumber);
           existingAccount.setAddress(address);
           return accountRepository.save(existingAccount);
       }
       return null;
   }

   @Override
   public String deleteAccount(Long accountId) {
       Account account = accountRepository.findById(accountId).orElse(null);
       if (account != null) {
           accountRepository.delete(account);
           return "Delete Successfully";
       }
       return "Error on Server";
   }

//	@Override
//	public Account updateAccountInfo(Long accountId, MultipartFile file, String username, String email, String password,
//			String gender, String birthday, String phoneNumber, String address, Set<Role> roles, Set<Level> levels) throws IOException {
//		
//	    	Account existingAccount = accountRepository.findById(accountId).orElse(null);
//	        if (existingAccount != null) {
//	            existingAccount.setUsername(username);
//	            existingAccount.setEmail(email);
//	            existingAccount.setPassword(password);
//	            if (file != null) {
//	                Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//	                existingAccount.setAvatarUrl(cloudinaryResponse.get("url").toString());
//	            }
////	            existingAccount.setAvatarUrl(cloudinaryResponse.get("url").toString());
//	            existingAccount.setGender(gender);
//	            existingAccount.setBirthday(birthday);
//	            existingAccount.setPhoneNumber(phoneNumber);
//	            existingAccount.setAddress(address);
//	            existingAccount.setRoles(roles);
//	            existingAccount.setLevels(levels);
//	            return accountRepository.save(existingAccount);
//	        }
//		return null;
//	}

	


}