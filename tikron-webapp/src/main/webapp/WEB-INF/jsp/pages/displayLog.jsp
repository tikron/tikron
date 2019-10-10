<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="canonicalUrl" value="/displayLog.html" />
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/title.jspf" %>
<%@ include file="/WEB-INF/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf" %>
<div id="content" class="log">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12">
			<section id="contentHeader" class="clear">
				<figure><img src="/images/log.gif" alt="Was bisher geschah" /></figure>
				<header><h1>Was bisher geschah...</h1></header>
				<p>Wer Tikron nicht so häufig besucht, kann auf dieser Seite auch Neuigkeiten nachlesen, die vielleicht schon etwas länger her sind. Leider sind <strong>nicht mehr alle Protokolleinträge vorhanden</strong>, so dass diese nur bis in's Jahr 2007 zurück reichen.</p>
			</section>
			<div id="contentMain">
				<%@ include file="displayLogContent.jspf" %>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
