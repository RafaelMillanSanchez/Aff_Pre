package com.minsait.affinity.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Recordtype;

public interface RecordTypeRepository extends PagingAndSortingRepository<Recordtype, Integer>{

	
	@Query("select r.sfid from Recordtype r where r.sobjecttype = ?2 and r.name = ?1")
	String findRecordType(String recName, String sobject);

	Recordtype findBySfid(String recordType);

}
