<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tikron" uri="/WEB-INF/tikron-webapp.tld" %>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/pages/gallery/include/pictureContent.jspf" %>
<%@ include file="/WEB-INF/jsp/include/tracking_ajax.jspf"%>
<script type="text/javascript">
	$('#content figure img').one('load', function(e) {
	  $('nav#pager').show();
	  $('span.counter').show();
	});
</script>
