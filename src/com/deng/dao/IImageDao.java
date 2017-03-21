package com.deng.dao;

import com.deng.bean.Image;

public interface IImageDao {
	public void saveImage(Image image);
	public Image findImageById(int id);
}
