package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.AccountDeliveryDatesC;

public interface AccountDeliveryDatesRepository extends PagingAndSortingRepository<AccountDeliveryDatesC, Integer>{

	
	//@Query("select a from AccountDeliveryDatesC a where a.lkpAccountC = ?1 order by a.createddate desc")
	//AccountDeliveryDatesC findDeliveryDate(String clienteid, PageRequest pageRequest);

	@Query("select a from AccountDeliveryDatesC a where a.lkpAccountC IN ?1")
	Iterable<AccountDeliveryDatesC> findDeliveryDates(Set<String> accountIds);

}
