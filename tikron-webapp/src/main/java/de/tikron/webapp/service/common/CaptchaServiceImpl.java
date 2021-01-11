/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

import de.tikron.webapp.util.CaptchaEngine;

/**
 * Default implementation of the Captcha service.
 * 
 * @author Titus Kruse
 * @since 16.06.2010
 */
@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {

	private ImageCaptchaService imageCaptchaService;

	public BufferedImage getCaptchaImage(String captchaId) throws CaptchaServiceException {
		return getImageCaptchaService().getImageChallengeForID(captchaId);
	}

	public boolean validateCaptchaCode(String captchaId, String captchaCode) {
		return getImageCaptchaService().validateResponseForID(captchaId, captchaCode.toUpperCase()).booleanValue();
	}

	/**
	 * Returnes the Captcha service.
	 * 
	 * @return The Captcha service.
	 */
	private ImageCaptchaService getImageCaptchaService() {
		if (imageCaptchaService == null) {
			imageCaptchaService = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(),
					new CaptchaEngine(), 180, 1000, 750);
		}
		return imageCaptchaService;
	}

}
