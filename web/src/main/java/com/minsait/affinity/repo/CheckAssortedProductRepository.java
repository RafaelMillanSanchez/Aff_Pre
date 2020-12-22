package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.CheckAssortedpromoProductC;

public interface CheckAssortedProductRepository extends PagingAndSortingRepository<CheckAssortedpromoProductC, Integer>{


	@Query("select c from CheckAssortedpromoProductC c where c.accountAssproC = ?1 and c.fechaInicioC <= CURRENT_DATE and c.fechaFinC >= CURRENT_DATE")
	List<CheckAssortedpromoProductC> findSurtidosActuales(String shiptoid);
	
	
	@Query("select c from CheckAssortedpromoProductC c where c.accountPromoC = ?1 and c.fechaInicioC <= CURRENT_DATE and c.fechaFinC >= CURRENT_DATE")
	List<CheckAssortedpromoProductC> findPromosActuales(String clienteid);

}
