package com.minsait.affinity.repo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Order;
import com.minsait.affinity.jpa.model.OrderSummary;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
	
	public Order findBySfid(String sfid);
	
	//@Query("select count(o) from Order o where o.effectivedate = ?2 and o.accountid = ?1")
	//public Integer findOrders(String accountid, Date date);

	@Query("select o from Order o "
			+ "where o.accountid = ?1 and o.effectivedate = ?2 "
			+ "and o.ownerid = '0059E00000853H8QAI' and o.status = 'Draft' "
			+ "order by o.createddate desc")
	public List<Order> findLastOrderSaved(String shiptoid, Date date, PageRequest pageRequest);

	@Query("select new com.minsait.affinity.jpa.model.OrderSummary(o.sfid, o.ordernumber, o.dttPlannedDeliveryDateC, a.name, "
			+ "(select u.name from User u where u.sfid = o.ownerid), "
			+ "o.totalamount, o.numKgPlannedC, a.shippingstreet, a.shippingcity, o.sumTotalCommercialDiscountC, "
			+ "o.chkUrgentDeliveryC, o.chkCollectionC, o.conditionTypeValueC) "
			+ "from Order o, Account a "
			+ "where a.sfid = o.accountid "
			+ "and o.accountid IN (select a.sfid from Account a where a.lkpRdcC = ?1) "
			+ "and o.status = 'Sent_to_SAP' "
			+ "and ((o.effectivedate = ?2 and o.chkUrgentDeliveryC = false) or (o.effectivedate = ?3 and o.chkUrgentDeliveryC = true)) "
			+ "order by o.createddate desc")
	public List<OrderSummary> findOrderSummary(String username, Date date, Date date2);
	
	
    @Query("select o from Order o where o.accountid = ?1 and o.recordtypeid = ?2 and o.status = 'Budget' order by o.createddate desc")
	public Order findLastBudget(String shiptoid, String recTypeidPresu, PageRequest pageRequest);

	
    @Query("select o from Order o where o.accountid IN ?1 and o.effectivedate = ?2 and o.status = 'Confirmed'")
    public Iterable<Order> findOrders(Set<String> accountIds, Date dat);

    @Query("select o from Order o where o.accountid = ?1 and o.chkCollectionC = false and o.recordtypeid != ?2 and o.status = 'Sent_to_SAP' order by o.createddate desc")
    public Order findLastOrder(String shiptoid, String recTypeidPresu, PageRequest of);
	
}