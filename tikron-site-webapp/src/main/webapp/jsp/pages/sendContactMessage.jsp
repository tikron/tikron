<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/jsp/include/doctype.jspf"%>
<%@ include file="/jsp/include/environment.jspf" %>
<%@ include file="/jsp/include/htmlhead_start.jspf" %>
<%@ include file="/jsp/include/title.jspf" %>
<%@ include file="/jsp/include/head.jspf" %>
<link rel="stylesheet" type="text/css" href="/css/main.css${contentVersionParam}" />
<script type="text/javascript" src="/js/captcha.js" ></script>
<script type="text/javascript" src="/js/contact.js" ></script>
<%@ include file="/jsp/include/htmlhead_end.jspf" %>
<%@ include file="/jsp/include/htmlbody_start.jspf" %>
<%@ include file="/jsp/include/header.jspf" %>
<%@ include file="/jsp/include/nav_main.jspf" %>
<c:url var="sendContactMessageUrl" value="/sendContactMessage.json" />
<c:url var="captchaImageUrl" value="/captchaImage.html"><c:param name="random" /></c:url>
<section id="content">
	<div id="contentWrap">
		<section id="contentHeader">
			<header><h1><spring:message code="sendContactMessage.headline" /></h1></header>
			<p><spring:message code="sendContactMessage.featureText" /></p>
			<a class="arrow email_link" href="#"></a>
		</section>
		<section id="contentMain">
			<form:form commandName="contactMessage" action="${sendContactMessageUrl}" id="contact">
				<ul class="globalMsg"><form:errors element="li" cssClass="error"/></ul>
				<fieldset>
					<dl id="name">
						<dt><form:label path="name"><spring:message code="sendContactMessage.label.name" arguments="*"/></form:label></dt>
						<dd><form:input path="name" /><span class="error"><form:errors path="name" /></span></dd>
					</dl>
					<dl id="email" class="right">
						<dt><form:label path="email"><spring:message code="sendContactMessage.label.email" arguments="*"/></form:label></dt>
						<dd><form:input path="email" type="email" /><span class="error"><form:errors path="email" /></span></dd>
					</dl>
					<div class="clear"></div>
					<dl id="message">
						<dt><form:label path="message"><spring:message code="sendContactMessage.label.message" arguments="*"/></form:label></dt>
						<dd><form:textarea path="message" rows="5" cols="60" /><span class="error"><form:errors path="message" /></span></dd>
					</dl>
					<div class="clear"></div>
					<dl id="captchaImage">
						<dt><a href="#" class="arrow"><spring:message code="captchaCode.link.reload" /></a></dt>
						<dd><img src="${captchaImageUrl}" alt="<spring:message code="captchaCode.image.alt" />" title="<spring:message code="captchaCode.image.alt" />" /></dd>
					</dl>
					<dl id="captchaCode" class="right">
						<dt><form:label path="captchaCode"><spring:message code="captchaCode.label" arguments="*"/></form:label></dt>
						<dd><form:input path="captchaCode" /><span class="error"><form:errors path="captchaCode" /></span></dd>
					</dl>
					<div class="clear"></div>
					<ul class="buttonBar">
						<li><button type="submit" class="button button_green"><i class="fa fa-send-o"></i><spring:message code="sendContactMessage.button.sendMessage" /></button></li>
	<%-- 					<li><button type="button" class="button button_green" onclick="location.href='/';"><i class="fa fa-reply"></i><spring:message code="button.cancel" /></button></li> --%>
					</ul>
				</fieldset>
				<div class="clear"></div>
			</form:form>
		</section>
	</div>
	<div class="clear"></div>
</section>
<%@ include file="/jsp/include/footer.jspf" %>
<%@ include file="/jsp/include/htmlbody_end.jspf" %>
<script type="text/javascript">
	$(document).ready(function() {
		var tikronCaptcha = new TikronCaptcha({'captchaImageUrl': '${captchaImageUrl}'});
		tikronCaptcha.init();
		var tikronContact = new TikronContact({'reloadCaptcha' : tikronCaptcha.reloadImage});
		tikronContact.init();
	});
 </script>
<%@ include file="/jsp/include/html_end.jspf" %>
