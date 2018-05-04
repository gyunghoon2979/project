package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


@Controller
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@RequestMapping("/addPurchase.do")
	public String addPurchase(@ModelAttribute Purchase purchase, HttpServletRequest request, Model model, HttpSession session) throws Exception {
		
		purchase.setPurchaseProd(productService.getProduct(Integer.parseInt(request.getParameter("prono"))));
		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setTranCode("1");
		
		purchaseService.addPurchase(purchase);
		
		model.addAttribute("purchVO", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
		
	}
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView( HttpServletRequest request, Model model) throws Exception {
		
		model.addAttribute("prodvo", productService.getProduct(Integer.parseInt(request.getParameter("prod_no"))));
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	//확인완료
	
	@RequestMapping("getPurchase.do")
	public String getPurchase( HttpServletRequest request, Model model) throws Exception {

		model.addAttribute("purch", purchaseService.getPurchase(Integer.parseInt(request.getParameter("tranNo"))));
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	

}