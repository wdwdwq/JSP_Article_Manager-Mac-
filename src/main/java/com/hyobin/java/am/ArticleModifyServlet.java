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

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		
		try {
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			SecSql sql = SecSql.from("SELECT * FROM article");
			sql.append("WHERE id = ?", id);
			
			Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
			
			if (articleMap.isEmpty()) {
				response.setContentType("text/html; charset=UTF-8;");
				response.getWriter().append(String.format("<script>alert('%d번 게시물은 존재하지 않습니다'); location.replace('list');</script>", id));
				return;
			}
			
			HttpSession session = request.getSession();
			
			int loginedMemberId = -1;
			
			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
			}
			
			if (loginedMemberId == -1) {
				response.setContentType("text/html; charset=UTF-8;");
				response.getWriter().append("<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>");
				return;
			}
			
			if ((int) articleMap.get("memberId") != loginedMemberId) {
				response.setContentType("text/html; charset=UTF-8;");
				response.getWriter().append(String.format("<script>alert('해당 게시물에 대한 권한이 없습니다'); location.replace('detail?id=%d');</script>", id));
				return;
			}
			
			request.setAttribute("articleMap", articleMap);
			
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
			
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
}