package com.ehuizhen.weixin.ftp;

public class Ftp{
	//ip地址
	private String ipAddr;
	//端口号
    private Integer port;
    //用户名
    private String userName;
    //密码
    private String pwd;
    //路径
    private String path;
    
    
	public Ftp() {
		super();
	}
	
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
    
}
