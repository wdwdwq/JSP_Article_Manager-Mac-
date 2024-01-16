package com.hyobin.java.am;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/home/printDan")
public class HomeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8;");
		
		String inputDan = request.getParameter("dan");
		String inputLimit = request.getParameter("limit");
		String inputColor = request.getParameter("color");
		
		if (inputDan == null || inputDan.equals("")) {
			inputDan = "1";
		}
		if (inputLimit == null || inputLimit.equals("")) {
			inputLimit = "1";
		}
		if (inputColor == null || inputColor.equals("")) {
			inputColor = "black";
		}
		
		int dan = Integer.parseInt(inputDan);
		int limit = Integer.parseInt(inputLimit);
		
		response.getWriter().append(String.format("<div style=\"color:%s;\">== %d단 ==</div>", inputColor, dan));
		
		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style=\"color:%s;\">%d * %d = %d</div>", inputColor, dan, i, dan * i));
		}
		
	}

}
