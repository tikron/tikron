<%@ page import="java.util.regex.Pattern" %><%
	Pattern p = Pattern.compile("[a-f0-9]{32}");
	String key = request.getParameter("key");
	if(key == null)
		out.write("Alles OK");
	else if(p.matcher(key).matches())
		out.write(key);
%>