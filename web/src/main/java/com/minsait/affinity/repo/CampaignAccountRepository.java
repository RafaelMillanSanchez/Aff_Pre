package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.LightCampaignAccountC;

public interface CampaignAccountRepository extends PagingAndSortingRepository<LightCampaignAccountC, Integer> {
	
	public LightCampaignAccountC findBySfid(String sfid);

	public List<LightCampaignAccountC> findByLkpAccountC(String lkpAccountC);
	

	
}