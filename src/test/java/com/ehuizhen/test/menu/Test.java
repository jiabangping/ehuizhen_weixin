package com.ehuizhen.test.menu;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ehuizhen.weixin.dao.MenuDao;
import com.ehuizhen.weixin.model.MenuTest;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		//HibernateTemplate t = (HibernateTemplate)context.getBean("hibernateTemplate");
		
		MenuDao dao = context.getBean(MenuDao.class);
		//query(dao,1);
		save(dao);
	}
	public static void save(MenuDao dao) {
	/*	MenuTest entity = new MenuTest();
		entity.setId(1);
		entity.setName("购物");
		
		Set<MenuTest> childs = new HashSet<MenuTest>();
			MenuTest child1 = new MenuTest();
			child1.setName("男装");
			
			
			MenuTest child2 = new MenuTest();
			child2.setName("女装");
			
			
			childs.add(child1);
			childs.add(child2);
		
		entity.setMenus(childs);
		
		dao.save(entity);
	}
	
	public static void query(MenuDao dao,int id) {
		MenuTest entity = dao.get(id);
		//System.out.println(entity);
		Set<MenuTest> childs = entity.getMenus();
		for(MenuTest c :childs) {
			System.out.println(c);
		}
 		System.out.println(entity);
	}*/
	}
}
