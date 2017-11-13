<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tikron" uri="/WEB-INF/tikron-webapp.tld" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<script type="text/javascript" src="/lib/jquery-placeholder-2.3.1/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="/lib/jRate.min.js" ></script>
<script type="text/javascript" src="/lib/hammer.min.js" ></script>
<script type="text/javascript" src="/js/rating.js" ></script>
<script type="text/javascript" src="/js/comment.js" ></script>
<script type="text/javascript" src="/js/touch.js" ></script>
<link rel="stylesheet" type="text/css" href="/css/gallery.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<div id="content" class="gallery picture">
	<%@ include file="/jsp/pages/gallery/include/pictureContent.jspf" %>
</div>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<%@ include file="/jsp/include/html_end.jspf" %>
<script type="text/javascript">
	var tikronComment = new TikronComment({'msg' : {author:'<spring:message code="comments.author"/>', date: '<spring:message code="comments.date"/>'}});
	tikronComment.init();
	var tikronRating = new TikronRating({'msg' : {ratingCount:'<spring:message code="rating.count"/>', ratingEmpty: '<spring:message code="rating.empty"/>'}});
	tikronRating.init();
</script>
