<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/title.jspf" %>
<%@ include file="/WEB-INF/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf" %>
<div id="content" class="imprint">
	<div class="row">
		<article class="col-lg-9 col-md-12 col-xs-12">
			<header id="contentHeader">
				<h1>Impressum</h1>
				<p>Auch hier geht es nicht ganz ohne rechtliche Feinheiten ab. Deshalb an dieser Stelle Angaben zum Verantwortlichen der Website.</p>
			</header>
			<div id="contentMain" class="clear">
				<h2>Angaben gemäß § 5 TMG</h2>
				<%@ include file="/WEB-INF/jsp/include/siteowner.jspf" %>
				<h2>Haftung für Inhalte</h2>
				<p>Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen.</p>
				<p>Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den allgemeinen Gesetzen bleiben hiervon unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.</p>
				<h2>Haftung für Links</h2>
				<p>Unser Angebot enthält Links zu externen Websites Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar.</p>
				<p>Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung nicht zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend entfernen.</p>
				<h2>Urheberrecht</h2>
				<p>Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten unterliegen dem deutschen Urheberrecht. Die Vervielfältigung, Bearbeitung, Verbreitung und jede Art der Verwertung außerhalb der Grenzen des Urheberrechtes bedürfen der schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers. Downloads und Kopien dieser Seite sind nur für den privaten, nicht kommerziellen Gebrauch gestattet.</p>
				<p>Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen.</p>
				<%--
				<iframe src="//analytics.tikron.de/index.php?module=CoreAdminHome&amp;action=optOut&amp;language=de" class="optOut"></iframe>
				 --%>
				<%-- Banner code not needed yet
				<div class="livewatch">
			     	<p align="center">  
				        <a href="http://www.livewatch.de" rel="nofollow"><img src="${pageContext.request.scheme}://uptime.livewatch.de/uptime/4f95b6042ea30" alt="Serverüberwachung / Servermonitoring mit Livewatch.de" border="0"></a>  
				        <br><a href="http://www.livewatch.de">Servermonitoring mit Livewatch.de</a>  
				    </p>  
				</div>
				 --%>
			</div>
		</article>
		<div class="col-lg-3 col-md-12 col-xs-12">
			<aside class="teaserbar">
				<div class="teaser">
					<figure>
						<img src="/images/antifa.gif" class="antifa" alt="Antifa" />
					</figure>
				</div>
			</aside>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
