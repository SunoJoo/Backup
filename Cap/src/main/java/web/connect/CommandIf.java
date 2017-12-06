package web.connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandIf {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp);
}
