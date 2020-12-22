package com.minsait.affinity.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.affinity.jpa.model.User;
import com.minsait.affinity.mappers.UserDataMapper;
import com.minsait.affinity.repo.UserRepository;
import com.minsait.affinity.web.model.WsUser;

@Service
public class SalesforceUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDataMapper userMapper;
	

	public List<WsUser> getAllUsers() {
		Iterable<User> allUsers = this.userRepository.findAll();
		return userMapper.salesforce2web(allUsers);
	}
	
	public WsUser getUserByUsername(String username) {
		User theUser = this.userRepository.findByUsername(username);
		return this.userMapper.salesforce2web(theUser);
	}

	

}
