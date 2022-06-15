package com.kb.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.cj.xdevapi.PreparableStatement;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/security-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class authorTests {

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;

	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	
	@Test
	public void testInsertMember() {
		String sql = "insert into tbl_author (uid, authority) values (?, ?) ";
		
		for(int i = 0; i < 10; i++) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = ds.getConnection();
				stmt = conn.prepareStatement(sql);
								
				if(i<3) {
					stmt.setString(1, "user"+i);
					stmt.setString(2, "ROLE_USER");
				} else if(i < 7) {
					stmt.setString(1, "manager"+i);
					stmt.setString(2, "ROLE_MEMBER");
				} else {
					stmt.setString(1, "admin"+i);
					stmt.setString(2, "ROLE_ADMIN");					
				}
				
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}






