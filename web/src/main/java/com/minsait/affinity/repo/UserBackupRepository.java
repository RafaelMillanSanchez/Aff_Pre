package com.minsait.affinity.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.UserBackupC;

public interface UserBackupRepository extends PagingAndSortingRepository<UserBackupC, Integer> {

	@Query("select u from UserBackupC u where u.chkIsSubstitutingC = true and u.lkpSubstituteC = ?1")
	Iterable<UserBackupC> findByLkpSubstituteC(String username);

	

	
}