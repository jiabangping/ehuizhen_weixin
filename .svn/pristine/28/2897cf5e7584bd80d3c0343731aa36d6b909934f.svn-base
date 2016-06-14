package com.ehuizhen.weixin.web.servlet;

import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 文件进度监听器
 * 
 * @author chengqi
 *
 */
public class FileProcessListener implements ProgressListener{

	/** 日志对象*/
	private Logger logger = LoggerFactory.getLogger(FileProcessListener.class);

	private HttpSession session;

	public FileProcessListener(HttpSession session) {
		this.session = session;  
	}
	

	public void update(long pBytesRead, long pContentLength, int pItems) {
		double readByte = pBytesRead;
		double totalSize = pContentLength;
		if(pContentLength == -1) {
			logger.debug("item index[" + pItems + "] " + pBytesRead + " bytes have been read.");
		} else {
			logger.debug("item index[" + pItems + "] " + pBytesRead + " of " + pContentLength + " bytes have been read.");
			String p = NumberFormat.getPercentInstance().format(readByte / totalSize);
			session.setAttribute("fileUploadProcess", p);
		}
	}

}