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
<c:url var="displayLogAjaxUrl" value="/displayLogAjax.html"/>
<div id="content" class="homepage">
	<div class="row">
		<section class="col-lg-9 col-md-12 col-xs-12">
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
					In letzter Zeit ist es etwas ruhiger hier geworden.
					Die Umstellung von Debian auf Ubuntu Linux mit self managed Domain und Mail-Server war ein großer Schritt im vergangen Winter. 
					Weitere neue Herausforderungen ließen die Arbeit an der Tikron Website etwas in den Hintergrund geraten.
					Deshalb gibt es mit diesem Update auch nur kleinere Anpassungen.
					Grundsätzlich soll es aber weiter gehen!
					Die sogenannte "responsive Darstellung" der gesamten Site ist ein Ziel, das nicht vergessen wird...
				</p>
			</article>
			<section class="log">
				<a id="showLog" href="#" class="arrow log" title="<spring:message code='displayHomepage.link.log.description' />"><spring:message code="displayHomepage.link.log" /></a>
				<div id="logResponse" class="timestamp">
					Letzte Aktualisierung: <time datetime="2016-04-19">19.04.2016</time>
				</div>
			</section>
		</section>
		<section class="col-lg-3 col-md-12 col-xs-12">
			<%@ include file="/jsp/include/teaserbar.jspf" %>
		</section>
	</div>
</div>
<%@ include file="/jsp/include/footer.jspf"%>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<script type="text/javascript">
	$("#showLog").click(function() {
		$.ajax({
			url:'${displayLogAjaxUrl}',
			success: function(msg) {
				$("#logResponse").html( msg );
			}
		});
		return false;
	});
</script>
<%@ include file="/jsp/include/html_end.jspf"%>
