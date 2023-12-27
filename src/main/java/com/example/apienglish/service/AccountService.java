package com.example.apienglish.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.Level;
import com.example.apienglish.entity.Role;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AccountService {
    // Thêm Account
    Account createAccount(MultipartFile file, String username, String email, String password, String gender, String birthday, String phoneNumber, String address, String fullname, Set<String> levelNames) throws IOException;

    
//    Account createAccountAdmin(MultipartFile file, String username, String email, String password, String gender, String birthday, String phoneNumber, String address,Set<Role> roles) throws IOException;
    // Hiển thị tất cả Account
    List<Account> getAllAccounts();

    // Lấy Account theo id
    Account getAccountById(Long accountId);

//    // Chỉnh sửa Account theo id
//    Account updateAccount(Account account, Long accountId);

    // Xóa Account theo id
    String deleteAccount(Long accountId);
    
    Account updateAccount(Long accountId, MultipartFile file,String username, String email, String gender, String birthday, String phoneNumber, String address, String fullname) throws IOException;
    
//    Account updateAccountInfo(Long accountId, MultipartFile file, String username, String email, String password, String gender, String birthday, String phoneNumber, String address, Set<Role> roles, Set<Level> levels) throws IOException;
}