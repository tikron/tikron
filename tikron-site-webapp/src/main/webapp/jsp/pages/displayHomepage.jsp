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
<%@ include file="/jsp/include/nav_main.jspf"%>
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
					Immerhin jeder dritte Gast auf Tikron ist mobil, also mit seinem Smartphone unterwegs.
					Deshalb wurde nun für alle Seiten eine passende Darstellung programmiert.
					Also kein lästiges Fingerspreizen und Augenblinzeln mehr.
					Das ganze nennt sich übrigens responsive Design und ist aktueller Stand der Technik.
					Schönes Ding.
				</p>
			</article>
			<div class="log">
				<a id="showLog" href="${displayLogUrl}" class="arrow log ajax" data-response-target="#logResponse" title="<spring:message code='displayHomepage.link.log.description' />"><spring:message code="displayHomepage.link.log" /></a>
				<div id="logResponse" class="timestamp">
					Letzte Aktualisierung: <time datetime="2016-07-28">28.07.2016</time>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-12 col-xs-12">
			<%@ include file="/jsp/include/teaserbar.jspf" %>
		</div>
	</div>
</div>
<%@ include file="/jsp/include/footer.jspf"%>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/jsp/include/html_end.jspf"%>
