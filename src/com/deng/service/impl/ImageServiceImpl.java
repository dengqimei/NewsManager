package com.deng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.deng.bean.Image;
import com.deng.dao.IImageDao;
import com.deng.service.IImageService;

public class ImageServiceImpl implements IImageService{
	
	@Autowired
	private IImageDao imageDao;
	
	@Override
	public void saveImage(Image image) {
		imageDao.saveImage(image);
	}

	@Override
	public Image findImageById(int id) {
		return imageDao.findImageById(id);
	}

}
