package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.ObjetivoGlobal;
import com.minsait.affinity.jpa.model.ObjetivocuantitativoC;

public interface ObjetivoCuantitativoRepository extends PagingAndSortingRepository<ObjetivocuantitativoC, Integer> {

	@Query("select o from ObjetivocuantitativoC o where o.userC = ?1 and o.startdateC <= CURRENT_DATE and o.enddateC >= CURRENT_DATE order by o.subbrandC asc")
	Iterable<ObjetivocuantitativoC> findCuantiRDC(String username);

	@Query("select new com.minsait.affinity.jpa.model.ObjetivoGlobal(sum(o.goalC), sum(o.achievementC), "
			+ "o.channelC, o.startdateC, o.salesentityC, o.enddateC, o.lkpSubbrandC) "
			+ "from ObjetivocuantitativoC o where o.salesentityC IN ?1 and o.startdateC <= CURRENT_DATE and o.enddateC >= CURRENT_DATE "
			+ "group by o.lkpSubbrandC, o.enddateC, o.channelC, o.startdateC, o.salesentityC "
			+ "order by o.lkpSubbrandC asc")
	Iterable<ObjetivoGlobal> findCuantiGlobal(List<String> countryCodes);

}
