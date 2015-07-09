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
<section id="content" class="homepage">
	<section id="contentLeft">
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
				Nur eine Woche ist seit dem letzten Update vergangen.
				Dieses Mal gibt es wieder ein neues Feature auf Tikron:
				Fotos und Filme könnt ihr nun auch bewerten.
				Wer also keine Lust hat, einen Kommentar zu verfassen, aber doch irgendwie seine Begeisterung zum Ausdruck bringen möchte, klickt einfach auf die Sternchen.
				Je mehr Sternchen, des besser die Bewertung. 
			</p>
		</article>
		<section class="log">
			<a id="showLog" href="javascript:void(0);" class="arrow log"><spring:message code="displayHomepage.link.log" /></a>
			<div id="logResponse" class="timestamp">
				Letzte Aktualisierung: 3.05.2015
			</div>
		</section>
	</section>
	<section id="contentRight">
		<%@ include file="/jsp/include/teaserbar.jspf" %>
	</section>
	<div class="clear"></div>
</section>
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
