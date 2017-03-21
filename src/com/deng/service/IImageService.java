package com.deng.service;

import com.deng.bean.Image;

public interface IImageService {

	public void saveImage(Image image);
	
	public Image findImageById(int id);
	
}
