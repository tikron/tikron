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
<div id="content" class="clips">
	<div class="row">
		<div class="col-lg-9 col-md-12 col-xs-12">
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
					<c:set var="dateRecordedHtml">${tikron:formatLocalDate(clip.dateRecorded, "yyyy-MM")}</c:set>
					<c:set var="playtimeHtml">${tikron:formatLocalTime(clip.playtime, "'PT'm'M'ss'S'")}</c:set>
					<li>
						<c:url var="displayClipUrl" value="/clips/displayClip.html">
							<c:param name="clipId" value="${clip.id}"/>
							<c:param name="name" value="${clip.seoName}"/>
						</c:url>
						<article>
							<figure>
								<a href="${displayClipUrl}" class="image block">
									<img src="/images/clips/${clip.name}.png" alt="${clip.title}"/>
									<span class="duration"><time datetime="${playtimeHtml}">${tikron:formatLocalizedTime(clip.playtime, "m:ss", pageContext.response.locale)}</time></span>
								</a>
							</figure>
							<div class="description clear">
								<header>
									<h2><a href="${displayClipUrl}"><c:out value="${clip.title}" /></a></h2>
									<span class="date"><time datetime="${dateRecordedHtml}">${tikron:formatLocalizedDate(clip.dateRecorded, "MMM. yyyy", pageContext.response.locale)}</time></span>
								</header>
								<p><c:out value="${clip.shortDescription}" /></p>
								<c:set var="rating" value="${ratings[clip.id]}" />
								<%@ include file="/jsp/pages/user/include/addRating.jspf" %>
							</div>
						</article>
						<c:remove var="displayClipUrl"/>
					</li>
					</c:forEach>
				</ul>
			</section>
		</div>
	</div>
</div>
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
