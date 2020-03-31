<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf"%>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf"%>
<%@ include file="/WEB-INF/jsp/include/title.jspf"%>
<%@ include file="/WEB-INF/jsp/include/head.jspf"%>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf"%>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf"%>
<%@ include file="/WEB-INF/jsp/include/header.jspf"%>
<div id="content">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12">
			<article>
				<header><h1><spring:message code="displayApplicationError.headline"/></h1></header>
				<p><spring:message code="displayApplicationError.info"/></p>
				<c:if test="${not empty messageKey}">
				<br />
				<p><spring:message code="${messageKey}" arguments="${messageParam}"/></p>
				</c:if>
				<br />
				<a href="/"><spring:message code="link.homepage"/></a>
			</article>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf"%>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
