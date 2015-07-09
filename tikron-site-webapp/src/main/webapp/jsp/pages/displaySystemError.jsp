<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf"%>
<%@ include file="/jsp/include/htmlhead_start.jspf"%>
<%@ include file="/jsp/include/title.jspf"%>
<%@ include file="/jsp/include/head.jspf"%>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf"%>
<%@ include file="/jsp/include/htmlbody_start.jspf"%>
<%@ include file="/jsp/include/header.jspf"%>
<section id="content">
	<article>
		<h1><spring:message code="displaySystemError.headline"/></h1>
		<p><spring:message code="displaySystemError.info"/></p>
		<br />
		<a href="/"><spring:message code="link.homepage"/></a>
	</article>
</section>
<%@ include file="/jsp/include/footer.jspf"%>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/jsp/include/html_end.jspf" %>
