<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/include/doctype.jspf"%>
<%@ include file="/WEB-INF/jsp/include/environment.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/title.jspf" %>
<%@ include file="/WEB-INF/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<script type="text/javascript" src="/js/captcha.js" ></script>
<script type="text/javascript" src="/js/contact.js" ></script>
<%@ include file="/WEB-INF/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/nav_main.jspf" %>
<c:url var="sendContactMessageUrl" value="/sendContactMessage.json" />
<c:url var="captchaImageUrl" value="/captchaImage.html"><c:param name="random" /></c:url>
<div id="content">
	<div class="contact row">
		<div class="col-lg-9 col-md-9 col-xs-12">
			<section id="contentHeader">
				<header><h1><spring:message code="sendContactMessage.headline" /></h1></header>
				<p><spring:message code="sendContactMessage.featureText" /></p>
				<a class="arrow contact-email" title="<spring:message code='sendContactMessage.email.link.description'/>" href="#0" rel="nofollow"></a>
			</section>
			<div id="contentMain">
				<form:form commandName="contactMessage" action="${sendContactMessageUrl}" id="contact" class="clear ajax orange">
					<ul class="globalMsg"><form:errors element="li" cssClass="error"/></ul>
					<fieldset>
						<div class="row inner form-control" id="name">
							<div class="col-lg-4 col-md-4 col-xs-12">
								<form:label path="name"><spring:message code="sendContactMessage.label.name" arguments="*"/></form:label>
							</div>
							<div class="col-lg-6 col-md-8 col-xs-12">
								<form:input path="name" /><span class="error"><form:errors path="name" /></span>
							</div>
						</div>
						<div class="row inner form-control" id="email">
							<div class="col-lg-4 col-md-4 col-xs-12">
								<form:label path="email"><spring:message code="sendContactMessage.label.email" arguments="*"/></form:label>
							</div>
							<div class="col-lg-6 col-md-8 col-xs-12">
								<form:input path="email" type="email" /><span class="error"><form:errors path="email" /></span>
							</div>
						</div>
						<div class="row inner form-control" id="message">
							<div class="col-lg-4 col-md-4 col-xs-12">
								<form:label path="message"><spring:message code="sendContactMessage.label.message" arguments="*"/></form:label>
							</div>
							<div class="col-lg-8 col-md-8 col-xs-12">
								<form:textarea path="message" rows="5" cols="60" /><span class="error"><form:errors path="message" /></span>
							</div>
						</div>
						<div class="row inner captchaImage">
						</div>
						<div class="row inner form-control captchaCode" id="captchaCode">
							<div class="col-lg-4 col-md-4 col-xs-12">
								<form:label path="captchaCode"><spring:message code="captchaCode.label" arguments="*"/></form:label>
							</div>
							<div class="col-lg-4 col-md-4 col-xs-12 captchaInput">
								<form:input path="captchaCode" /><span class="error"><form:errors path="captchaCode" /></span>
							</div>
							<div class="col-lg-4 col-md-6 col-xs-12 captchaImage">
								<img src="${captchaImageUrl}" alt="<spring:message code="captchaCode.image.alt" />" />
								<a href="#0" rel="nofollow" class="arrow" title="<spring:message code='captchaCode.link.reload.description' />"><spring:message code="captchaCode.link.reload" /></a>
							</div>
						</div>
						<div class="row inner">
							<div class="col-lg-4 col-md-4 col-xs-12">
							</div>
							<div class="col-lg-8 col-md-8 col-xs-12">
								<ul class="buttonBar">
									<li><button type="submit" class="button button_green" title="<spring:message code='sendContactMessage.button.sendMessage.description'/>"><i class="fa fa-send-o"></i><spring:message code="sendContactMessage.button.sendMessage" /></button></li>
			<%-- 					<li><button type="button" class="button button_green" onclick="location.href='/';"><i class="fa fa-reply"></i><spring:message code="button.cancel" /></button></li> --%>
								</ul>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/include/htmlbody_end.jspf" %>
<script type="text/javascript">
	$(document).ready(function() {
		var tikronCaptcha = new TikronCaptcha({'captchaImageUrl': '${captchaImageUrl}'});
		tikronCaptcha.init();
		var tikronContact = new TikronContact({'reloadCaptcha' : tikronCaptcha.reloadImage});
		tikronContact.init();
	});
 </script>
<%@ include file="/WEB-INF/jsp/include/html_end.jspf" %>
