package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
	public User findBySfid(String sfid);

	public User findByUsername(String username);

	public User findByTxtSapIdC(String txtSapIdC);
	
	@Query("select u from User u where u.isactive = true and u.userroleid IN ?1")
	public Iterable<User> findISByCountry(List<String> idRoles);
	
}
