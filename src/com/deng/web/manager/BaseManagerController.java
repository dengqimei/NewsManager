package com.deng.web.manager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.deng.bean.Catalog;
import com.deng.bean.News;
import com.deng.service.ICatalogService;
import com.deng.service.INewsService;


@Controller
public class BaseManagerController {
	
	@Autowired
	private ICatalogService catalogService;
	@Autowired
	private INewsService newsService;

	
	private List<Catalog> catalogList;
	private List<News> newsList;
	private Catalog catalog;
	private News news;
	
	@RequestMapping("/Manager/toIndex.action")
	public String toManagerIndex(){
		System.out.println("in manager toindex...");
		//userService.register(new User(2,"tom",20));
		return "/manager/index";
	}
	
	@RequestMapping("/Manager/toAddCatalog.action")
	public String toAddCatalog(){
		return "/manager/addCatalog";
	}
	
	@RequestMapping("/Manager/addCatalog.action")
	public String addCatalog(Catalog catalog){
		catalogService.addCatalog(catalog);
		return "/manager/addCatalog";
	}
	
	@RequestMapping("/Manager/toUpdCatalog.action")
	public String toUpdCatalog(Long id,Model model){
//		System.out.println("===========");
//		System.out.println(id);
		catalog = catalogService.findCatalogById(id);
//		System.out.println(catalog);
		model.addAttribute(catalog);
		return "/manager/updCatalog";
	}
	
	@RequestMapping("/Manager/updCatalog.action")
	public String updCatalog(Catalog catalog){
		catalogService.updateCatalog(catalog);
		return "/manager/catalogManager";
	}
	
	@RequestMapping("/Manager/delCatalog.action")
	public String delCatalog(Long id){
		//System.out.println(id);
		catalogService.deleteCatalogById(id);
		return "/manager/catalogManager";
	}
	
	@RequestMapping("/Manager/toCatalogManager.action")
	public String toCatalogManager(Model model){
		catalogList = catalogService.findAll();
//		System.out.println("===================");
//		System.out.println(catalogList.get(1).getId());
//		System.out.println(catalogList.get(1).getName());
//		System.out.println(catalogList.get(1).getCode());
//		System.out.println(catalogList.get(1).getInputTime());
//		System.out.println(catalogList.get(1).getUpdateTime());
		model.addAttribute("catalogList", catalogList);
		return "/manager/catalogManager";
	}
	
	@RequestMapping("/Manager/toAddNews.action")
	public String toAddNews(News news,Model model){
		catalogList = catalogService.findAll();
		model.addAttribute("catalogList", catalogList);
//		System.out.println(catalogList.size());
//		System.out.println(catalogList);
		return "/manager/addNews";
	}
	
	@RequestMapping("/Manager/toNewsManager.action")
	public String toNewsManager(Model model){
		newsList = newsService.findAll();
		model.addAttribute("newsList", newsList);
		return "/manager/newsManager";
	}
	
	@RequestMapping("/Manager/addNews.action")
	public String addNews(News news,@RequestParam("uploadimage") CommonsMultipartFile uploadimage){
		byte[] image = uploadimage.getBytes();
		news.setImage(image);
		newsService.addNews(news);
//		System.out.println("================");
//		System.out.println(news.getTitle());
//		System.out.println(news.getAuthor());
//		System.out.println(news.getContent());
//		System.out.println(news.getCatalog_id());
		return "/manager/addNews";
	}
	
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
	
	@RequestMapping("/Manager/updNews.action")
	public String updNews(News news,@RequestParam("uploadimage") CommonsMultipartFile uploadimage){
		news.setImage(uploadimage.getBytes());
		newsService.updateNews(news);
		return "/manager/newsManager";
	}
	
	@RequestMapping("/Manager/delNews.action")
	public String delNews(Long id){
		newsService.deleteNewsById(id);
		return "/manager/newsManager";
	}
	@RequestMapping("/Manager/showImage.action")
	public void showImage(Long id,HttpServletResponse response,OutputStream out){
		byte[] image = newsService.findNewsById(id).getImage();
		System.out.println(image.toString());
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
	
}
