package com.deng.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
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
	private List<UserNewsCommentModel> commentList;
	private List<LoginInfo> loginInfoList;
	
	//进入系统首页
	@RequestMapping("/toIndex.action")
	public String toIndex(String userid,Model model,HttpServletRequest request){
		if(userid!=null){
			showCatalog(model);
			catalogNewsList = newsService.findAllNews();
			model.addAttribute("catalogNewsList", catalogNewsList);
			User user = userService.findById(userid);
			String username = user.getName();
			String usertype = user.getType();
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("login", "");
			request.getSession().setAttribute("usertype", usertype);
		}else{
			showCatalog(model);
			catalogNewsList = newsService.findAllNews();
			model.addAttribute("catalogNewsList", catalogNewsList);
	//		request.getSession().setMaxInactiveInterval(5*60);
			String login = (String) request.getSession().getAttribute("login");
			String userName = (String) request.getSession().getAttribute("username");
			if(("".equals(login)||login==null)&&("".equals(userName)||userName==null)){
				request.getSession().setAttribute("login", "请登录");
				request.getSession().setAttribute("usertype", "1");
			}
		}
		return "index";
	}
	
	//跳转到登录页面
	@RequestMapping("/toLogin.action")
	public String toLogin(){
		return "login";
	}
	
	//用户登录
	@ResponseBody
	@RequestMapping(value="/login.action",produces={"text/html;charset=utf-8"})
	public String login(String uid,String pwd,HttpServletRequest request,Model model){
		String sessionId = request.getSession().getId();
		loginInfo = new LoginInfo(sessionId, uid, "", "", "");
		String message = userService.login(uid, pwd,loginInfo);
		if("success".equals(message)){
			return "index";
		}else{
			return message;
		}
	}
	
	/*//检查用户名是否存在
	@ResponseBody
	@RequestMapping(value="/checkUserId.action",produces={"text/html;charset=utf-8"})
	public String checkUserId(String userid){
		User user = userService.findById(userid);
		if(user==null){
			return "failed";
		}else{
			return "success";
		}
	}
	
	//检查登录密码是否正确
	@ResponseBody
	@RequestMapping(value="/checkLogin.action",produces={"text/html;charset=utf-8"})
	public String checkLogin(String userid,String password){
		User user = new User();
		user.setId(userid);
		user.setPassword(MD5.getInstance().getMD5ofStr(password));
		if("failed".equals(userService.login(user))){
			return "failed";
		}else{
			return "success";
		}
	}*/
	
	//用户退出登录
	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest request,Model model){
		String userName = (String) request.getSession().getAttribute("username");
		System.out.println("=================");
		System.out.println(userName);
		System.out.println(loginInfo);
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
	
	/*//跳转到新闻列表页面
	@RequestMapping("/toList.action")
	public String toList(Long c_id,Model model){
		showCatalog(model);
		newsList = newsService.findNewsByCatalog(c_id);
		catalog = catalogService.findCatalogById(c_id);
		model.addAttribute("newsList", newsList);
		model.addAttribute("catalog",catalog);
		return "list";
	}*/
	
	//跳转到新闻列表页面
	@RequestMapping("/toList.action")
	public String toList(Long c_id,Model model,Integer currPage){
		showCatalog(model);
		int pageSize = 5;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = newsService.getCatalogPageCount(pageSize,c_id);
		newsList = newsService.findNewsByCatalog(c_id,offset,pageSize);
		catalog = catalogService.findCatalogById(c_id);
		model.addAttribute("newsList", newsList);
		model.addAttribute("catalog",catalog);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		return "list";
	}
	
	//查询栏目下新闻信息、分页
	@RequestMapping("/listNews.action")
	public String searchNews(Long c_id,Model model,Integer currPage){
		int pageSize = 5;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = newsService.getInusePageCount(pageSize, c_id);
		newsList = newsService.findInuseNewsByCatalog(c_id, offset, pageSize);
		catalog = catalogService.findCatalogById(c_id);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		model.addAttribute("newsList", newsList);
		model.addAttribute("catalog", catalog);
		return "c_news";
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
	
	//跳转到用户信息页面
	@RequestMapping("/toUserInfo.action")
	public String toUserInfo(String userName,Model model){
		User user = userService.findByName(userName);
		catalogList = catalogService.findAllUserInuse();
		model.addAttribute(user);
		model.addAttribute("catalogList", catalogList);
		showAddress(user, model);
		return "userInfo";
	}
	
	//显示所有系统栏目
	public void showCatalog(Model model){
		catalogList = catalogService.findAllInuse();
		model.addAttribute("catalogList", catalogList);
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
		
	//显示评论信息
	@ResponseBody
	@RequestMapping("showComment.action")
	public List<UserNewsCommentModel> showComment(Long newsId){
		List<UserNewsCommentModel> list = commentService.findNewsComments(newsId);
		return list;
	}
	
	//保存评论信息
	@ResponseBody
	@RequestMapping("saveComment.action")
	public void saveComment(Long newsId,String content,String username){
		User user = userService.findByName(username);
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setNews_id(newsId);
		comment.setUser_id(user.getId());
		commentService.addComment(comment);
	}
	
	//检查该用户是否已经对该新闻进行评论
	@ResponseBody
	@RequestMapping(value="checkIsComment.action",produces={"text/html;charset=utf-8"})
	public String checkIsComment(String userName,Long newsId){
		boolean flag = commentService.isComment(userName, newsId);
		if(flag){
			return "您已经评论过该新闻，请不要重复评论！！！";
		}else{
			return "success";
		}
	}
	
	@RequestMapping("/showUserInfo.action")
	public String showUserInfo(String userName,Integer currPage,String catalogName,Model model){
		User user = userService.findByName(userName);
		if("修改密码".equals(catalogName)){
			return "updPassword";
		}else if("我的评论".equals(catalogName)){
			int pageSize = 8;
			if(currPage==null)currPage=1;
			int offset = (currPage-1)*pageSize;
			int pageCount = commentService.getPageCountByUser(pageSize,user.getId());
			commentList = commentService.findUserComment(user.getId(),offset,pageSize);
			model.addAttribute("commentList",commentList);
			model.addAttribute("pageCount",pageCount);
			model.addAttribute("currPage", currPage);
			return "myComment";
		}else if("我的登录信息".equals(catalogName)){
			int pageSize = 8;
			if(currPage==null)currPage=1;
			int offset = (currPage-1)*pageSize;
			int pageCount = userService.getLoginInfoPageCount(pageSize, user.getId());
			loginInfoList = userService.findLoginInfo(user.getId(), offset, pageSize);
			model.addAttribute("loginInfoList",loginInfoList);
			model.addAttribute("pageCount",pageCount);
			model.addAttribute("currPage", currPage);
			return "myLoginInfo";
		}else{
			catalogList = catalogService.findAllUserInuse();
			model.addAttribute(user);
			model.addAttribute("catalogList", catalogList);
			showAddress(user, model);
			return "myInfo";
		}
	}
	
	//修改用户信息
	@RequestMapping("/updUser.action")
	public String updUser(User user,Model model){
		userService.updateUser(user);
		User user1 = userService.findById(user.getId());
		catalogList = catalogService.findAllUserInuse();
		model.addAttribute("user",user1);
		model.addAttribute("catalogList", catalogList);
		showAddress(user1, model);
		return "userInfo";
	}
	
	//修改密码
	@ResponseBody
	@RequestMapping(value="updPassword.action",produces={"text/html;charset=utf-8"})
	public String updPassword(String userName,String oldPWD,String newPWD){
		String message = userService.updPassword(userName,oldPWD,newPWD);
		return message;
	}
	
	//批量删除评论
	@ResponseBody
	@RequestMapping(value="/batchDelComment.action",produces={"text/html;charset=UTF-8;"})
	public String batchDelComment(@Param("delids")String[] delids){
		int result = commentService.batchDel(delids);
		if(delids.length==result){
			return result+"条评论删除成功！！！";
		}else{
			return "删除失败！！！";
		}
	}
	
}
