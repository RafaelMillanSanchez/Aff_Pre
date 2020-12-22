package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.MtPicklist;

public interface PicklistRepository extends PagingAndSortingRepository<MtPicklist, Integer> {
	
	@Query("select a "
			+ "from MtPicklist a "
			+ "where a.nombrepicklist = ?1 "
			+ "and a.dependenciavalor IN ?3 "
			+ "and a.dependenciapck = ?2 "
			+ "and a.dependenciavalor2 IN ?5 "
			+ "and a.dependenciapck2 = ?4 ")
	public List<MtPicklist> getPiklist(String pck, String relatedPk, String ValuePK, String relatedPk2, String ValuePK2);
	
	@Query("select a "
			+ "from MtPicklist a "
			+ "where a.nombrepicklist = ?1 "
			+ "and a.dependenciavalor IN ?3 "
			+ "and a.dependenciapck = ?2 ")
	public List<MtPicklist> getPiklist2(String pck, String relatedPk, String ValuePK);
	
	@Query("select a "
			+ "from MtPicklist a "
			+ "where a.nombrepicklist = ?1 ")
	public List<MtPicklist> getPiklist3(String pck);
}
