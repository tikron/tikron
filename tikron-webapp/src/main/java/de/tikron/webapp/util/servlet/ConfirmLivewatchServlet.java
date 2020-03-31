package de.tikron.webapp.util.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Confirms the "Livewatch" request.
 * 
 * @date 11.10.2019
 * 
 * @author Titus Kruse
 */
public class ConfirmLivewatchServlet extends HttpServlet {

	private static final long serialVersionUID = -3178077520261901366L;
	
	private static final Pattern keyPattern = Pattern.compile("[a-f0-9]{32}");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		ServletOutputStream out = resp.getOutputStream();
		String key = req.getParameter("key");
		if(key == null)
			out.print("Ahoi!");
		else if(keyPattern.matcher(key).matches())
			// Confirm with 32 hex characters key
			out.print(key);
		else 
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid key");
	}
}
