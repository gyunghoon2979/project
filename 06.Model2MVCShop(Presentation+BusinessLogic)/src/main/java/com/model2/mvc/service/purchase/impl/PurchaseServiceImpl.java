package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDAO dao;

	public void setDao(PurchaseDAOImpl dao) {
		this.dao = dao;
	}

	public PurchaseServiceImpl() {	}
	
	public void addPurchase(Purchase purchaseVO) throws Exception{
		dao.addPurchase(purchaseVO);
	}
	public Purchase getPurchase2(int ProdNo) throws Exception{
		return dao.getPurchase2(ProdNo);
	}
	
	public Purchase getPurchase(int tranNo) throws Exception{
		return dao.getPurchase(tranNo);
	}
	
	public Map<String,Object> getPurchaseList(Search searchVO,String buyerId, String cencel) throws Exception{
		return dao.getPurchaseList(searchVO,buyerId,cencel);
	}
	
	public Map<String,Object> getSaleList(Search searchVO) throws Exception{
		return null;
	}
	
	public void updatePurcahse(Purchase purchaseVO) throws Exception{
		dao.updatePurchase(purchaseVO);
	}
	
	public void updateTranCode(Purchase purchaseVO) throws Exception{
		dao.updateTranCode(purchaseVO);
	}
	
	public void updateFlag(Purchase purchaseVO) throws Exception {
		dao.updateFlag(purchaseVO);
	}
}
