package com.deng.web.manager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.deng.bean.Catalog;
import com.deng.bean.Code;
//import com.deng.bean.LoginInfo;
import com.deng.bean.News;
import com.deng.bean.User;
import com.deng.model.UserModel;
import com.deng.model.UserNewsCommentModel;
import com.deng.service.ICatalogService;
import com.deng.service.ICodeService;
import com.deng.service.ICommentService;
import com.deng.service.INewsService;
import com.deng.service.IUserService;


@Controller
public class BaseManagerController {
	
	@Autowired
	private ICatalogService catalogService;
	@Autowired
	private INewsService newsService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICodeService codeService;
	@Autowired
	private ICommentService commentService;

	private List<Catalog> catalogList;
	private List<News> newsList;
	private Catalog catalog;
	private News news;
	private List<UserModel> userModelList;
	private List<UserNewsCommentModel> commentList;
//	private LoginInfo loginInfo;
	
	//跳转到管理界面
	@RequestMapping("/Manager/toIndex.action")
	public String toManagerIndex(){
		return "/manager/index";
	}
	
	//跳转到添加栏目页面
	@RequestMapping("/Manager/toAddCatalog.action")
	public String toAddCatalog(){
		return "/manager/addCatalog";
	}
	
	//添加栏目
	@RequestMapping("/Manager/addCatalog.action")
	public String addCatalog(Catalog catalog){
		catalogService.addCatalog(catalog);
		return "/manager/addCatalog";
	}
	
	//跳转到修改栏目页面
	@RequestMapping("/Manager/toUpdCatalog.action")
	public String toUpdCatalog(Long id,Model model){
		catalog = catalogService.findCatalogById(id);
		model.addAttribute(catalog);
		return "/manager/updCatalog";
	}
	
	//修改栏目信息
	@RequestMapping("/Manager/updCatalog.action")
	public String updCatalog(Catalog catalog){
		catalogService.updateCatalog(catalog);
		return "/manager/catalogManager";
	}
	
	//删除栏目
	@RequestMapping("/Manager/delCatalog.action")
	public String delCatalog(Long id){
		catalogService.deleteCatalogById(id);
		return "/manager/catalogManager";
	}
	
	//批量删除栏目
	@ResponseBody
	@RequestMapping(value="/Manager/batchDelCatalog.action",produces={"text/html;charset=UTF-8;"})
	public String batchDelCatalog(@Param("delids")String[] delids){
		int result = catalogService.batchDel(delids);
		if(delids.length==result){
			return result+"个用户删除成功！！！";
		}else{
			System.out.println("删除失败！！！");
			return "删除失败！！！";
		}
	}
	
	//批量启用栏目
	@ResponseBody
	@RequestMapping(value="/Manager/batchInUseCatalog.action",produces={"text/html;charset=UTF-8;"})
	public String batchInUseCatalog(@Param("updids")String[] updids){
		int result = catalogService.batchInUse(updids);
		if(updids.length==result){
			return result+"个栏目启用成功！！！";
		}else{
			System.out.println("启用失败！！！");
			return "启用失败！！！";
		}
	}
	//批量禁用用户
	@ResponseBody
	@RequestMapping(value="/Manager/batchUnUseCatalog.action",produces={"text/html;charset=UTF-8;"})
	public String batchUnUseCatalog(@Param("updids")String[] updids){
		int result = catalogService.batchUnUse(updids);
		if(updids.length==result){
			return result+"个栏目禁用成功！！！";
		}else{
			System.out.println("禁用失败！！！");
			return "禁用失败！！！";
		}
	}
	
	//跳转到栏目管理页面
	@RequestMapping("/Manager/toCatalogManager.action")
	public String toCatalogManager(Model model,Integer currPage){
		int pageSize = 12;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = catalogService.getPageCount(pageSize);
		catalogList = catalogService.findAllCatalog(offset, pageSize);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		model.addAttribute("catalogList", catalogList);
		return "/manager/catalogManager";
	}
	
	//跳转到添加新闻页面
	@RequestMapping("/Manager/toAddNews.action")
	public String toAddNews(News news,Model model){
		catalogList = catalogService.findAllInuse();
		model.addAttribute("catalogList", catalogList);
		return "/manager/addNews";
	}
	
