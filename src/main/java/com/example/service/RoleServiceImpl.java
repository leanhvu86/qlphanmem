package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByRoleId(Integer roleId) {
		return roleRepository.findByRoleId(roleId);
	}



}
