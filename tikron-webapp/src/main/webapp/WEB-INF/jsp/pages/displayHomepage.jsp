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
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf"%>
<c:url var="displayLogUrl" value="/displayLog.html"/>
<div id="content" class="homepage">
	<div class="row">
		<div class="col-lg-9 col-md-12 col-xs-12">
			<h1><spring:message code="displayHomepage.headline" /></h1>
			<article class="feature">
				<figure>
					<img src="/images/rtts_270.gif" alt="<spring:message code="displayHomepage.logo.alt" />" />
				</figure>
				<h2><spring:message code="displayHomepage.feature.headline" /></h2>
				<p><spring:message code="displayHomepage.feature.text" /></p>
			</article>
			<article class="news">
				<h2><spring:message code="displayHomepage.news.headline" /></h2>
				<p>
					Die meisten Menschen halten heute Instagram, Facebok und Youtube für das Internet.
					Seine Ideen und Erfahrungen zu präsentieren geht dort allerdings auch wesentlich einfacher, als mit der Programmierung einer eigenen Website.
					Für viele ist auch Google der Einstieg ins Netz.
					Weil dort unter den ersten zehn Suchergebnissen fast immer Links zu den großen Anbietern erscheinen, wird man als kleines Portal kaum noch wahrgenommen.
					Deshalb habe ich die Lust an der Weiterentwicklung von Tikron verloren.
					Die Website bleibt aber noch eine Weile bestehen. 
				</p>
			</article>
			<%--
			<div class="log">
				<a id="showLog" href="${displayLogUrl}" class="arrow log ajax" data-response-target="#logResponse" title="<spring:message code='displayHomepage.link.log.description' />"><spring:message code="displayHomepage.link.log" /></a>
				<div id="logResponse" class="timestamp">
					Letzte Aktualisierung: <time datetime="2016-07-28">28.07.2016</time>
				</div>
			</div>
			 --%>
		</div>
		<div class="col-lg-3 col-md-12 col-xs-12">
			<%@ include file="/WEB-INF/jsp/include/teaserbar.jspf" %>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf"%>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf"%>
