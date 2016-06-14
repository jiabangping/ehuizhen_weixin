package com.ehuizhen.test.scpClient;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Main2 {
	/*public static void main(String[] args) {
		//File f = new File("\\\\121.42.211.90/ImageSiteTest/upload/post/1465354392334.html");
		String url = "http://testimage.zhongyinginfo.com:81/upload/post/1465354392334.html";
		File f = new File(url);
		System.out.println(f.exists());
		
		try {
			String strResult = WeixinBasicKit.sendGet(url);
			System.out.println(strResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static boolean isWindows() {
		return System.getProperty("os.name").indexOf("Windows") != -1;
	}
	
	public static void main(String[] args) {
			if(isWindows()) {
				File local = new File("C:/Users/jbp/Desktop/0.5m.jpg");
				File targetFile = new File("\\\\121.42.211.90/ImageSiteTest/test333.jpg");
				if(!targetFile.exists()) {
					try {
						targetFile.createNewFile();
						FileUtils.copyFile(local, targetFile, true);
						String replayUrlPath = "http://testimage.zhongyinginfo.com:81/test333.jpg";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				File local = new File("/home/0.5m.jpg");
				File targetFile = new File("//121.42.211.90/ImageSiteTest/test333.jpg");
				if(!targetFile.exists()) {
					try {
						targetFile.createNewFile();
						FileUtils.copyFile(local, targetFile, true);
						String replayUrlPath = "http://testimage.zhongyinginfo.com:81/test333.jpg";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
}
