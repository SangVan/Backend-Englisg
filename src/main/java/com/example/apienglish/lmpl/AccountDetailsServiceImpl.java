package com.example.apienglish.lmpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.apienglish.entity.Account;
import com.example.apienglish.repository.AccountRepository;

import jakarta.transaction.Transactional;


@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
  @Autowired
  AccountRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return AccountDetailsImpl.build(user);
  }

}