package com.example.apienglish.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apienglish.entity.UserAccess;
import com.example.apienglish.repository.UserAccessRepository;
import com.example.apienglish.service.UserAccessService;

@Service
public class UserAccessServiceImpl implements UserAccessService {
	@Autowired
	private UserAccessRepository accessRepository;

	@Override
	public UserAccess saveUserAccess(UserAccess access) {
		// TODO Auto-generated method stub
		return accessRepository.save(access);
	}

}
