package com.ehuizhen.weixin.util;


import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
  
import javax.net.ssl.X509TrustManager;  
  
/** 
 * 
 封装通用的请求方法
	读到这里，就默认大家已经掌握了上面讲到的所有关于自定义菜单的理论知识，下面就进入代码实战讲解的部分。
	先前我们了解到，创建菜单需要调用二个接口，并且都是https请求，而非http。如果要封装一个通用的请求方法，该方法至少需要具备以下能力：
	1）支持HTTPS请求；
	2）支持GET、POST两种方式；
	3）支持参数提交，也支持无参数的情况；
对于https请求，我们需要一个证书信任管理器，这个管理器类需要自己定义，但需要实现X509TrustManager接口，代码如下：
 * 证书信任管理器（用于https请求） 
 *  
 * @author liufeng 
 * @date 2013-08-08 
 */  
public class MyX509TrustManager implements X509TrustManager {  
  
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
  
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
  
    public X509Certificate[] getAcceptedIssuers() {  
        return null;  
    }  
}  