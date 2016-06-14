package com.ehuizhen.weixin.ftp;
import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ehuizhen.weixin.config.ImageConfig;
@Component
public class FtpUpload {
	private static Logger log =Logger.getLogger(FtpUpload.class);
	public String fileUpload(MultipartFile file, String path, String imgName){
		
		   Ftp f=new Ftp();
	       f.setIpAddr(FtpConfig.IP);
	       f.setUserName(FtpConfig.USERNAME);
	       f.setPwd(FtpConfig.PASSWORD);
	       f.setPort(FtpConfig.PORT);
	       f.setPath(path);
	       boolean flag = false;
	       try {
	    	   // 创建连接
	    	   flag = FtpUtil.connectFtp(f);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
	        //上传图片地址
			//String IMAGE_TYPE = path;
			// Calendar cal = Calendar.getInstance();
			// String imgName = cal.getTimeInMillis() + ".JPG";
			
	       try {
	    	   CommonsMultipartFile cf = (CommonsMultipartFile)file; 
	           DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	           File ff = fi.getStoreLocation();
	           //把文件上传在ftp上
	    	   flag=FtpUtil.upload(ff,imgName);
	       } catch (Exception e) {
	    	   log.error(e.getMessage(),e);
	       }
	       
	       	// 关闭连接
	       	FtpUtil.closeFtp();
	       	
	       	if(flag){
	       		return ImageConfig.GetImageServer(path,imgName);
	       	}
	       	return null;
	}
	
	
	public static String fileUpload(File file, String path, String imgName){
		
		   Ftp f=new Ftp();
	       f.setIpAddr(FtpConfig.IP);
	       f.setUserName(FtpConfig.USERNAME);
	       f.setPwd(FtpConfig.PASSWORD);
	       f.setPort(FtpConfig.PORT);
	       f.setPath(path);
	       boolean flag = false;
	       try {
	    	   // 创建连接
	    	   flag = FtpUtil.connectFtp(f);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			
	     
	       try {
				flag=FtpUtil.upload(file,imgName);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
	       
	       	// 关闭连接
	       	FtpUtil.closeFtp();
	       	
	       	if(flag){
	       		return ImageConfig.GetImageServer(path,imgName);
	       	}
	       	return null;
	}
	
	
}
