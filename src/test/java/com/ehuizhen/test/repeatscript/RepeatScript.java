package com.ehuizhen.test.repeatscript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RepeatScript {
	static String replaceedStr = "url: 'http://121.42.212.104:8090/ehuizhen_back/post/";
	static String newStr = "url: 'http://web.zhongyinginfo.com/ehuizhen/post/";
	
	public static void main(String[] args) throws IOException {
		File dir = new File("\\\\121.42.211.90\\ImageSiteTest\\upload\\post");
		if(dir.exists() ) {
			if(dir.isDirectory()) {
				File[] files = dir.listFiles();
				int count = 0;
				for(File f : files) {
					if(f.isFile()) {
						System.out.println("共："+files.length+"个文件,name="+f.getName()+",count="+(++count));
						BufferedReader reader = null;
						PrintWriter out = null;
						try {
							reader = new BufferedReader(new FileReader(f));
							String tmp = null;
							File local = new File("G:/news/"+f.getName());
							out = new PrintWriter(local);
							local.createNewFile();
							while((tmp=reader.readLine()) != null) {
								if(tmp != null && tmp.contains(replaceedStr)) {
									tmp = tmp.replace(replaceedStr, newStr);
								}
								out.println(tmp);
							}
							out.flush();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							reader.close();
							out.close();
						}
					}
				}
			}
		}
	}
}
