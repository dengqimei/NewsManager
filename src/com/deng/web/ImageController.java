package com.deng.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.deng.bean.Image;
import com.deng.service.IImageService;

@Controller
public class ImageController {
	
	@Autowired
	private IImageService imageService;
	
	//跳转到添加图片页面
	@RequestMapping("/addImage")
	public String addImage() {
		return "addImage";
	}
	
	//处理添加图片
	@RequestMapping("/toSaveImage")
	public String saveImage(Image image,@RequestParam("pic") CommonsMultipartFile pic){
		byte[] picture = pic.getBytes();
		System.out.println("================");
		System.out.println(image.getId());
		System.out.println(image.getTitle());
		System.out.println(image.getName());
		System.out.println(pic);
		image.setPicture(picture);
		imageService.saveImage(image);
		return "success";
	}
	
	//显示图片
	@RequestMapping("/toShowImage")
	public String toShowImage(){
		return "showImage";
	}
	
	//根据Id查找图片
	@RequestMapping("/showImage")
	public void findImageById(HttpServletResponse response, OutputStream out){
		Image image = imageService.findImageById(12);
		byte[] picture = image.getPicture();
		response.setContentType("img/jpeg");  
        response.setCharacterEncoding("utf-8");  
        try {  
            out.write(picture);
            out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}
}
