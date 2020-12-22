package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Userrole;

public interface UserRoleRepository extends PagingAndSortingRepository<Userrole, Integer> {

	@Query("select u.sfid from Userrole u where u.name IN ?1")
	List<String> findRolesIS(List<String> roles);

}
