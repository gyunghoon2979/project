package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;

@Repository("purchaseDaoImpl")
public class PurchaseDAOImpl implements PurchaseDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public PurchaseDAOImpl() {}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public Purchase getPurchase2(int prodNo) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getPurchase2", prodNo);
	}
	
	public Purchase getPurchase(int tranNo) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public Map<String,Object> getPurchaseList(Search searchVO,String buyerId,String cencel) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("search", searchVO);
		map.put("buyer", buyerId);
		map.put("cencel", cencel);
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getList", map);
		int count = sqlSession.selectOne("PurchaseMapper.getTotalCount", map);
		System.out.println(count);
		map.put("list", list);
		map.put("count", count);
		
		return map;
	}
	
	public void addPurchase(Purchase purch) throws Exception{
		sqlSession.insert("PurchaseMapper.addPurchase", purch);
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchaseTranCode", purchase);
	}
	
	public void updateFlag(Purchase purch) throws Exception{
		sqlSession.update("PurchaseMapper.updatePurchaseFlag", purch);
	}
}
