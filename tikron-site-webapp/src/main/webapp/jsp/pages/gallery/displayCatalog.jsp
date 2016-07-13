<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/gallery.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<section id="content" class="gallery catalog row">
	<div class="col-lg-9 col-md-12 col-xs-12">
		<article id="contentHeader">
			<header><h1><c:out value="${catalog.title}" /></h1></header>
			<p><c:out value="${catalog.longDescription}" escapeXml="false"/></p>
		</article>
		<section id="contentMain">
			<ul class="imageIndex">
			<c:forEach var="category" items="${categories}" varStatus="listStatus">
				<c:choose>
					<c:when test="${category.categoryType eq 'GALLERY'}">
						<c:url var="displayCategoryUrl" value="/gallery/displayCategory.html">
							<c:param name="categoryId" value="${category.id}"/>
							<c:param name="name" value="${category.seoName}"/>
						</c:url>
					</c:when>
					<c:when test="${category.categoryType eq 'REPORT'}">
						<c:url var="displayCategoryUrl" value="/travels/displayTravel.html">
							<c:param name="categoryId" value="${category.id}"/>
							<c:param name="name" value="${category.seoName}"/>
						</c:url>
					</c:when>
				</c:choose>
				<li>
					<article>
						<figure>
							<a href="${displayCategoryUrl}" class="image" title="<spring:message code='gallery.displayCatalog.figure.description' />"><img src="/images/gallery/catalog_${catalog.id}/${category.imageName}" alt="${category.title}"/></a>
						</figure>
						<div class="description clear">
							<header>
								<h2><a href="${displayCategoryUrl}"><c:out value="${category.title}" /></a></h2>
							</header>
							<c:out value="${category.shortDescription}" escapeXml="false"/>
						</div>
					</article>
				</li>
				<c:remove var="displayCategoryUrl"/>
			</c:forEach>
			</ul>
		</section>
	</div>
</section>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/jsp/include/html_end.jspf" %>
