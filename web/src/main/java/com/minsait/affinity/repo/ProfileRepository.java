package com.minsait.affinity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Profile;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Integer> {
	
	public Profile findBySfid(String sfid);
	
}