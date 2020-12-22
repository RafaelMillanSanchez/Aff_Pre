package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Attachment;

public interface AttachmentRepository extends PagingAndSortingRepository<Attachment, Integer> {
	
	@Query("select a from Attachment a where a.parentid in ?1")
	Iterable<Attachment> findFotos(Set<String> set);
	
	@Query("select a from Attachment a where a.parentid in ?1")
	Iterable<Attachment> findFotos(List<String> productos);

}
