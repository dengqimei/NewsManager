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
import com.deng.bean.Comment;
import com.deng.bean.LoginInfo;
import com.deng.bean.Catalog;
import com.deng.bean.News;
import com.deng.bean.User;
import com.deng.model.CatalogNewsModel;
import com.deng.model.UserNewsCommentModel;
import com.deng.service.ICodeService;
import com.deng.service.ICommentService;
import com.deng.service.ICatalogService;
import com.deng.service.INewsService;
import com.deng.service.IUserService;
import com.deng.util.MD5;

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
	@Autowired
	private ICommentService commentService;
	
	private List<Catalog> catalogList;
	private List<CatalogNewsModel> catalogNewsList;
	private List<News> newsList;
	private Catalog catalog;
	private News news;
	private List<Code> provinceList;
	private List<Code> cityList;
	private List<Code> countyList;
	private LoginInfo loginInfo;
	
	//进入系统首页
	@RequestMapping("/toIndex.action")
	public String toIndex(Model model,HttpServletRequest request){
		showCatalog(model);
		catalogNewsList = newsService.findAllNews();
		model.addAttribute("catalogNewsList", catalogNewsList);
		request.getSession().setMaxInactiveInterval(5*60);
		String login = (String) request.getSession().getAttribute("login");
		String userName = (String) request.getSession().getAttribute("username");
		if(("".equals(login)||login==null)&&("".equals(userName)||userName==null)){
			request.getSession().setAttribute("login", "请登录");
			request.getSession().setAttribute("usertype", "1");
		}
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
		request.getSession().setMaxInactiveInterval(5*60);
		String password = MD5.getInstance().getMD5ofStr(user.getPassword());
		user.setPassword(password);
		if(user.getId()!=null){
			showCatalog(model);
			catalogNewsList = newsService.findAllNews();
			model.addAttribute("catalogNewsList", catalogNewsList);
			if("success".equals(userService.login(user))){
				String userName = userService.findById(user.getId()).getName();
				request.getSession().setAttribute("login", "");
				request.getSession().setAttribute("username", userName);
				loginInfo = new LoginInfo();
				loginInfo.setSessionId(request.getSession().getId());
				loginInfo.setUserId(user.getId());
				loginInfo.setUserName(userName);
				userService.saveLoginInfo(loginInfo);
				request.getSession().setAttribute("usertype", "1");
				return "index";
			}else if("manager".equals(userService.login(user))){
				String userName = userService.findById(user.getId()).getName();
				request.getSession().setAttribute("login", "");
				request.getSession().setAttribute("username", userName);
				loginInfo = new LoginInfo();
				loginInfo.setSessionId(request.getSession().getId());
				loginInfo.setUserId(user.getId());
				loginInfo.setUserName(userName);
				userService.saveLoginInfo(loginInfo);
				request.getSession().setAttribute("usertype", "0");
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
	
	//检查用户名是否存在
	@ResponseBody
	@RequestMapping(value="/checkUserId.action",produces={"text/html;charset=utf-8"})
	public String checkUserId(String userid){
		System.out.println(userid);
		User user = userService.findById(userid);
		if(user==null){
			return "failed";
		}else{
			return "success";
		}
	}
	
	//检查用户名是否存在
	@ResponseBody
	@RequestMapping(value="/checkLogin.action",produces={"text/html;charset=utf-8"})
	public String checkLogin(String userid,String password){
		User user = new User();
		user.setId(userid);
		user.setPassword(MD5.getInstance().getMD5ofStr(password));
		System.out.println("====================");
		System.out.println(user);
		System.out.println(userService.login(user));
		if("failed".equals(userService.login(user))){
			return "failed";
		}else{
			return "success";
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
			request.getSession().setAttribute("usertype", "1");
			return "index";
		}
		showCatalog(model);
		catalogNewsList = newsService.findAllNews();
		model.addAttribute("catalogNewsList", catalogNewsList);
		String login = (String) request.getSession().getAttribute("login");
		if(("".equals(login)||login==null)&&("".equals(userName)||userName==null)){
			request.getSession().setAttribute("login", "请登录");
			request.getSession().setAttribute("usertype", "1");
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
		String password = MD5.getInstance().getMD5ofStr(user.getPassword());
		user.setPassword(password);
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
		model.addAttribute("catalog",catalog);
		return "list";
	}
	
	//跳转到新闻详情页面
	@RequestMapping("/toContent.action")
	public String toContent(Long id,Model model){
		showCatalog(model);
		news = newsService.findNewsById(id);
		catalog = catalogService.findCatalogById(news.getCatalog_id());
		model.addAttribute(news);
		model.addAttribute("catalog",catalog);
		return "content";
	}
	
	//显示所有栏目
	public void showCatalog(Model model){
		catalogList = catalogService.findAllInuse();
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
		
		
	@RequestMapping("/toTest.action")
	public String toTest(){
		return "test";
	}
	
	@ResponseBody
	@RequestMapping("showComment.action")
	public List<UserNewsCommentModel> showComment(Long newsId){
		List<UserNewsCommentModel> list = commentService.findNewsComments(newsId);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("saveComment.action")
	public void saveComment(Long newsId,String content,String username){
		System.out.println("==============");
		User user = userService.findByName(username);
		System.out.println(content);
		System.out.println(newsId);
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setNews_id(newsId);
		comment.setUser_id(user.getId());
		commentService.addComment(comment);
	}
	
}
