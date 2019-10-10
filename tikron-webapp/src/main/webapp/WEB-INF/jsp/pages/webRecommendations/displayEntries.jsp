<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/title.jspf" %>
<%@ include file="/WEB-INF/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/links.css${contentVersionParam}" />
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf" %>
<div id="content" class="links">
	<div class="row">
		<div class="col-lg-9 col-md-12 col-xs-12">
			<article id="contentHeader">
				<figure><img src="/images/links/btx.jpg" alt="Wegweiser" /></figure>
				<header class="content"><h1>Surftipps</h1></header>
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
			<div id="contentMain">
				<ul class="imageIndex">
					<c:forEach var="webRecommendation" items="${webRecommendations}" varStatus="listStatus">
					<li>
						<article>
						<figure>
							<a href="${webRecommendation.url}" rel="nofollow" class="image" title="<spring:message code='webRecommendations.icon.description'/>">
								<img src="/images/links/thumbs/${webRecommendation.imageName}" alt="${webRecommendation.title}" />
							</a>
						</figure>
						<div class="description clear">
								<header><h2 class="link"><a href="${webRecommendation.url}" rel="nofollow">${webRecommendation.title}</a></h2></header>
								<p>${webRecommendation.description}</p>
						</div>
						</article>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
