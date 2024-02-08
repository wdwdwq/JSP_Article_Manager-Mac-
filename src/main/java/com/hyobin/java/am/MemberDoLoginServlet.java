package com.hyobin.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.hyobin.java.am.util.DBUtil;
import com.hyobin.java.am.util.SecSql;
import com.hyobin.java.config.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8;");
		
		Connection conn = null;
		
		try {
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			
			SecSql sql = SecSql.from("SELECT * FROM `member`");
			sql.append("WHERE loginId = ?", loginId);
			
			Map<String, Object> memberMap = DBUtil.selectRow(conn, sql);
			
			if (memberMap.isEmpty()) {
				response.getWriter().append(String.format("<script>alert('[ %s ]은(는) 존재하지 않는 아이디입니다'); history.back();</script>", loginId));
				return;
			}
			
			if (memberMap.get("loginPw").equals(loginPw) == false) {
				response.getWriter().append("<script>alert('비밀번호가 일치하지 않습니다'); location.replace('login');</script>");
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loginedMemberId", memberMap.get("id"));
			session.setAttribute("loginedMemberLoginId", memberMap.get("loginId"));
			
			response.getWriter().append(String.format("<script>alert('%s님 환영합니다'); location.replace('../home/main');</script>", memberMap.get("name")));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}