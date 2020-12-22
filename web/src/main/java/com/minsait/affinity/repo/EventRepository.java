package com.minsait.affinity.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.minsait.affinity.jpa.model.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

	@Modifying
	@Query("update Event e set e.startdatetime = ?1, e.enddatetime = ?2 where e.sfid = ?3")
	public void updateEvent(Date d, Date d2,@Param("id") String sfid);
	
	@Transactional
	@Modifying
	@Query("update Event e set e.activityStatusC = :cancel where e.type = 'Visit' and e.sfid IN :id")
	public int cancelRutas(@Param("cancel")String cancel, @Param("id")List<String> ids);

	public Event findBySfid(String sfid);

	public Integer save(List<Event> evtoup);

	@Query("select e from Event e where e.activitydate > ?2 and e.whatid = ?1 and e.type = 'Visit' and e.activityStatusC = 'Not Started' order by e.activitydate desc")
	public Event findProximaRuta(String clienteid, Date dat, PageRequest pageable);

	@Query("select e from Event e where e.whatid = ?1 and e.startdatetime = ?2")
	public Event rutaExists(String idShipTo, Date date);

	@Query("select e from Event e where e.sfid IN ?1")
	public Iterable<Event> findAllSfid(List<String> ids);


}