	//跳转到新闻管理页面
	@RequestMapping("/Manager/toNewsManager.action")
	public String toNewsManager(Model model,Integer currPage){
		int pageSize = 12;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = newsService.getPageCount(pageSize);
		newsList = newsService.findAllNews(offset, pageSize);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		model.addAttribute("newsList", newsList);
		return "/manager/newsManager";
	}
	
	//添加新闻
	@RequestMapping("/Manager/addNews.action")
	public String addNews(News news,@RequestParam("uploadimage") CommonsMultipartFile uploadimage){
		byte[] image = uploadimage.getBytes();
		news.setImage(image);
		newsService.addNews(news);
		return "/manager/addNews";
	}
	
	//跳转到修改新闻信息页面
	@RequestMapping("/Manager/toUpdNews.action")
	public String toUpdNews(Long id,Model model){
		news = newsService.findNewsById(id);
		catalog = catalogService.findCatalogById(news.getCatalog_id());
		catalogList = catalogService.findAll();
		model.addAttribute(news);
		model.addAttribute(catalog);
		model.addAttribute("catalogList", catalogList);
		return "/manager/updNews";
	}
	
	//修改新闻信息
	@RequestMapping("/Manager/updNews.action")
	public String updNews(News news/*,@RequestParam("uploadimage") CommonsMultipartFile uploadimage*/){
//		news.setImage(uploadimage.getBytes());
		newsService.updateNews(news);
		return "/manager/newsManager";
	}
	
	//删除新闻信息
	@RequestMapping("/Manager/delNews.action")
	public String delNews(Long id){
		newsService.deleteNewsById(id);
		return "/manager/newsManager";
	}
	
	//批量删除新闻
	@ResponseBody
	@RequestMapping(value="/Manager/batchDelNews.action",produces={"text/html;charset=UTF-8;"})
	public String batchDelNews(@Param("delids")String[] delids){
		int result = newsService.batchDel(delids);
		if(delids.length==result){
			return result+"条新闻删除成功！！！";
		}else{
			System.out.println("删除失败！！！");
			return "删除失败！！！";
		}
	}
	
	//批量发布新闻
	@ResponseBody
	@RequestMapping(value="/Manager/batchPublishNews.action",produces={"text/html;charset=utf-8;"})
	public String batchPublishNews(@Param("updids")String[] updids){
		int result = newsService.batchPublish(updids);
		if(updids.length==result){
			return result+"条新闻发布成功！！！";
		}else{
			return "发布失败！！！";
		}
	}
	
	//批量取消发布新闻
	@ResponseBody
	@RequestMapping(value="/Manager/batchCancelNews.action",produces={"text/html;charset=utf-8;"})
	public String batchCancelNews(@Param("updids")String[] updids){
		int result = newsService.batchCancel(updids);
		if(updids.length==result){
			return result+"条新闻取消发布成功！！！";
		}else{
			return "取消发布失败！！！";
		}
	}
	
