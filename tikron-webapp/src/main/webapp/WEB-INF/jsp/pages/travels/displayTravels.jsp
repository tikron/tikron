<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/title.jspf" %>
<%@ include file="/WEB-INF/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/travels.css${contentVersionParam}" />
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf" %>
<c:url var="displayBalticSea2014Url" value="/travels/displayBalticSea2014.html" />
<div id="content" class="travels index">
	<div class="row">
		<div class="col-lg-9 col-md-12 col-xs-12">
			<article id="contentHeader">
				<header><h1>Reisen</h1></header>
				<p>So oft es geht, fahren wir mit dem Campingmobil raus und schauen uns andere Länder an. Meistens geht es nach Skandinavien, weil man sich dort am besten von dem hektischen Leben in Deutschland erholen kann. Auch Polen und das Baltikum wurden schon erkundet. Hier gibt es als westlich geprägten Europäer noch viel zu entdecken. In südlicher Himmelsrichtung waren wir nun schon drei Mal in Kroatien. Die dalmatinischen Inseln sind sehr schön, das Mittelmeer auch im Spätsommer noch warm und die Strände nicht so zugebaut wie in Frankreich oder Italien.</p>
				<p>Hier wird es wohl nicht den großen Reiseblog mit Berichten von aufregenden Abenteuern aus der ganzen Welt geben. Trotzdem könnt ihr hier auch kleinere Geschichten lesen und viele schöne Fotos sehen.</p>
			</article>
			<div id="contentMain">
				<ul class="imageIndex">
				<li>
					<a href="${displayBalticSea2014Url}" class="image"><img src="/images/travels/balticsee.png" alt="Einmal um die Ostsee" /></a>
					<div class="description clear">
						<article>
							<header><h2><a href="${displayBalticSea2014Url}">Einmal um die Ostsee</a></h2></header>
							<p>Im Jahr 2014 sind wir mit dem VW-Bus einmal um die Ostsee gefahren. Naja, nicht ganz herum. Lappland und Russland wären dann doch etwas zu weit gewesen. Trotzdem sind wir durch 6 Länder gefahren und haben eine für uns bis dahin unbekannte Gegend erkundet. Besonders Estland war so schön wie einsam. Der Bericht beschreibt die Tour mit vielen Erlebnissen.</p>
						</article>
					</div>
				</li>
				<c:if test="${not empty catalog}">
				<c:forEach var="category" items="${categories}" varStatus="listStatus">
					<c:if test="${category.visible eq true}">
						<c:url var="displayCategoryUrl" value="/gallery/displayCategory.html">
							<c:param name="categoryId" value="${category.id}"/>
							<c:param name="name" value="${category.seoName}"/>
						</c:url>
						<li>
							<article>
								<figure>
									<a href="${displayCategoryUrl}" class="image"><img src="/images/gallery/catalog_${catalog.id}/${category.imageName}" alt="${category.title}"/></a>
								</figure>
								<div class="description clear">
										<header><h2><a href="${displayCategoryUrl}"><c:out value="${category.title}" /></a></h2></header>
										<p><c:out value="${category.shortDescription}" escapeXml="false"/></p>
								</div>
							</article>
						</li>
						<c:remove var="displayCategoryUrl"/>
					</c:if>
				</c:forEach>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
