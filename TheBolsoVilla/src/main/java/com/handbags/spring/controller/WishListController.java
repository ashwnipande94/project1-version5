package com.handbags.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.handbags.spring.Service.ProductServices;
import com.handbags.spring.Service.UserDetailService;
import com.handbags.spring.Service.WishListServices;
import com.handbags.spring.model.Product;
import com.handbags.spring.model.WishList;

@Controller
public class WishListController {
	
	@Autowired
	WishListServices wishListServices;
	
	@Autowired
	UserDetailService userDetailService;
	
	@Autowired
	ProductServices productServices;
	
	
	@RequestMapping("wishList-{productId}")
	public String buynow(@PathVariable("productId")int productId, @ModelAttribute("wishList")WishList wishList,Model model,@RequestParam("userId")int userId,Product product ,HttpSession httpSession){
		
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String username=auth.getName();
	userId=userDetailService.getUserByName(username).getUserId();
	
	wishList.setCartId(userId);
	wishList.setUserId(userId);
	wishList.setProductId(productId);
	
    String productName=productServices.getProductById(productId).getProductName();
	wishList.setProductName(productName);
	
	int productPrice=productServices.getProductById(productId).getProductPrice();
	wishList.setProductPrice(productPrice);
	
	int productDiscount=productServices.getProductById(productId).getProductDiscount();
	wishList.setProductDiscount(productDiscount);
	
	wishListServices.addWishList(wishList);
	productServices.updateQuantity(productId);
	
	return "WishList";
			
}


}
