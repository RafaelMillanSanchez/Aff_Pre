package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.LocalBudgetRdcC;

public interface BudgetRDCRepository extends PagingAndSortingRepository<LocalBudgetRdcC, Integer> {

	@Query("select l from LocalBudgetRdcC l where l.ownerid = ?1 and l.activordcC = true")
	List<LocalBudgetRdcC> findByOwnerid(String username);

}
