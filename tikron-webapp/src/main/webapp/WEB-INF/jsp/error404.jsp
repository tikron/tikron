<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="de.tikron.webapp.messages" var="msg"/>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/base.css${contentVersionParam}" />
<title><fmt:message key="error404.title" bundle="${msg}" /></title>
</head>
<body>
	<div id="container">
		<div id="wrap">
			<section id="content" class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<h1><fmt:message key="error404.headline" bundle="${msg}" /></h1>
					<p><fmt:message key="error404.info" bundle="${msg}" /></p>
					<br />
					<a href="/"><fmt:message key="link.homepage" bundle="${msg}" /></a>
				</div>
			</section>
		</div>
	</div>
</body>
</html>
