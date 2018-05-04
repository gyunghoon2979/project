package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


@Controller
public class ProductController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute Product product , HttpServletRequest request) throws Exception {
		
		productService.addProduct(product);
		
		request.setAttribute("prodvo", product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Product vo= productService.getProduct(Integer.parseInt(request.getParameter("prodNo"))); 

		request.setAttribute("provo", vo);
		
		if(request.getParameter("menu").equals("manage")) {
			return "forward:/product/updateProduct.jsp";
		}else {
			String history=null;
			
			Integer no = new Integer(vo.getProdNo());
			
			Cookie[] cookies = request.getCookies();
			
			 if(cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equals("history")) {
						history = cookie.getValue();
					}
				}
			}
			
			history+=","+no.toString();
			Cookie cookie = new Cookie("history",history);
			
			response.addCookie(cookie); 
			return "forward:/product/getProductDetail.jsp";
		}
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search, Model model , HttpServletRequest request) throws Exception {
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String,Object> map=productService.getProductList(search);	
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("searchVO", search);
		model.addAttribute("menu",  request.getParameter("menu"));
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("product") Product productVO ,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		productService.updateProduct(productVO);
		
		request.setAttribute("provo", productVO);
		
		return "forward:/product/getProductDetail.jsp?menu=manage";
	
	}	
	
}