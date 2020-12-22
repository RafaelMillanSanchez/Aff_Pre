package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.minsait.affinity.jpa.model.LightCampaignProductC;

public interface CampaignProductRepository extends PagingAndSortingRepository<LightCampaignProductC, Integer> {
	
	
	@Query("select c from LightCampaignProductC c where c.lkpCampaignC IN :ids")
	public List<LightCampaignProductC> findCampaignsProducts(@Param("ids")Set<String> campaignIds);
	
	

	
}