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
public class MemberTests {

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;

	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	
	@Test
	public void testInsertMember() {
		String sql = "insert into tbl_member (uid, upw, uname) values (?, ?, ?) ";
		
		for(int i = 0; i < 10; i++) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = ds.getConnection();
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(2, pwencoder.encode("pw"+i));
				if(i<3) {
					stmt.setString(1, "user"+i);
					stmt.setString(3, "일반사용자"+i);
				} else if(i < 7) {
					stmt.setString(1, "manager"+i);
					stmt.setString(3, "운영자"+i);
				} else {
					stmt.setString(1, "admin"+i);
					stmt.setString(3, "관리자"+i);					
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






