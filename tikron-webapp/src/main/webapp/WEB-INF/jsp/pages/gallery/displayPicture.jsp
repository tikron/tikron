<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tikron" uri="/WEB-INF/tikron-webapp.tld" %>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/title.jspf" %>
<%@ include file="/WEB-INF/jsp/include/head.jspf" %>
<script src="/lib/jquery-placeholder-2.3.1/jquery.placeholder.min.js"></script>
<script src="/lib/picturefill-3.0.3/dist/picturefill.min.js"></script>
<script src="/lib/jRate.min.js" ></script>
<script src="/lib/hammer.min.js" ></script>
<script src="/js/rating.js" ></script>
<script src="/js/comment.js" ></script>
<script src="/js/touch.js" ></script>
<link rel="stylesheet" type="text/css" href="/css/gallery.css${contentVersionParam}" />
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf" %>
<div id="content" class="gallery picture">
	<%@ include file="/WEB-INF/jsp/pages/gallery/include/pictureContent.jspf" %>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<script type="text/javascript">
	var tikronComment = new TikronComment({'msg' : {author:'<spring:message code="comments.author"/>', date: '<spring:message code="comments.date"/>'}});
	tikronComment.init();
	var tikronRating = new TikronRating({'msg' : {ratingCount:'<spring:message code="rating.count"/>', ratingEmpty: '<spring:message code="rating.empty"/>'}});
	tikronRating.init();
	$('#content figure img').one('load', function(e) {
	  $('nav#pager').show();
	  $('span.counter').show();
	});
</script>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
