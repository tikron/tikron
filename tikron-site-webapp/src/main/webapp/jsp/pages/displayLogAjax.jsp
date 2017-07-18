<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="log">
	<%@ include file="displayLogContent.jspf" %>
</div>
<script type="text/javascript">
	try {
		if (Piwik) {
			var piwikTracker = Piwik.getAsyncTracker();
			piwikTracker.setCustomUrl('${fullRequestURI}');
			piwikTracker.setDocumentTitle('${pageTitle}');
			piwikTracker.trackPageView();
			piwikTracker.enableLinkTracking();
		}
	} catch (err) {}
</script>