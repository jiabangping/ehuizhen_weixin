package com.ehuizhen.test.scpClient;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

public class SCPClientMain {
	private static void windowsCopyToLinuxF1() {
		File local = new File("C:/Users/jbp/Desktop/0.5m.jpg");
		File targetFile = new File("/192.168.5.100/ImageSite/test2.jpg");
		if(!targetFile.exists()) {
			try {
				targetFile.createNewFile();
				FileUtils.copyFile(local, targetFile, true);
				String replayUrlPath = "http://192.168.5.100:9998/test2.jpg";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void f2() {
		/*System.out.println("\\\\");*/
		File f = new File("\\\\192.168.4.145/home/test.jpg");
		if(!f.exists()) {
			try {
				f.createNewFile();
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Connection con = new Connection("192.168.4.145", 22);
		con.connect();
		//远程服务器的用户名密码
		boolean isAuthed = con.authenticateWithPassword("root","258369");
		if(isAuthed) {
			//建立SCP客户端
			SCPClient scpClient = con.createSCPClient();
			
			//服务器端的文件下载到本地的目录下
			//scpClient.get("/home/apache-tomcat-7.0.57.tar.gz", "G:/");
			//将本地文件上传到服务器端的目录下
			//scpClient.put("G:/ehuizhen_weixin.war", "/home/");
			scpClient.put("G:/ehuizhen_back.war", "/home/apache-tomcat-7.0.57/webapps/");
//			scpClient.put("G:/ehuizhen_weixin.war", "/home/apache-tomcat-7.0.57/webapps/");
			
			
			//建立会话
			/*Session session = null;
			session = con.openSession();
			//利用会话可以操作远程服务器
			//例如：删除远程目录下的文件
		//	session.execCommand("cd /home;pwd;ps -ef |grep tomcat;service iptables restart;ps -ef|grep tomcat|grep -v grep|awk '{print $2}'|xargs kill -9;/home/apache-tomcat-7.0.57/bin/startup.sh;");
			session.execCommand("ps -ef |grep tomcat;ps -ef|grep tomcat|grep -v grep|awk '{print $2}'|xargs kill -9;");
			
			//session.execCommand("rm  /home/apache-tomcat-7.0.57/webapps/ehuizhen_weixin*");
			
			scpClient.put("G:/ehuizhen_weixin.war", "/home/apache-tomcat-7.0.57/webapps/");
			
			//session.execCommand("/home/apache-tomcat-7.0.57/bin/startup.sh");
			
			//显示执行命令后的信息
			InputStream stdout = new StreamGobbler(session.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			 
			while (true) {
			String line = br.readLine();
			    if (line == null) {
			       System.out.println("远程服务器返回信息:空");
			           break;
			    }
			    System.out.println("远程服务器返回信息:" + line);
			}
			//获得推出状态
			System.out.println("ExitCode: " + session.getExitStatus());
			session.close();*/
			con.close();
		}else {
			System.out.println("auth-fail");
		}
		
	
	}
}
