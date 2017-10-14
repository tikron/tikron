<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<div id="content" class="imprint">
	<div class="row">
		<article class="col-lg-9 col-md-12 col-xs-12">
			<header id="contentHeader">
				<h1>Impressum</h1>
				<p>Auch hier geht es nicht ganz ohne rechtliche Feinheiten ab. Deshalb an dieser Stelle Angaben zum Verantwortlichen der Website.</p>
			</header>
			<section id="contentMain" class="clear">
				<div class="address"></div>
				<p>Diese Adresse ist jedoch nur für Schriftverkehr vorgesehen, der nicht anders als auf postalischem Weg abgewickelt werden kann. Für alle anderen Fälle steht unter <a href="${sendContactMessageUrl}">Kontakt</a> eine E-Mail-Adresse zur Verfügung.</p>
				<p>Trotz sorgfältiger inhaltlicher Kontrolle übernehme ich keine Haftung für die Inhalte externer Links. Für den Inhalt der verlinkten Seiten sind ausschließlich deren Betreiber verantwortlich. Alle Inhalte meiner Seiten unterliegen - soweit nicht anders gekennzeichnet - meinem Urheberrecht. Es ist nicht gestattet, Texte oder Grafiken meiner Seite ohne meine schriftliche Einwilligung ganz oder in Teilen zu verwenden. Ebenfalls ist es nicht gestattet, eine Verlinkung von Bild-, Ton- oder Videodateien auf andereren Websites vorzunehmen.</p>
				<p>"Tikron" (ehem. Freakworm) besteht seit dem 21.05.2005. Um eine korrekte Darstellung der Website zu ermöglichen, solltest Du einen der unten aufgeführten Web-Browser mit der angegebenen Version oder einer aktuelleren Version verwenden:</p>
				<ul class="browserList">
					<li>Apple Safari 5</li>
					<li>Google Chrome 7.0</li>
					<li>Microsoft Internet Explorer 9</li>
					<li>Mozilla Firefox 4</li>
				</ul>
				<h2>Tracking-Cookie</h2>
				<iframe src="//analytics.tikron.de/index.php?module=CoreAdminHome&amp;action=optOut&amp;language=de" class="optOut"></iframe>
				<%-- Banner code not needed yet
				<div class="livewatch">
			     	<p align="center">  
				        <a href="http://www.livewatch.de" rel="nofollow"><img src="${pageContext.request.scheme}://uptime.livewatch.de/uptime/4f95b6042ea30" alt="Serverüberwachung / Servermonitoring mit Livewatch.de" border="0"></a>  
				        <br><a href="http://www.livewatch.de">Servermonitoring mit Livewatch.de</a>  
				    </p>  
				</div>
				 --%>
			</section>
		</article>
		<section class="col-lg-3 col-md-12 col-xs-12">
			<aside class="teaserbar">
				<section class="teaser">
					<figure>
						<img src="/images/antifa.gif" class="antifa" alt="Antifa" />
					</figure>
				</section>
			</aside>
		</section>
	</div>
</div>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/jsp/include/html_end.jspf" %>
