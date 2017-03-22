package com.deng.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deng.bean.Code;
import com.deng.bean.LoginInfo;
import com.deng.bean.Catalog;
import com.deng.bean.News;
import com.deng.bean.User;
import com.deng.model.CatalogNewsModel;
import com.deng.service.ICodeService;
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
	private ICodeService codeService;
	@Autowired
	private IUserService userService;
	
	private List<Catalog> catalogList;
	private List<CatalogNewsModel> catalogNewsList;
	private List<News> newsList;
	private Catalog catalog;
	private News news;
	private List<Code> provinceList;
	private List<Code> cityList;
	private List<Code> countyList;
	private LoginInfo loginInfo;
	
	@RequestMapping("/toIndex.action")
	public String toIndex(Model model,HttpServletRequest request,User user){
		showCatalog(model);
		catalogNewsList = newsService.findAllNews();
		model.addAttribute("catalogNewsList", catalogNewsList);
		request.getSession().setAttribute("login", "请登录");
		request.getSession().setAttribute("username", "");
		request.getSession().setMaxInactiveInterval(5*60);
		String islogin = (String) request.getSession().getAttribute("login");
		String userName = (String) request.getSession().getAttribute("username");
		if("请登录".equals(islogin)&&"".equals(userName)){
			if(user.getId()!=null){
				login(user, request);
			}
		}else if(loginInfo!=null&&!"".equals(userName)){
			logout(request);
		}
		return "index";
	}
	
	@RequestMapping("/toLogin.action")
	public String toLogin(){
		return "login";
	}
	
	public String login(User user,HttpServletRequest request){
		if("success".equals(userService.login(user))){
//			showCatalog(model);
//			catalogNewsList = newsService.findAllNews();
//			model.addAttribute("catalogNewsList", catalogNewsList);
			String userName = userService.findById(user.getId()).getName();
			request.getSession().setAttribute("login", "");
			request.getSession().setAttribute("username", userName);
			loginInfo = new LoginInfo();
			loginInfo.setSessionId(request.getSession().getId());
			loginInfo.setUserId(user.getId());
			loginInfo.setUserName(userName);
			userService.saveLoginInfo(loginInfo);
			return "index";
		}
		return "failed";
	}
	
	public String logout(HttpServletRequest request){
		userService.setLogoutTime(loginInfo);
		System.out.println("===================");
		System.out.println(request.getSession().getAttribute("username"));
		request.getSession().setAttribute("username","");
		request.getSession().setAttribute("login", "请登录");
//		showCatalog(model);
//		catalogNewsList = newsService.findAllNews();
//		model.addAttribute("catalogNewsList", catalogNewsList);
		return "index";
	}
	
	@RequestMapping("/toRegister.action")
	public String toRegister(Model model){
		return "register";
	}
	
	@RequestMapping("/register.action")
	public String register(User user,String sex,String address){
		userService.register(user);
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="/showProvince.action",consumes="application/json",method=RequestMethod.POST,produces="application/json")
	public List<Code> showProvince(){
		provinceList = codeService.findProvince();
		return provinceList;
		
	}
	
	@ResponseBody
	@RequestMapping("/showCity.action")
	public List<Code> showCity(String id){
		String provinceId = id.substring(0,3);
		cityList = codeService.findCity(provinceId+"_00");
		return cityList;
	}
	
	@ResponseBody
	@RequestMapping("/showCounty.action")
	public List<Code> showCounty(String id){
		String cityId = id.substring(0,4);
		countyList = codeService.findCounty(cityId+"%");
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
