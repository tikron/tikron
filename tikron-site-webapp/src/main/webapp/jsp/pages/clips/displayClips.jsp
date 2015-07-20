<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tikron" uri="/WEB-INF/tikron-webapp.tld" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<script type="text/javascript" src="/lib/jRate.min.js" ></script>
<script type="text/javascript" src="/js/rating.js" ></script>
<link rel="stylesheet" type="text/css" href="/css/clips.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<section id="content" class="clips">
	<div id="contentWrap">
		<article id="contentHeader">
			<header><h1>Kurzfilme</h1></header>
			<p>
				In unregelmäßigen Abständen wird hier im Kurzfilmkino ein neuer Film gezeigt.
				Mit der <a href="http://www.nikon.de/de_DE/product/digital-cameras/coolpix/all-weather/coolpix-aw110" rel="nofollow">Nikon Coolpix AW110<i class="fa fa-external-link"></i></a> kann man, wie mit vielen anderen Kompaktkameras auch, Filme in HD-Qualität drehen.
				Weil sie Staub- und Wasserdicht ist, sind auch Aufnahmen in ungewöhnlichem Terrain möchlich.
				So war sie einige Male beim Schnorcheln dabei.
				Auch Staub und Qualm beim Tracktor Pulling sind kein Problem.
				Viel Spaß beim gucken.
				Der Eintritt ist wie immer kostenlos und das Kino frei von Werbung.
			</p>
		</article>
		<section id="contentMain">
			<ul class="imageIndex">
				<c:forEach var="clip" items="${clips}" varStatus="listStatus">
				<c:set var="dateRecordedHtml"><fmt:formatDate value="${clip.dateRecorded}" type="date" pattern="yyyy-MM" /></c:set>
				<c:set var="playtimeHtml"><fmt:formatDate value="${clip.playtime}" type="time" pattern="'PT'm'M'ss'S'" /></c:set>
				<%@ include file="/jsp/include/list_border.jspf" %>
				<li<c:if test="${not empty liClass}"> class="${liClass}"</c:if>>
					<c:url var="displayClipUrl" value="/clips/displayClip.html">
						<c:param name="clipId" value="${clip.id}"/>
						<c:param name="name" value="${clip.seoName}"/>
					</c:url>
					<article>
						<figure>
							<a href="${displayClipUrl}" class="image block">
								<img src="/images/clips/${clip.name}.png" alt="${clip.title}"/>
								<span class="duration"><time datetime="${playtimeHtml}"><fmt:formatDate value="${clip.playtime}" type="time" pattern="m:ss" /></time></span>
							</a>
						</figure>
						<div class="description">
							<header>
								<h2><a href="${displayClipUrl}"><c:out value="${clip.title}" /></a></h2>
								<span class="date"><time datetime="${dateRecordedHtml}"><fmt:formatDate value="${clip.dateRecorded}" type="date" pattern="MMM. yyyy" /></time></span>
							</header>
							<p><c:out value="${clip.shortDescription}" /></p>
							<c:set var="rating" value="${ratings[clip.id]}" />
							<%@ include file="/jsp/pages/user/include/addRating.jspf" %>
							<div class="clear"></div>
						</div>
					</article>
					<c:remove var="displayClipUrl"/>
				</li>
				<c:remove var="liClass"/>
				</c:forEach>
			</ul>
		</section>
	</div>
	<div class="clear"></div>
</section>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<c:if test="${not browserLowerIE9}">
<c:url var="addRatingUrl" value="/clips/addClipRating.json"><c:param name="clipId" value=""/><c:param name="ratingValue" value=""/></c:url>
<spring:message code="rating.count" var="msgRatingCount"/>
<spring:message code="rating.empty" var="msgRatingEmpty"/>
<script type="text/javascript">
	$(document).ready(function() {
		var tikronRating = new TikronRating({'addRatingUrl': '${addRatingUrl}',	'msg' : 
			{ratingCount:'<spring:message code="rating.count"/>', ratingEmpty: '<spring:message code="rating.empty"/>'}});
		tikronRating.init();
	});
</script>
</c:if>
<%@ include file="/jsp/include/html_end.jspf" %>
