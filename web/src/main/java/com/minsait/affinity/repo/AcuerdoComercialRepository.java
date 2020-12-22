package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.AcuerdoComercialC;

public interface AcuerdoComercialRepository extends PagingAndSortingRepository<AcuerdoComercialC, Integer> {

	AcuerdoComercialC findByAccountC(String shiptoid);

	@Query("select a from AcuerdoComercialC a where a.accountC = ?1 and a.fechaInicioC <= CURRENT_DATE and a.fechaFinC >= CURRENT_DATE")
	List<AcuerdoComercialC> findAcuerdos(String shiptoid);

}
