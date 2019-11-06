package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer>{
	
	Role findByRoleId(Integer id);
}
