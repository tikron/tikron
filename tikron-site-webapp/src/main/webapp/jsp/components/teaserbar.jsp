<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/include/environment.jspf"%>
<aside class="teaserbar">
	<c:forEach var="teaser" items="${teasers}">
	<c:choose>
	<c:when test="${teaser.name eq 'FINDING'}">
		<section id="finding" class="teaser">
			<c:if test="${not empty teaser.picture}">
				<c:url var="displayPictureUrl" value="/gallery/displayPicture.html">
					<c:param name="pictureId" value="${teaser.picture.id}"/>
					<c:param name="name" value="${teaser.picture.seoName}"/>
				</c:url>
				<h2><spring:message code="teaser.finding.headline" /></h2>
				<figure>
					<a href="${displayPictureUrl}" target="_top"><img src="<c:out value="${imageServerUrl}${teaser.picture.image.imageUris['galleryTeaser']}" />" alt="${teaser.picture.title}" /></a>
					<figcaption><c:out value="${teaser.picture.title}"></c:out></figcaption>
				</figure>
				<c:remove var="displayPictureUrl"/>
			</c:if>
		</section>
	</c:when>
	<c:otherwise>
		<section class="teaser">
			<h2><c:out value="${teaser.title}" /></h2>
			<figure>
				<a href="${teaser.targetUrl}" rel="nofollow">
				<c:choose>
				<c:when test="${not empty teaser.imageName}"><img src="/images/teaser/${teaser.imageName}" alt="${teaser.imageAlternate}" /></c:when>
				<c:when test="${not empty teaser.picture}"><img src="${imageServerUrl}${teaser.picture.image.imageUris['galleryTeaser']}" alt="${teaser.picture.title}" /></c:when>
				</c:choose>
				</a>
				<figcaption><c:out value="${teaser.caption}" /><c:if test="${not empty teaser.subTitle}"><br /><span class="sub"><c:out value="${teaser.subTitle}" /></span></c:if></figcaption>
			</figure>
		</section>
	</c:otherwise>
	</c:choose>
	</c:forEach>
</aside>
