package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;

@Repository("productDaoImpl")
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public ProductDAOImpl() {
	}

	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",Integer.valueOf(prodNo));
	}

	public Map<String, Object> getProductList(Search searchVO) throws Exception {
		
		List<Product> list= sqlSession.selectList("ProductMapper.getProductList",searchVO);
		int totalCount = sqlSession.selectOne("ProductMapper.getTotalCount",searchVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateProduct(Product product) throws Exception {
		System.out.println("-----");
		
		sqlSession.update("ProductMapper.updateProduct", product);
	}

	@Override
	public void insertProduct(Product product) throws Exception {
		
		sqlSession.insert("ProductMapper.addProduct", product);
	}
}
