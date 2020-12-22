package com.minsait.affinity.repo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {
	
	public Task findBySfid(String sfid);
	
	//@Query("select t from Task t where t.activitydate = ?2 and t.accountid = ?1 and t.odigoctiCallStartTimeC is not null")
	//public List<Task> findCalls(String clienteid, Date dat);

	//@Query("select count(t) from Task t where t.accountid = ?1 and t.description != ''")
	//public int findInformes(String clienteid);
	
	@Query("select t from Task t where t.whatid IN ?1 and t.type = 'Visit' and t.createddate >= ?2 and t.createddate < ?3")
	public Iterable<Task> findInformes(Set<String> accountIds, Date dat, Date nextDay);
	
}