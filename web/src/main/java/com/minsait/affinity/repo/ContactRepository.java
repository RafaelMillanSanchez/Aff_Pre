package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Contact;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
	
	public Contact findBySfid(String sfid);
	
	
	@Query("select c from Contact c where c.accountid = ?1 and c.mainContactC = true")
	public Contact findMainContact(String clienteid, PageRequest pageRequest);

	@Query("select c from Contact c where c.accountid IN ?1 and c.mainContactC = true")
	public Iterable<Contact> findMainContacts(Set<String> accountIds);
	
}