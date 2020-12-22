package com.minsait.affinity.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.LightMessagesC;


public interface MessageRepository extends PagingAndSortingRepository<LightMessagesC, Integer> {
	

	@Query("select msg from LightMessagesC msg where msg.lightRecipientuserC = ?1 and msg.lightStartdateC <= CURRENT_DATE and msg.lightEnddateC >= CURRENT_DATE")
	Iterable<LightMessagesC> Mensajesrdc(String rdcs);
}

