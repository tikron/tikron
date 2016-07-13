<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="canonicalUrl" value="/displayLog.html" />
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<section id="content" class="log clear">
	<div id="contentWrap">
		<section id="contentHeader" class="clear">
			<figure><img src="/images/log.gif" alt="Was bisher geschah" /></figure>
			<header><h1>Was bisher geschah...</h1></header>
			<p>Wer Tikron nicht so häufig besucht, kann auf dieser Seite auch Neuigkeiten nachlesen, die vielleicht schon etwas länger her sind. Leider sind <strong>nicht mehr alle Protokolleinträge vorhanden</strong>, so dass diese nur bis in's Jahr 2007 zurück reichen.</p>
		</section>
		<section id="contentMain">
			<%@ include file="displayLogContent.jspf" %>
		</section>
	</div>
</section>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/jsp/include/html_end.jspf" %>
