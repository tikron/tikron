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
<script type="text/javascript" src="/lib/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="/lib/jRate.min.js" ></script>
<script type="text/javascript" src="/js/rating.js" ></script>
<script type="text/javascript" src="/js/comment.js" ></script>
<link rel="stylesheet" type="text/css" href="/css/gallery.css${contentVersionParam}" />
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<c:url var="displayCategoryUrl" value="/gallery/displayCategory.html">
	<c:param name="categoryId" value="${picture.category.id}"/>
	<c:param name="name" value="${picture.category.seoName}"/>
</c:url>
<c:url var="addCommentUrl" value="/gallery/addComment.html">
	<c:param name="pictureId" value="${picture.id}"/>
</c:url>
<article id="content" class="gallery picture clear">
	<section id="contentLeft">
		<figure>
			<a href="${displayCategoryUrl}"><img src="<c:out value="${imageServerUrl}${picture.image.imageUris['galleryImage']}" />" alt="${picture.title}" /></a>
		</figure>
		<c:if test="${picture.category.commentable and picture.id ne 731}">
		<section class="commentBox">
			<%@ include file="/jsp/pages/user/include/addCommentForm.jspf" %>
			<%@ include file="/jsp/pages/user/include/commentList.jspf" %>
		</section>
		</c:if>
	</section>
	<section id="contentRight" class="intro">
		<div id="galleryInfo" class="teaser">
			<div class="description box">
				<header>
					<h1><c:out value="${not empty picture.title ? picture.title : picture.category.title}" /></h1>
				</header>
				<p>
					<c:choose>
						<c:when test="${not empty picture.description}"><c:out value="${picture.description}" escapeXml="false" /></c:when>
						<c:otherwise><spring:message code="gallery.displayPicture.emptyDescription" /></c:otherwise>
					</c:choose>
					<c:if test="${picture.category.commentable}">
						<br /><br />
						<a id="addComment" href="#" class="arrow"><spring:message code="link.addPictureComment"/></a>
					</c:if>
				</p>
			</div>
			<c:if test="${picture.category.rateable and not browserLowerIE9}">
			<%@ include file="/jsp/pages/user/include/addRating.jspf" %>
			</c:if>
			<%@ include file="include/picturePager.jspf" %>
			<ul class="buttonBar vt afterPager">
				<li><a href="${displayCategoryUrl}" class="button button_green" title="<spring:message code='button.allPictures.description'/>"><i class="fa fa-hand-o-right"></i><spring:message code="button.allPictures"/></a></li>
			</ul>
		</div>
	</section>
</article>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<c:if test="${picture.category.commentable}">
<script type="text/javascript">
	$(document).ready(function() {
		// Bind placeholder plugin
		$('input, textarea').placeholder();
		// Bind srolling to comment box
		$('a#addComment').click(function (){
			$('html, body').animate({
				scrollTop: $('.commentBox').offset().top
			}, 500);
		});
		// Init. comment form handler
		var tikronComment = new TikronComment({'msg' : {author:'<spring:message code="comments.author"/>', date: '<spring:message code="comments.date"/>'}});
		tikronComment.init();
	});
</script>
</c:if>
<c:if test="${picture.category.rateable and not browserLowerIE9}">
<c:url var="addRatingUrl" value="/gallery/addPictureRating.json"><c:param name="pictureId" value=""/><c:param name="ratingValue" value=""/></c:url>
<script type="text/javascript">
	$(document).ready(function() {
		// Init. rating box
		var tikronRating = new TikronRating({'addRatingUrl': '${addRatingUrl}',	'msg' : 
			{ratingCount:'<spring:message code="rating.count"/>', ratingEmpty: '<spring:message code="rating.empty"/>'}});
		tikronRating.init();
	});
</script>
</c:if>
<%@ include file="/jsp/include/html_end.jspf" %>
