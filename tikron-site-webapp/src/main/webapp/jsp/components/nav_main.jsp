<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/include/environment.jspf"%>
<c:url var="displaySubNavUrl" value="/displaySubNav.html"><c:param name="catalogId" /></c:url>
<nav id="main">
	<div class="top clear">
		<div id="toggleNav" class="clear"><i class="fa fa-bars fa-2x" aria-hidden="true"></i><%--<span>Menü</span> --%></div>
		<ul>
			<%-- HOMEPAGE --%>
			<li<c:if test="${topNavAction eq 'displayHomepage'}"><c:out value=" class=\"current\"" escapeXml="false"/></c:if>>
				<a id="navTop_home" href="${displayHomepageUrl}"><spring:message code="navMain.homepage"/></a>
			</li>
			<%-- CATALOGS --%>
			<c:forEach var="catalog" items="${topNavCatalogs}" varStatus="topNavStatus">
			<c:choose>
			<%-- ### Direct link to single category of catalog ### --%>
			<c:when test="${fn:length(catalog.categories) eq 1}">
				<c:set var="category" value="${catalog.categories[0]}" />
				<li<c:if test="${topNavCategoryId eq category.id}"><c:out value=" class=\"current\"" escapeXml="false"/></c:if>>
					<c:url var="displayCategoryUrl" value="/gallery/displayCategory.html"><c:param name="categoryId" value="${category.id}"/><c:param name="name" value="${category.seoName}"/></c:url>
					<a id="navTop_${catalog.id}" href="${displayCategoryUrl}"><c:out value="${category.title}"></c:out></a>
					<c:remove var="displayCategoryUrl"/>
				</li>
			</c:when>
			<%-- ### Catalog link ### --%>
			<c:otherwise>
				<c:set var="topClass" value="hasSubNav"></c:set>
				<c:if test="${topNavCatalogId eq catalog.id}"><c:set var="topClass" value="${topClass} current"></c:set></c:if>
				<li id="navTop_${catalog.id}" class="${topClass}">
					<c:url var="displayCatalogUrl" value="/gallery/displayCatalog.html"><c:param name="catalogId" value="${catalog.id}" /><c:param name="name" value="${catalog.seoName}" /></c:url>
					<a href="${displayCatalogUrl}"><c:out value="${catalog.title}"></c:out><i class="fa fa-caret-down"></i></a>
					<c:remove var="displayCatalogUrl"/>
					<%-- ### Render sub navigation with list of categories ### --%>
					<c:choose>
					<c:when test="${catalog.id eq topNavCatalogId}">
						<div id="navSub_${catalog.id}" class="sub active permanent">
							<%@ include file="/jsp/components/nav_main_sub.jspf"%>
						</div>
					</c:when>
					<c:otherwise>
						<div id="navSub_${catalog.id}" class="sub temporary"></div>
					</c:otherwise>
					</c:choose>
				</li>
				<c:remove var="topClass"/>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			<%-- CLIPS --%>
			<c:url var="displayClipsUrl" value="/clips/displayClips.html"></c:url>
			<li<c:if test="${topNavNamespace eq '/clips'}"><c:out value=" class=\"current\"" escapeXml="false"/></c:if>>
				<a id="navTop_clips" href="${displayClipsUrl}"><spring:message code="navMain.clips"/></a>
			</li>
			<c:remove var="displayClipsUrl"/>
			<%-- WEB RECOMMENDATIONS --%>
			<c:url var="displayWebRecommendationsUrl" value="/webRecommendations/displayEntries.html"></c:url>
			<li<c:if test="${topNavNamespace eq '/webRecommendations'}"><c:out value=" class=\"current\"" escapeXml="false"/></c:if>>
				<a id="navTop_webRecommendations" href="${displayWebRecommendationsUrl}"><spring:message code="navMain.webRecommendations"/></a>
			</li>
			<c:remove var="displayWebRecommendationsUrl"/>
			<%-- CONTACT--%>
			<li<c:if test="${topNavAction eq 'sendContactMessage'}"><c:out value=" class=\"current\"" escapeXml="false"/></c:if>>
				<a id="navTop_contact" href="${sendContactMessageUrl}"><spring:message code="navMain.contact"/></a>
			</li>
		</ul>
	</div>
</nav>
<script type="text/javascript">
	$(document).ready(function() {
		var tikronNav = new TikronNav({"loadSubNavUrl": "${displaySubNavUrl}"});
		tikronNav.init();
	});
</script>
