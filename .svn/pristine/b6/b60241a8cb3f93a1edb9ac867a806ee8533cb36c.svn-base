package com.ehuizhen.weixin.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehuizhen.weixin.config.EnumClass.ImageType;
import com.ehuizhen.weixin.config.ImageConfig;
import com.ehuizhen.weixin.ftp.FtpUpload;
import com.ehuizhen.weixin.init.ServerConfigConst;

/**
 * 文件上传数据接收类
 * 
 * @author chengqi
 *
 */
public class AjaxUploadFileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	/** 日志对象*/
	private Logger logger = LoggerFactory.getLogger(AjaxUploadFileServlet.class);

	private static final long serialVersionUID = 1L;

	/** 上传目录名*/
	private static final String uploadFolderName = "uploadFiles";

	/** 上传临时文件存储目录*/
	private static final String tempFolderName = "tempFiles";

	/** 上传文件最大为30M*/ 
	//private static final Long fileMaxSize = 30000000L;
	//最大1M
	private static final Long fileMaxSize = 2*5000000L;

	/** 允许上传的扩展名*/
	private static final String [] extensionPermit = {"jpg","jpeg","png","gif",""};//需要""的后缀名有的设备上传的没有后缀名 
	
	/** 不允许上传的扩展名*/
	private static final String [] extensionNotPermit = {"zip","exe"};//不允许上传的格式
	

	/** 统一的编码格式*/
	private static final String encode = "UTF-8";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("UploadFileServlet#doPost() start");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		String localUrlPath = basePath + "/" + uploadFolderName+"/";
		String replayUrlPath = "";
		try {
			//清除上次上传进度信息
			request.getSession().removeAttribute("fileUploadProcess");

			String curProjectPath = this.getServletContext().getRealPath("/");
			String saveDirectoryPath = curProjectPath + "/" + uploadFolderName;
			String tempDirectoryPath = curProjectPath + "/" + tempFolderName;
			File saveDirectory = new File(saveDirectoryPath);
			File tempDirectory = new File(tempDirectoryPath);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			if(!tempDirectory.exists()) {
				tempDirectory.mkdir();
			}
			factory.setRepository(tempDirectory);

			FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(this.getServletContext());
			factory.setFileCleaningTracker(fileCleaningTracker);

			ServletFileUpload upload = new ServletFileUpload(factory);
			FileProcessListener processListener = new FileProcessListener(request.getSession());
			upload.setProgressListener(processListener);
			upload.setFileSizeMax(fileMaxSize);

			upload.setHeaderEncoding(encode);

			String phoneNum = "";
			List<FileItem> fileItems = upload.parseRequest(request);
			for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {
				FileItem fileItem = iterator.next();
				//Form表单数据
				if(fileItem.isFormField()) {
					String value = fileItem.getString(encode);
					phoneNum = value;
				}
			}
			
			//遍历解析完成后的Form数据和上传文件数据
			for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {
				FileItem fileItem = iterator.next();
				String fieldName = fileItem.getFieldName();
				String name = fileItem.getName();
				if(name != null) {
					localUrlPath+=name;
				}
				if(!fileItem.isFormField()) {
					if(fileItem.getSize() > 0) {
						String format = ".jpg";
						try{
							format = name.substring(name.lastIndexOf("."), name.length());
						}catch(Exception e) {
							
						}
						String fileExtension = FilenameUtils.getExtension(name);
						if(!ArrayUtils.contains(extensionPermit, fileExtension)) {//fileExtension不是extensionPermit中的
							throw new NoSupportExtensionException("No Support extension.");
						}
						/*if(ArrayUtils.contains(extensionNotPermit, fileExtension)) {//fileExtension是extensionNotPermit中不允许的
							throw new NoSupportExtensionException("No Support extension.");
						}*/
						
						String fileName = FilenameUtils.getName(name);
						File localFile = new File(saveDirectory, fileName);
						FileUtils.copyInputStreamToFile(fileItem.getInputStream(), 
								localFile);
						
						// 检测本地是否有缓存
						if (localFile.exists()) {
							if(phoneNum == null || "".equals(phoneNum)) {
								responseMessage(response, State.PHONE_NUM_ERROR);
								return;
							}
							
							// 保存
							try {
								if(ServerConfigConst.isWindows()) {
									/*String serverFilePath = ImageConfig.GetImagePath(ImageType.DOCTORHEADER.toString(), phoneNum+format);
									File targetFile = new File(serverFilePath);
									if (!targetFile.exists()) {
										targetFile.createNewFile();
									}*/	
								
										// 上传头像到图片服务器
									/*	FileUtils.copyFile(localFile, targetFile, true);
										replayUrlPath = ImageConfig.GetImageServer(ImageType.DOCTORHEADER.toString(), phoneNum+format);
									*/
								}
								replayUrlPath =	FtpUpload.fileUpload(localFile, ImageType.DOCTORHEADER.toString(), phoneNum+format);
								
								// 删除本地缓存图片
								if (localFile.exists()) {
									localFile.delete();
								}
							}catch(Exception e) {
								logger.error(e.getMessage(), e);
								responseMessage(response, State.ERROR);
							}
						}
					}
				}
			}
			
			responseMessage(response, State.OK,replayUrlPath);
		} catch(FileSizeLimitExceededException e) { 
			logger.error(e.getMessage(), e);
			responseMessage(response, State.OVER_FILE_LIMIT);
		} catch(NoSupportExtensionException e) { 
			logger.error(e.getMessage(), e);
			responseMessage(response, State.NO_SUPPORT_EXTENSION);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			responseMessage(response, State.ERROR);
		}
		logger.info("UploadFileServlet#doPost() end"); 
	}

	public enum State {
		OK(200, "上传成功"),
		ERROR(500, "上传失败"),
		OVER_FILE_LIMIT(501, "超过上传大小限制"),
		NO_SUPPORT_EXTENSION(502, "不支持的扩展名，仅支持【jpg,jpeg,png,gif】"),
		PHONE_NUM_ERROR(503, "请正确填写手机号");
		private int code;
		private String message;
		private State(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}

	}

	/**
	 * 返回结果函数
	 * @param response
	 * @param state
	 */
	private void responseMessage(HttpServletResponse response, State state) {
		response.setCharacterEncoding(encode);
		response.setContentType("text/html; charset=" + encode);
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write("{\"code\":" + state.getCode() +",\"message\":\"" + state.getMessage()+ "\"}");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
	
	/**
	 * 返回结果函数
	 * @param response
	 * @param state
	 */
	private void responseMessage(HttpServletResponse response, State state,String url) {
		response.setCharacterEncoding(encode);
		response.setContentType("text/html; charset=" + encode);
		Writer writer = null;
		try {
			writer = response.getWriter();
			String result = "{\"code\":" + state.getCode() +",\"message\":\"" + state.getMessage()+ "\",\"url\":\""+url +"\" }";
			writer.write(result);
			writer.flush();
			writer.close();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

}
