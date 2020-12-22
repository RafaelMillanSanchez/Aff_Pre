package com.minsait.affinity.repo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.minsait.affinity.jpa.model.Account;
import com.minsait.affinity.jpa.model.Rutas;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

	public Account findBySfid(String sfid);

	public Account deleteBySfid(String salesforceId);
	
	@Query("select new com.minsait.affinity.jpa.model.Rutas(a, (select a2 from Account a2 where a2.sfid = a.parentid), e) "
			+ "from Account a, Event e "
			+ "where e.whatid = a.sfid "
			+ "and a.lkpRdcC IN :usernames "
			+ "and a.recordtypeid IN :rectype "
			+ "and e.type = 'Visit' "
			+ "and e.ownerid IN :usernames "
			+ "and ((e.activitydate = :date) or (e.activitydate < :date and e.activitydate > :lastdate and e.activityStatusC = 'Not Started')) "
			+ "order by e.activitydate desc")
	public List<Rutas> rutasAccount(@Param("usernames") List<String> rdcs, @Param("date") Date date, @Param("rectype") List<String> rectypeid, @Param("lastdate") Date lastweek);
	
	
	
	
	
	/*@Query("select a from Account a "
			+ "where a.lkpRdcC IN ?7 "
			+ "and a.recordtypeid IN ?8 "
			+ "and (?1 = '' or lower(a.name) like lower(CONCAT('%', ?1, '%'))) " 
			+ "and (?2 = '' or lower(a.txtSapidC) like lower(CONCAT('%', ?2, '%'))) "
			+ "and (?3 = '' or lower(a.frmlParentCustomerCodeC) like lower(CONCAT('%', ?3, '%'))) "
			+ "and (?4 = '' or lower(a.shippingcity) like lower(CONCAT('%', ?4, '%'))) "
			+ "and (?5 = '' or lower(a.shippingpostalcode) like lower(CONCAT('%', ?5, '%'))) "
			+ "and (a.parentid IN (select b.sfid from Account b where (?6 = '' or lower(b.name) like lower(CONCAT('%', ?6, '%')))) or a.parentid is null)")
	public Iterable<Account> findShipTo(String shiptoname, String shiptocode, String clientcode, String poblacion, String postalcode, String clientname, List<String> rdcs, List<String> recTypeIds);*/

	public Account findByParentid(String parentid);

	@Query("select a from Account a where a.sfid IN ?1")
	public Iterable<Account> findAllSfid(Set<String> shiptoids);
	
	
	@Query("SELECT a "
			+ "FROM Account a "
			+ "WHERE a.lkpRdcC IN ?1 "
			+ "and a.recordtypeid IN ?2 "
			+ "and a.pckCustomerStatusC NOT IN ('C', 'D') "
			+ "and (a.txtBloqueoPedidoC IS NULL or a.txtBloqueoPedidoC != 'ZT')")
	public List<Account> findShipToByDistance(List<String> rdcs, List<String> recTypeIds);

	
	@Query("select a from Account a "
			+ "where a.recordtypeid IN ?5 "
			+ "and a.pckSalesEntityC IN ?4 "
			+ "and a.pckCustomerStatusC NOT IN ('C', 'D') "
			+ "and (a.txtBloqueoPedidoC IS NULL or a.txtBloqueoPedidoC != 'ZT') "
			+ "and (?2 = '' or lower(a.name) like lower(CONCAT('%', ?2, '%'))) " 
			+ "and (?1 = '' or lower(a.mailSapEmail1C) like lower(CONCAT('%', ?1, '%'))) "
			+ "and (?3 = '' or lower(a.phoneSapPhone2C) like lower(CONCAT('%', ?3, '%')))")
	public Iterable<Account> findAllAccounts(String email, String name, String phone, List<String> countryCodes, List<String> recTypeIds);

	
	@Query("select a from Account a where a.billingcountry = ?1 and a.recordtypeid = ?2")
	public List<Account> findDirectos(String country, String recId);

	@Query("select a from Account a "
			+ "where a.lkpRdcC IN ?7 "
			+ "and a.recordtypeid = ?8 "
			+ "and a.pckCustomerStatusC NOT IN ('C', 'D') "
			+ "and (a.txtBloqueoPedidoC IS NULL or a.txtBloqueoPedidoC != 'ZT') "
			+ "and (?1 = '' or lower(a.name) like lower(CONCAT('%', ?1, '%'))) " 
			+ "and (?2 = '' or lower(a.txtSapidC) like lower(CONCAT('%', ?2, '%'))) "
			+ "and (?3 = '' or lower(a.frmlParentCustomerCodeC) like lower(CONCAT('%', ?3, '%'))) "
			+ "and (?4 = '' or lower(a.shippingcity) like lower(CONCAT('%', ?4, '%'))) "
			+ "and (?5 = '' or lower(a.shippingpostalcode) like lower(CONCAT('%', ?5, '%'))) "
			+ "and (a.parentid IN (select b.sfid from Account b where (?6 = '' or lower(b.name) like lower(CONCAT('%', ?6, '%')))))")
	public List<Account> findShipTo(String shiptoName, String shiptoCode, String clientCode, String poblacion,
			String postalcode, String clientName, List<String> rdcs, String recTypeShipto);

	
	/*@Query("select a from Account a "
			+ "where a.lkpRdcC IN ?6 "
			+ "and a.recordtypeid = ?7 "
			+ "and (?1 = '' or lower(a.name) like lower(CONCAT('%', ?1, '%'))) " 
			+ "and (?2 = '' or lower(a.txtSapidC) like lower(CONCAT('%', ?2, '%'))) "
			+ "and (?3 = '' or lower(a.frmlParentCustomerCodeC) like lower(CONCAT('%', ?3, '%'))) "
			+ "and (?4 = '' or lower(a.shippingcity) like lower(CONCAT('%', ?4, '%'))) "
			+ "and (?5 = '' or lower(a.shippingpostalcode) like lower(CONCAT('%', ?5, '%')))")
	public Iterable<Account> findPotential(String shiptoName, String shiptoCode, String clientCode, String poblacion,
			String postalcode, List<String> rdcs, String recTypePotential);*/
	
	@Query("select a from Account a "
			+ "where a.lkpRdcC IN ?6 "
			+ "and a.recordtypeid IN ?7 "
			+ "and a.pckCustomerStatusC NOT IN ('C', 'D') "
			+ "and (a.txtBloqueoPedidoC IS NULL or a.txtBloqueoPedidoC != 'ZT') "
			+ "and (?1 = '' or lower(a.name) like lower(CONCAT('%', ?1, '%'))) " 
			+ "and (?2 = '' or lower(a.txtSapidC) like lower(CONCAT('%', ?2, '%'))) "
			+ "and (?3 = '' or lower(a.frmlParentCustomerCodeC) like lower(CONCAT('%', ?3, '%'))) "
			+ "and (?4 = '' or lower(a.shippingcity) like lower(CONCAT('%', ?4, '%'))) "
			+ "and (?5 = '' or lower(a.shippingpostalcode) like lower(CONCAT('%', ?5, '%')))")
	public List<Account> findPotential(String shiptoName, String shiptoCode, String clientCode, String poblacion,
			String postalcode, List<String> rdcs, List<String> recTypeIds);
}
