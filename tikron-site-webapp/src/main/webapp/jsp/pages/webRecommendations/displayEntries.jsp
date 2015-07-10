<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/links.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<section id="content" class="links">
	<div id="contentWrap">
		<article id="contentHeader">
			<figure><img src="/images/links/btx.jpg" alt="Wegweiser" /></figure>
			<header><h1>Surftipps</h1></header>
			<p>
				Das Bildungsangebot von "Tikron" soll hier durch Verweise auf weitere Angebote zu Kunst, Kultur und Internet ergänzt werden.
				Denn es mehren sich Anzeichen, dass es deisbezüglich bei dem einen oder anderen Nachholbedarf gibt.
			</p>
			<p>
				Auch wenn es sich hier um Empfehlungen handelt, muss aus rechtlichen Gründen darauf hingewiesen werden, dass der Betreiber von Tikron nicht unbedingt mit der Auffassung der unter den Verweisen geführten Inhalten übereinstimmt oder gar dafür verantwortlich ist.
				Sollten akzeptable Gründe für das Korrigieren oder Entfernen von Links bestehen, wird diesem Wunsch natürlich umgehend entsprochen.
				Auch <a class="arrow" href="${sendContactMessageUrl}">Tipps für neue Links</a> werden gerne angenommen.
			</p>
		</article>
		<section id="contentMain">
			<ul class="imageIndex">
				<c:forEach var="webRecommendation" items="${webRecommendations}" varStatus="listStatus">
				<%@ include file="/jsp/include/list_border.jspf" %>
				<li<c:if test="${not empty liClass}"> class="${liClass}"</c:if>>
					<article>
					<figure>
						<a href="${webRecommendation.url}" rel="nofollow" class="image" title="<spring:message code='webRecommendations.icon.description'/>">
							<img src="/images/links/thumbs/${webRecommendation.imageName}" alt="${webRecommendation.title}" />
						</a>
					</figure>
					<div class="description">
							<header><h2 class="link"><a href="${webRecommendation.url}" rel="nofollow">${webRecommendation.title}</a></h2></header>
							<p>${webRecommendation.description}</p>
						<div class="clear"></div>
					</div>
					</article>
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
<%@ include file="/jsp/include/html_end.jspf" %>
