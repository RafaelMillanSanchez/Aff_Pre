package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.CheckVisitC;

public interface CheckVisitRepository extends PagingAndSortingRepository<CheckVisitC, Integer>{

	
	@Query("select c from CheckVisitC c where c.checkProductC IN ?1 order by c.createddate desc")
	List<CheckVisitC> findCheckVisits(List<String> check_prod_ids);

	@Query("select c from CheckVisitC c where c.checkProductC = ?1 and c.accountC = ?2 order by c.createddate desc")
	CheckVisitC findLastCheckVisitPromo(String sfid, String shiptoid, PageRequest pageable);

	@Query("select c from CheckVisitC c where c.checkProductC = ?1 order by c.createddate desc")
	CheckVisitC findLastCheckVisitSurtido(String sfid, PageRequest of);

}
