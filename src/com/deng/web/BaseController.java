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
	
	//进入系统首页、退出登录
	@RequestMapping("/toIndex.action")
	public String toIndex(Model model,HttpServletRequest request){
		showCatalog(model);
		catalogNewsList = newsService.findAllNews();
		model.addAttribute("catalogNewsList", catalogNewsList);
		String login = (String) request.getSession().getAttribute("login");
		String userName = (String) request.getSession().getAttribute("username");
		if(("".equals(login)||login==null)&&("".equals(userName)||userName==null)){
			request.getSession().setAttribute("login", "请登录");
		}
		request.getSession().setMaxInactiveInterval(5*60);
		return "index";
	}
	
	//跳转到登录页面
	@RequestMapping("/toLogin.action")
	public String toLogin(){
		return "login";
	}
	
	//用户登录
	@RequestMapping("/login.action")
	public String login(User user,HttpServletRequest request,Model model){
		if(user.getId()!=null){
			if("success".equals(userService.login(user))){
				showCatalog(model);
				catalogNewsList = newsService.findAllNews();
				model.addAttribute("catalogNewsList", catalogNewsList);
				String userName = userService.findById(user.getId()).getName();
				request.getSession().setAttribute("login", "");
				request.getSession().setAttribute("username", userName);
				loginInfo = new LoginInfo();
				loginInfo.setSessionId(request.getSession().getId());
				loginInfo.setUserId(user.getId());
				loginInfo.setUserName(userName);
				userService.saveLoginInfo(loginInfo);
				return "index";
			}else{
				request.getSession().setAttribute("msg", "登录失败，用户名或者密码错误！");
				return "failed";
			}
		}else{
			String userName = (String) request.getSession().getAttribute("username");
			if(userName==null){
				request.getSession().setAttribute("login", "请登录");
			}else{
				request.getSession().setAttribute("username", userName);
			}
			showCatalog(model);
			catalogNewsList = newsService.findAllNews();
			model.addAttribute("catalogNewsList", catalogNewsList);
			return "index";
		}
	}
	
	//用户退出登录
	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest request,Model model){
		String userName = (String) request.getSession().getAttribute("username");
		if(loginInfo!=null&&userName!=null){
			request.getSession().setAttribute("username","");
			request.getSession().setAttribute("login", "请登录");
			userService.Logout(loginInfo);
			showCatalog(model);
			catalogNewsList = newsService.findAllNews();
			model.addAttribute("catalogNewsList", catalogNewsList);
			return "index";
		}
		showCatalog(model);
		catalogNewsList = newsService.findAllNews();
		model.addAttribute("catalogNewsList", catalogNewsList);
		String login = (String) request.getSession().getAttribute("login");
		if(("".equals(login)||login==null)&&("".equals(userName)||userName==null)){
			request.getSession().setAttribute("login", "请登录");
		}
		return "index";
	}
	
	//跳转到用户注册页面
	@RequestMapping("/toRegister.action")
	public String toRegister(Model model){
		return "register";
	}
	
	//用户注册
	@RequestMapping("/register.action")
	public String register(User user,String sex,String address){
		userService.register(user);
		return "login";
	}
	
	//显示所有省份
	@ResponseBody
	@RequestMapping(value="/showProvince.action",consumes="application/json",method=RequestMethod.POST,produces="application/json")
	public List<Code> showProvince(){
		provinceList = codeService.findAllProvince();
		return provinceList;
		
	}
	
	//显示省份中所有的城市
	@ResponseBody
	@RequestMapping("/showCity.action")
	public List<Code> showCity(String id){
		String provinceId = id.substring(0,3);
		cityList = codeService.findCityByProvince(provinceId+"_00");
		return cityList;
	}
	
	//显示城市中的所有县
	@ResponseBody
	@RequestMapping("/showCounty.action")
	public List<Code> showCounty(String id){
		String cityId = id.substring(0,4);
		countyList = codeService.findCountyByCity(cityId+"%");
		return countyList;
	}
	
	//跳转到新闻列表页面
	@RequestMapping("/toList.action")
	public String toList(Long c_id,Model model){
		showCatalog(model);
		newsList = newsService.findNewsByCatalog(c_id);
		catalog = catalogService.findCatalogById(c_id);
		model.addAttribute("newsList", newsList);
		model.addAttribute(catalog);
		return "list";
	}
	
	//跳转到新闻详情页面
	@RequestMapping("/toContent.action")
	public String toContent(Long id,Model model){
		showCatalog(model);
		news = newsService.findNewsById(id);
		model.addAttribute(news);
		return "content";
	}
	
	//显示所有栏目
	public void showCatalog(Model model){
		catalogList = catalogService.findAll();
		model.addAttribute("catalogList", catalogList);
	}
	
	//跳转到用户信息页面
	@RequestMapping("/toUserInfo.action")
	public String toUserInfo(String userName,Model model){
		User user = userService.findByName(userName);
		model.addAttribute(user);
		showAddress(user, model);
		return "userInfo";
	}
	
	//显示地址
		public void showAddress(User user,Model model){
			String addressId = user.getAddress();
			String provinceId = addressId.substring(0, 2)+"0000";
			String cityId = addressId.substring(0,4)+"00";
			Code province = codeService.findProvinceById(provinceId);
			Code city = codeService.findCityById(cityId);
			Code address = codeService.findCountyById(addressId);
			model.addAttribute("province",province);
			model.addAttribute("city",city);
			model.addAttribute("address",address);
		}
	
}
