package com.kb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
	private String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private String user = "root";
	private String password = "smart";

	@Test
	public void testConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			log.info(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
