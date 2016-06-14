package com.ehuizhen.weixin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Menu_Test")
public class MenuTest  implements java.io.Serializable {

	    private String name;  
	    private String link;  
	    private Integer grade; //菜单等级  
	    private Integer morder; //同一级菜单中的顺序  
	    
	  
	    private MenuTest parentMenu;  
	    //子菜单列表  
	    private List<MenuTest> childMenu = new ArrayList<MenuTest>();  
	  
	    public MenuTest() {  
	        super();  
	    }  
	  
	    @Column(nullable = false, unique = true)  
	    public String getName() {  
	        return name;  
	    }  
	  
	    @ManyToOne(fetch = FetchType.LAZY)  
	    @JoinColumn(name = "pid")  
	    public MenuTest getParentMenu() {  
	        return parentMenu;  
	    }  
	  
	    @OneToMany(targetEntity = MenuTest.class, cascade = { CascadeType.ALL }, mappedBy = "parentMenu")  
	    @Fetch(FetchMode.SUBSELECT)  
	    @OrderBy("morder")  
	    public List<MenuTest> getChildMenu() {  
	        return childMenu;  
	    }

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public Integer getGrade() {
			return grade;
		}

		public void setGrade(Integer grade) {
			this.grade = grade;
		}

		public Integer getMorder() {
			return morder;
		}

		public void setMorder(Integer morder) {
			this.morder = morder;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setParentMenu(MenuTest parentMenu) {
			this.parentMenu = parentMenu;
		}

		public void setChildMenu(List<MenuTest> childMenu) {
			this.childMenu = childMenu;
		}  
	  
	    
	    
    
}
