package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.MarketCatalogsC;

public interface CatalogRepository extends PagingAndSortingRepository<MarketCatalogsC, Integer> {
	
	@Query("select c from MarketCatalogsC c where c.chkActiveVisibilityDateC = true and c.pckSalesEntityC IN ?1 and c.pckItemStatusC = 'P' and c.datePromotionalStartDateC <= CURRENT_DATE and c.datePromotionalEndDateC >= CURRENT_DATE")
	Iterable<MarketCatalogsC> findCatalogs(List<String> countryCodes);
	
	@Query("select c from MarketCatalogsC c where c.itemC = ?1 and c.chkActiveVisibilityDateC = true and c.pckSalesEntityC = ?2 and c.pckItemStatusC = 'P' and c.pckChannelC = ?3 and c.datePromotionalStartDateC <= CURRENT_DATE and c.datePromotionalEndDateC >= CURRENT_DATE")
	MarketCatalogsC findMarketCatalog(String product, String pckSalesEntityC, String pckChannelC, PageRequest pageRequest);
	
	@Query("select c from MarketCatalogsC c where c.itemC IN ?1 and c.chkActiveVisibilityDateC = true and c.pckSalesEntityC = ?2 and c.pckItemStatusC = 'P' and c.pckChannelC = ?3 and c.datePromotionalStartDateC <= CURRENT_DATE and c.datePromotionalEndDateC >= CURRENT_DATE")
	Iterable<MarketCatalogsC> findMarketCatalog(Set<String> keySet, String pckSalesEntityC, String pckChannelC);
	

	
}