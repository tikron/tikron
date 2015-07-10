<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf"%>
<%@ include file="/jsp/include/htmlhead_start.jspf"%>
<%@ include file="/jsp/include/title.jspf"%>
<%@ include file="/jsp/include/head.jspf"%>
<script type="text/javascript" src="/lib/jRate.min.js" ></script>
<script type="text/javascript" src="/js/rating.js" ></script>
<link rel="stylesheet" type="text/css" href="/css/clips.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf"%>
<%@ include file="/jsp/include/htmlbody_start.jspf"%>
<%@ include file="/jsp/include/header.jspf"%>
<%@ include file="/jsp/include/nav_main.jspf"%>
<c:url var="displayClipsUrl" value="/clips/displayClips.html"></c:url>
<section id="content" class="clips">
	<div id="contentWrap">
		<section id="contentLeft">
			<video<c:if test='${not empty clip.videoWidth}'> width="${clip.videoWidth}"</c:if><c:if test='${not empty clip.videoHeight}'> height="${clip.videoHeight}"</c:if> controls="controls">
				<source src="${fileArchiveUrl}${clip.videoUris['ogv']}" type="video/ogg" />
				<source src="${fileArchiveUrl}${clip.videoUris['mp4']}" type="video/mp4" />
				<spring:message code="error.missingVideoPlugin" /> 
			</video>
			<!--
			<object data="${videoUrl}${clip.name}.swf" type="application/x-shockwave-flash" width="100%" height="100%">
				<param name="movie" value="${videoUrl}${clip.name}.swf">
				<param name="quality" value="high">
				<param name="scale" value="exactfit">
				<param name="menu" value="true">
				<param name="bgcolor" value="#000040">
			</object>
			-->
		</section>
		<section id="contentRight" class="intro">
			<article class="description">
				<header><h1><c:out value="${clip.title}" /></h1></header>
				<p><c:out value="${clip.formattedLongDescription}" escapeXml="false" /></p>
			</article>
			<%@ include file="/jsp/pages/user/include/addRating.jspf" %>
			<ul class="buttonBar vt">
				<li><a href="${fileArchiveUrl}${clip.videoUris['mp4']}" class="button button_green" title="<spring:message code='button.videoPlayer.description'/>"><i class="fa fa-film"></i><spring:message code="button.videoPlayer"/></a></li>
				<li><a href="${displayClipsUrl}" class="button button_green" title="<spring:message code='button.allVideos.description'/>"><i class="fa fa-hand-o-right"></i><spring:message code="button.allVideos"/></a></li>
			</ul>
		</section>
	</div>
	<div class="clear"></div>
</section>
<%@ include file="/jsp/include/footer.jspf"%>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<c:if test="${not browserLowerIE9}">
<c:url var="addRatingUrl" value="/clips/addClipRating.json"><c:param name="clipId" value=""/><c:param name="ratingValue" value=""/></c:url>
<script type="text/javascript">
	$(document).ready(function() {
		var tikronRating = new TikronRating({'addRatingUrl': '${addRatingUrl}',	'msg' : 
		{ratingCount:'<spring:message code="rating.count"/>', ratingEmpty: '<spring:message code="rating.empty"/>'}});
		tikronRating.init();
	});
</script>
</c:if>
<%@ include file="/jsp/include/html_end.jspf" %>
