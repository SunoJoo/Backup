package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ocr.ResultData;
import ocr.Web_Run;

public class AppController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("EUC-KR");
		resp.setContentType("text/html; charset=EUC-KR");
		
		String reqUri = req.getRequestURI();
		String path = req.getContextPath();
		String cmd = reqUri.substring(path.length());
		String nextPage=null;
		if(cmd.equals("/main.page")) {		
			Web_Run wr = new Web_Run(req.getParameter("text"));			
			ResultData rd = wr.run();
			rd.getText();
			rd.getPosition();
			req.setAttribute("data", rd);
			//nextPage = "push.view";			
		}
		
	
		RequestDispatcher view = req.getRequestDispatcher("view.jsp");
		view.forward(req, resp);
	}

}

