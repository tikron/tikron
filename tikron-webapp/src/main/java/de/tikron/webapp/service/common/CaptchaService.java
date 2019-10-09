package de.tikron.webapp.service.common;

import java.awt.image.BufferedImage;

import com.octo.captcha.service.CaptchaServiceException;

public interface CaptchaService {

	/**
	 * Generates a new CaptchaImage, saves a reference between the captchaId (usually the sessionId) and the captchaCode
	 * the user has to enter. Returns the CaptchaImage as a ByteArray for further processing.
	 * 
	 * @param captchaId The captcha ID to store the generated code.
	 * @return The Captcha image.
	 * @throws CaptchaServiceException
	 */
	public BufferedImage getCaptchaImage(String captchaId) throws CaptchaServiceException;

	/**
	 * Validates the captchaId (usually the sessionId) and a given captchaCode. Returns true if the catptchaCode is valid
	 * for the given captchaId.
	 * 
	 * @param captchaId The captcha ID to find the generated code.
	 * @param captchaCode The code to validate.
	 * @return true, if the Captcha code is valid.
	 */
	public boolean validateCaptchaCode(String captchaId, String captchaCode);

}
