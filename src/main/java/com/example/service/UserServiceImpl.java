package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.LoginUser;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}



}