	//显示图片
	@RequestMapping("/Manager/showImage.action")
	public void showImage(Long id,HttpServletResponse response,OutputStream out){
		byte[] image = newsService.findNewsById(id).getImage();
		response.setContentType("img/jpg");
		response.setCharacterEncoding("utf-8");
		try {
			out.write(image);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//跳转到用户管理页面
	@RequestMapping("/Manager/toUserManager.action")
	public String toUserManager(Model model,Integer currPage){
		int pageSize = 12;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = userService.getPageCount(pageSize);
		userModelList = userService.findAll(offset, pageSize);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		model.addAttribute("userModelList", userModelList);
		return "/manager/userManager";
	}
	
	//跳转到修改用户信息页面
	@RequestMapping("/Manager/toUpdUser.action")
	public String toUpdUser(String id,Model model){
		User user = userService.findById(id);
		model.addAttribute(user);
		showAddress(user, model);
		return "manager/updUser";
	}
	
	//修改用户信息
	@RequestMapping("/Manager/updUser.action")
	public String updUser(User user){
		userService.updateUser(user);
		return "manager/userManager";
	}
	
	//删除用户
	@RequestMapping("/Manager/delUser.action")
	public String delUser(String id){
		userService.deleteUserById(id);
		return "/manager/userManager";
	}
	
	//批量删除用户
	@ResponseBody
	@RequestMapping(value="/Manager/batchDelUser.action",produces={"text/html;charset=UTF-8;"})
	public String batchDelUser(@Param("delids")String[] delids){
		int result = userService.batchDel(delids);
		if(delids.length==result){
			return result+"个用户删除成功！！！";
		}else{
			System.out.println("删除失败！！！");
			return "删除失败！！！";
		}
	}
	
	//批量启用用户
	@ResponseBody
	@RequestMapping(value="/Manager/batchInUseUser.action",produces={"text/html;charset=UTF-8;"})
	public String batchInUseUser(@Param("updids")String[] updids){
		int result = userService.batchInUse(updids);
		if(updids.length==result){
			return result+"个用户启用成功！！！";
		}else{
			System.out.println("启用失败！！！");
			return "启用失败！！！";
		}
	}
	//批量禁用用户
	@ResponseBody
	@RequestMapping(value="/Manager/batchUnUseUser.action",produces={"text/html;charset=UTF-8;"})
	public String batchUnUseUser(@Param("updids")String[] updids){
		int result = userService.batchUnUse(updids);
		if(updids.length==result){
			return result+"个用户禁用成功！！！";
		}else{
			System.out.println("禁用失败！！！");
			return "禁用失败！！！";
		}
	}
	
	//跳转到评论管理页面
	@RequestMapping("/Manager/toCommentManager.action")
	public String toCommentManager(Model model,Integer currPage){
		int pageSize = 12;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = commentService.getPageCount(pageSize);
		commentList = commentService.findAllComment(offset, pageSize);
		model.addAttribute("commentList",commentList);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		return "manager/commentManager";
	}
	
	//批量删除评论
	@ResponseBody
	@RequestMapping(value="/Manager/batchDelComment.action",produces={"text/html;charset=UTF-8;"})
	public String batchDelComment(@Param("delids")String[] delids){
		int result = commentService.batchDel(delids);
		if(delids.length==result){
			return result+"条评论删除成功！！！";
		}else{
			System.out.println("删除失败！！！");
			return "删除失败！！！";
		}
	}
	
	/*//跳转到管理员登录页面
	@RequestMapping("/Manager/toLogin.action")
	public String toLogin(){
		return "manager/login";
	}
	
	//管理员登录
	@RequestMapping("/Manager/login.action")
	public String login(User user,Model model,HttpServletRequest request){
		if("manager".equals(userService.login(user))){
			String userName = userService.findById(user.getId()).getName();
			request.getSession().setAttribute("username", userName);
			request.getSession().setAttribute("login", "");
			loginInfo = new LoginInfo();
			loginInfo.setSessionId(request.getSession().getId());
			loginInfo.setUserId(user.getId());
			loginInfo.setUserName(userName);
			userService.saveLoginInfo(loginInfo);
			return "manager/index";
		}
		return "manager/failed";
	}
	
	//管理员退出登录
	@RequestMapping("/Manager/logout.action")
	public void logout(HttpServletRequest request,Model model){
		userService.Logout(loginInfo);
		request.getSession().setAttribute("username","");
	}*/
	
	//通过栏目筛选新闻信息
//	@ResponseBody
	@RequestMapping("/Manager/showNewsByCatalog.action")
	public String showNewsByCatalog(Long catalog_id,Integer currPage,Model model){
		int pageSize = 12;
		if(currPage==null)currPage=1;
		int offset = (currPage-1)*pageSize;
		int pageCount = newsService.getCatalogPageCount(pageSize, catalog_id);
		System.out.println("=================");
		System.out.println(catalog_id);
		System.out.println(currPage);
		List<News> newsList = newsService.findNewsByCatalog(catalog_id,offset,pageSize);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currPage", currPage);
		model.addAttribute("newsList", newsList);
		return "manager/c_news";
	}
	
	//显示所有栏目
	@ResponseBody
	@RequestMapping("/Manager/showCatalog.action")
	public List<Catalog> showCatalog(){
		return catalogService.findAllInuse();
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
