package com.deng.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.mysql.jdbc.PreparedStatement;

public class ImageUtil {

	public static int readImage(String filePath) throws FileNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(filePath);
		int fileLength = fis.available();
		byte[] b = new byte[fileLength];
		@SuppressWarnings("resource")
		int len = new FileInputStream(new File(filePath)).read(b);
		System.out.println(Arrays.toString(b));
		System.out.println(len);
		FileOutputStream fos = new FileOutputStream("src/com/deng/util/222.txt");
		fos.write(b,0,len);
		fos.flush();
		return len;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		BufferedImage bi = ImageIO.read(new File("src/com/deng/util/111.jpg"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();    
        ImageIO.write(bi, "jpg", baos);    
        byte[] bytes = baos.toByteArray();    
        System.out.println(Arrays.toString(bytes));   
		System.out.println(bi);
//		readImage("src/com/deng/util/111.jpg");
	}
	
	
	
}
