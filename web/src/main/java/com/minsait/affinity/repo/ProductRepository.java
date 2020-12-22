package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.minsait.affinity.jpa.model.Product2;
import com.minsait.affinity.jpa.model.Productos;

public interface ProductRepository extends PagingAndSortingRepository<Product2, Integer> {
	
	@Query("select p from Product2 p where p.sfid IN :ids")
	Iterable<Product2> findProductsBySfid(@Param("ids")List<String> prodIds);
	
	
	@Query("select new com.minsait.affinity.jpa.model.Productos(p.sfid, p.productcode, p.name, m.pckItemStatusC, m.longTermPromotionalC, m.chkActivePromotionalC, p.frmLineC, p.frmSubbrandC, p.numWeightC, p.numBoxesXPaletC, p.numUnitsBoxC, p.frmBaseReferenceCodeC, m.pckRoundingProfileC, p.pckItemTypeC, p.pckItemClassC) "
			+ "from Product2 p, MarketCatalogsC m "
			+ "where p.txtItemC != '' "
			+ "and m.chkActiveVisibilityDateC = true "
			+ "and m.pckItemStatusC IN ('R', 'P') "
			+ "and m.pckSalesEntityC = ?1 "
			+ "and m.pckChannelC = ?2 "
			+ "and p.sfid = m.itemC "
			+ "and p.sfid NOT IN ?3 "
			+ "order by "
			+ "p.frmSubbrandOrderC asc nulls last, "
			+ "p.frmLineOrderC asc nulls last, "
			+ "p.txtOrderC asc nulls last, "
			+ "p.numLWeightC asc nulls last, "
			+ "m.pckItemStatusC desc")
	List<Productos> findOrderProductos(String sales, String channel, List<String> inactiveProds);


	Product2 findBySfid(String product2id);

	@Query("select p from Product2 p where p.sfid IN ?1")
	Iterable<Product2> findProductsBySfid(Set<String> prodIds);

	@Query("select p from Product2 p where p.productcode IN ?1")
	Iterable<Product2> findProductsByCode(List<String> prodIds);

	@Query("select p from Product2 p where p.productcode IN ?1")
	Iterable<Product2> findProductsByCode(Set<String> keySet);

	@Query("select p from Product2 p where p.frmBaseReferenceCodeC IN ?1")
	Iterable<Product2> findByBaseReference(Set<String> baseReferences);


	Product2 findByProductcode(String code);


	List<Product2> findByBaseReferenceC(String lkpBaseReferenceC);


	@Query("select p.productcode "
			+ "from Product2 p, MarketCatalogsC m "
			+ "where p.txtItemC != '' "
			+ "and m.chkActiveVisibilityDateC = true "
			+ "and m.pckItemStatusC IN ('R', 'P') "
			+ "and m.pckSalesEntityC = ?1 "
			+ "and m.pckChannelC = ?2 "
			+ "and p.sfid = m.itemC "
			+ "and p.sfid NOT IN ?3 "
			+ "and p.sfid IN ?4")
	List<String> findVisibleProds(String pckSalesEntityC, String pckChannelC, List<String> inactiveProds, Set<String> productids);

	
	

	
}