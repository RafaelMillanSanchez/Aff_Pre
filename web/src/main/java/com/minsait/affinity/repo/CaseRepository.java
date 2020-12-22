package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Case;

public interface CaseRepository extends PagingAndSortingRepository<Case, Integer> {
	
	public Case findBySfid(String sfid);
	
	@Query("select c from Case c where c.lkpOrderC IN ?1 and c.type = 'To Do'")
	public Iterable<Case> findToDo(Set<String> keySet);
	
}