/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.tikron.common.spring.bytecontent.AbstractContentTypeController;
import de.tikron.webapp.service.common.CaptchaService;

/**
 * Controller providing a random generated Captcha image as byte data.
 *
 * @date 30.12.2012
 * @author Titus Kruse
 */
@Controller
@RequestMapping("/captchaImage.html")
public class CaptchaImageController extends AbstractContentTypeController {

	private CaptchaService captchaService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.tikron.webapp.common.spring.bytecontent.AbstractContentTypeController#getData(javax.servlet.http.HttpServletRequest
	 * )
	 */
	@Override
	protected byte[] getData(HttpServletRequest request) throws Exception {
		// Fetch captcha image for the given session ID from captcha storage
		String captchaId = request.getSession().getId();
		BufferedImage image = captchaService.getCaptchaImage(captchaId);
		// Convert to JPEG
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", jpegOutputStream);
		return jpegOutputStream.toByteArray();
	}

	@Autowired
	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

}