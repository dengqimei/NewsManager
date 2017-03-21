package com.deng.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deng.bean.Address;
import com.deng.bean.Catalog;
import com.deng.bean.News;
import com.deng.bean.User;
import com.deng.model.CatalogNewsModel;
import com.deng.service.IAddressService;
import com.deng.service.ICatalogService;
import com.deng.service.INewsService;
import com.deng.service.IUserService;

@Controller
public class BaseController {
	
	@Autowired
	private ICatalogService catalogService;
	@Autowired
	private INewsService newsService;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private IUserService userService;
	
	private List<Catalog> catalogList;
	private List<CatalogNewsModel> catalogNewsList;
	private List<News> newsList;
	private Catalog catalog;
	private News news;
	private List<Address> provinceList;
	private List<Address> cityList;
	private List<Address> countyList;
	
	@RequestMapping("/toIndex.action")
	public String toIndex(Model model){
		//System.out.println("in toindex...");
		showCatalog(model);
		catalogNewsList = newsService.findAllNews();
		model.addAttribute("catalogNewsList", catalogNewsList);
		return "index";
	}
	
	@RequestMapping("/toLogin.action")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/login.action")
	public String login(User user,Model model){
//		System.out.println("============");
//		System.out.println(userService.login(user));
		if("success".equals(userService.login(user))){
			showCatalog(model);
			catalogNewsList = newsService.findAllNews();
			model.addAttribute("catalogNewsList", catalogNewsList);
			return "index";
		}
		return "failed";
	}
	
	@RequestMapping("/toRegister.action")
	public String toRegister(Model model){
		System.out.println("in register......");
//		provinceList = addressService.findProvince();
//		model.addAttribute("provinceList", provinceList);
		return "register";
	}
	
	@RequestMapping("/register.action")
	public String register(User user,String sex,String address){
//		System.out.println("==========================");
//		System.out.println(user.toString());
//		System.out.println(sex);
//		System.out.println(address);
		userService.register(user);
		return "login";
	}
	
	@RequestMapping(value="/showProvince.action",consumes="application/json",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public List<Address> showProvince(){
//		System.out.println("in province......");
		provinceList = addressService.findProvince();
//		model.addAttribute("provinceList", provinceList);
		return provinceList;
		
	}
	
	@RequestMapping("/showCity.action")
	@ResponseBody
	public List<Address> showCity(String id){
//		System.out.println("in city......");
		String provinceId = id.substring(0,3);
//		System.out.println(provinceId);
		cityList = addressService.findCity(provinceId+"_00");
//		model.addAttribute("cityList", cityList);
		return cityList;
	}
	
	@RequestMapping("/showCounty.action")
	@ResponseBody
	public List<Address> showCounty(String id){
//		System.out.println("in county......");
		String cityId = id.substring(0,4);
//		System.out.println(cityId);
		countyList = addressService.findCounty(cityId+"%");
//		model.addAttribute("countyList", countyList);
		return countyList;
	}
	
	@RequestMapping("/toList.action")
	public String toList(Long c_id,Model model){
		showCatalog(model);
		newsList = newsService.findNewsByCatalog(c_id);
		catalog = catalogService.findCatalogById(c_id);
		model.addAttribute("newsList", newsList);
		model.addAttribute(catalog);
		return "list";
	}
	
	@RequestMapping("/toContent.action")
	public String toContent(Long id,Model model){
		showCatalog(model);
		news = newsService.findNewsById(id);
		model.addAttribute(news);
		return "content";
	}
	
	public void showCatalog(Model model){
		catalogList = catalogService.findAll();
		model.addAttribute("catalogList", catalogList);
	}
}
