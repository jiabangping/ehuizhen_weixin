package com.ehuizhen.weixin.config;

import java.io.File;

import org.springframework.stereotype.Component;
@Component
public class ImageConfig {

	//@Value("#{configProperties['ImageConfigBean.ImagePath']}")
	private String imagePath;

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public static String GetImageServer(String directory, String photoName) {
		return ConstantClass.ImageServer + directory + "/" + photoName;
	}

	public static String GetImagePath(String directory, String photoName) {

		if (photoName == null) {
			return "";
		}
		if (photoName.trim().equals("")) {
			return "";
		}
		File tempFile = new File(ConstantClass.ImagePath + "\\" + directory);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		return ConstantClass.ImagePath + "\\" + directory + "\\" + photoName.trim();
	}
}
