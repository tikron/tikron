<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/gallery.css${contentVersionParam}" />
<c:if test="${category.displayType eq 'OVERLAY'}">
	<%-- Show overlay pictures with "qjeury plugin colorbox" --%>
	<link rel="stylesheet" type="text/css" href="/lib/colorbox/colorbox.css" />
</c:if>
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<section id="content" class="gallery category">
	<section id="contentLeft">
		<ul class="thumbs">
		<c:forEach var="picture" items="${pictures}" varStatus="listStatus">
			<c:url var="displayPictureUrl" value="/gallery/displayPicture.html">
				<c:param name="pictureId" value="${picture.id}"></c:param>
				<c:param name="name" value="${picture.seoName}"/>
			</c:url>
			<%@ include file="/jsp/include/list_border.jspf" %>
			<li<c:if test="${not empty liClass}"> class="${liClass}"</c:if>>
				<figure>
				<c:choose>
				<c:when test="${category.displayType eq 'OVERLAY'}">
					<%-- Show image with "fancybox" --%>
					<a class="colorbox" href="<c:out value="${imageServerUrl}${picture.image.imageUris['galleryImage']}" />" title="<spring:message code='gallery.thumnail.description'/>" data-caption="${picture.title}">
						<img src="<c:out value="${imageServerUrl}${picture.image.imageUris['galleryThumbnail']}" />" alt="${picture.title}" />
					</a>
				</c:when>
				<c:otherwise>
					<%-- Show image in new page --%>
					<a href="${displayPictureUrl}" title="<spring:message code='gallery.thumnail.description'/>">
						<img src="<c:out value="${imageServerUrl}${picture.image.imageUris['galleryThumbnail']}" />" alt="${picture.title}" />
					</a>
				</c:otherwise>
				</c:choose>
					<figcaption><c:out value="${picture.title}"></c:out></figcaption>
				</figure>
			</li>
			<c:remove var="liClass"/>
			<c:remove var="displayPictureUrl" />
		</c:forEach>
		</ul>
	</section>
	<section id="contentRight" class="intro">
		<div class="description">
			<article>
				<header><h1><c:out value="${category.title}" /></h1></header>
				<p><c:out value="${category.longDescription}" escapeXml="false"/></p>
			</article>
		</div>
		<%-- TODO: Show back button, if number of categories associated to current catalog is 1. --%>
		<c:if test="${category.catalog.id eq 1 or category.catalog.id eq 2}">
			<ul class="buttonBar vt">
				<c:url var="goBackUrl" value="/gallery/displayCatalog.html"><c:param name="catalogId" value="${category.catalog.id}" /><c:param name="name" value="${category.catalog.seoName}" /></c:url>
				<li><a href="${goBackUrl}" class="button button_green" title="<spring:message code='button.allTypes.description' arguments='${category.catalog.title}' />"><i class="fa fa-hand-o-right"></i><spring:message code="button.allTypes" arguments="${category.catalog.title}" /></a></li>
				<c:remove var="goBackUrl" />
			</ul>
		</c:if>
	</section>
	<div class="clear"></div>
</section>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<c:if test="${category.displayType eq 'OVERLAY'}">
	<%-- Show overlay pictures with "qjeury plugin colorbox" --%>
	<script type="text/javascript" src="/lib/jquery.colorbox-min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('a.colorbox').colorbox({photo:true, rel:'colorbox', current: '<spring:message code="colorbox.current"/>', title: function(){return $(this).attr('data-caption');}});
		});
	</script>
</c:if>
<%@ include file="/jsp/include/html_end.jspf" %>
