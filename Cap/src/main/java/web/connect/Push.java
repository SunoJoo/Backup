package web.connect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ocr.ResultData;
import ocr.Web_Run;

public class Push implements CommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		Web_Run wr = new Web_Run(req.getParameter("text"));			
		ResultData rd = wr.run();
		req.setAttribute("data", rd);
		return "view.jsp";
	}
}

