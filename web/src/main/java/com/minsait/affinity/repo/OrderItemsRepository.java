package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Orderitem;

public interface OrderItemsRepository extends PagingAndSortingRepository<Orderitem, Integer> {

	Iterable<Orderitem> findByOrderid(String lkpLastOrderC);

	@Query("select oi from Orderitem oi where oi.orderid IN ?1")
	Iterable<Orderitem> findAllByOrderid(Set<String> orderIds);

}
