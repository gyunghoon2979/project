package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Test
	public void testAddPurchase() throws Exception {

		
		Purchase purchase = new Purchase();
		
		User user = new User();
		user.setUserId("user01");
		
		Product product = new Product();
		product.setProdNo(10000);
		
		purchase.setBuyer(user);
		
		purchase.setPurchaseProd(product);
		System.out.println("-===");
		
		purchaseService.addPurchase(purchase);
	}
	
	//@Test
	public void testgetPurchase() throws Exception {
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10000);
		
		System.out.println(purchase);
	}
	
	//@Test
	public void testgetPurchase2() throws Exception {
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase2(10000);
		
		System.out.println(purchase);
	}
	
	//@Test
	public void tsetupdatePurchase() throws Exception{
		Purchase purchase = new Purchase();
		
		purchase.setTranNo(10004);
		purchase.setDivyAddr("����");
		purchase.setDivyDate("20100128");
		purchase.setDivyRequest("��û���׺���");
		purchase.setPaymentOption("2");
		purchase.setReceiverName("������");
		purchase.setReceiverPhone("010-3196-2979");
		purchase.setTranCode("2");
		
		purchaseService.updatePurcahse(purchase);
		
	}
	
	//@Test
	public void tsetupdatePurchaseTranCode() throws Exception{
		Purchase purchase = new Purchase();
		
		purchase.setTranNo(10004);
		purchase.setTranCode("10");
		
		purchaseService.updateTranCode(purchase);
		
	}
	
	//@Test
	public void updatePurchaseFlag() throws Exception{
		Purchase purchase = new Purchase();
	
		purchase.setTranNo(10004);
		
		purchaseService.updateFlag(purchase);
		
	}
	
	//@Test
	 public void testGetPurchaseAll() throws Exception{
		 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01", "cencel");
		 	
		 	Integer totalCount = (Integer)map.get("count");
		 	System.out.println(totalCount);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	
		 	System.out.println(list);
	 }
}