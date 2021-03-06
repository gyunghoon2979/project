package com.model2.mvc.service.product.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDAO dao;
	
	public ProductServiceImpl() {
	}
	
	public void setDao(ProductDAOImpl dao) {
		this.dao = dao;
	}

	public void addProduct(Product productVO) throws Exception{
		dao.insertProduct(productVO);
	}

	public Product getProduct(int prodNo) throws Exception{
		return dao.findProduct(prodNo);
	}

	public Map<String,Object> getProductList(Search searchVO) throws Exception{
		return dao.getProductList(searchVO);
	}

	public void updateProduct(Product productVO) throws Exception{
		dao.updateProduct(productVO);
	}
}