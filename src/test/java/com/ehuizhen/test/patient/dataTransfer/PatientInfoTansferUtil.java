package com.ehuizhen.test.patient.dataTransfer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PatientInfoTansferUtil {
	static String consultationOrderTableName = "";
	static String patientTableName = "";
	public Properties initParam() {
		Properties p = new Properties();
		try {
	         p.load(getClass().getResourceAsStream("jdbc.properties"));
	         consultationOrderTableName = p.getProperty("consultationOrderTableName");
	         patientTableName = p.getProperty("patientTableName");
			return p;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Connection initConn(Properties p) {
		try {
			Class.forName(p.getProperty("jdbc.driverClassName"));
			Connection conn = DriverManager.getConnection(p.getProperty("jdbc.url"),p.getProperty("jdbc.username"),p.getProperty("jdbc.password"));
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getDistinctPhoneNum(Connection conn) {
		String queryDistinctPhoneNumSql = "select distinct PatientPhoneNum from "+consultationOrderTableName;
		try {
			PreparedStatement queryDistinctPhoneNumStmt = conn.prepareStatement(queryDistinctPhoneNumSql);
			ResultSet queryDistinctPhoneNumRs = queryDistinctPhoneNumStmt.executeQuery();
			List<String> distinctPhoneNums = new ArrayList<String>();
			while(queryDistinctPhoneNumRs.next()) {
				distinctPhoneNums.add(queryDistinctPhoneNumRs.getString("PatientPhoneNum"));
			}
			System.out.println("distinctPhoneNums.size="+distinctPhoneNums.size());
			return distinctPhoneNums;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	
	public static void insertOneRowRecord(String name,String phoneNum,String sex,Connection conn,PreparedStatement insertPstmt) {
		try {
			insertPstmt.setString(1, name);
			insertPstmt.setString(2, phoneNum);
			insertPstmt.setString(3, "E10ADC3949BA59ABBE56E057F20F883E");
			insertPstmt.setInt(4, "0".equals(sex) ? 1 : 2);
			insertPstmt.addBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static PatientInfoTansferUtil instance = new PatientInfoTansferUtil();
	public static PatientInfoTansferUtil getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Connection conn = initConn(getInstance().initParam());
		List<String> distinctPhoneNums = getDistinctPhoneNum(conn);
		PreparedStatement insertPstmt = null;
		try {
			
			String patientRowRecordSql = "select id,PatientName,PatientPhoneNum,PatientSex,PatientAge from "+consultationOrderTableName+" where PatientPhoneNum=?";
			PreparedStatement patientRowRecordPstmt = conn.prepareStatement(patientRowRecordSql);
			insertPstmt = conn.prepareStatement("insert into "+patientTableName+"(PatientName,PhoneNum,PassWord,Sex) values(?,?,?,?)");
			
			for(String phoneNum : distinctPhoneNums) {
				patientRowRecordPstmt.setString(1, phoneNum);
				ResultSet patientRowRecordRs = patientRowRecordPstmt.executeQuery();
				patientRowRecordRs.next();
				String name = patientRowRecordRs.getString("PatientName");
				String phone = patientRowRecordRs.getString("PatientPhoneNum");
				String sex = patientRowRecordRs.getString("PatientSex");
				insertOneRowRecord(name,phone,sex,conn,insertPstmt);
			}
			int[] results = insertPstmt.executeBatch();
			System.out.println("共batch insert="+results.length+"，条");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
