package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.minsait.affinity.jpa.model.LightCampaignC;

public interface CampaignsRepository extends PagingAndSortingRepository<LightCampaignC, Integer> {
	
	
	@Query("select c from LightCampaignC c where c.sfid IN :ids and c.pckTypeC != 'Assortment' and c.datStartDateC <= CURRENT_DATE and c.datEndDateC >= CURRENT_DATE")
	public Iterable<LightCampaignC> findCampaigns(@Param("ids")List<String> campaignIds);
	
	@Query("select c from LightCampaignC c where c.sfid IN :ids and c.pckTypeC = 'Assortment' and c.datStartDateC <= CURRENT_DATE and c.datEndDateC >= CURRENT_DATE")
	public Iterable<LightCampaignC> findSurtidos(@Param("ids")List<String> campaignIds);
	
	

	
}