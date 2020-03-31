/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.awt.Color;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * ListImageCaptchaEngine with specific default background, font and word generator.
 *
 * @date 16.06.2010
 * @author Titus Kruse
 */
public class CaptchaEngine extends ListImageCaptchaEngine {

	@Override
	protected void buildInitialFactories() {
		WordGenerator wordGenerator = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		TextPaster textPaster = new RandomTextPaster(5, 5, new Color(0xcc, 0xcc, 0xcc));

		BackgroundGenerator backgroundGenerator = new GradientBackgroundGenerator(225, 50,
				new SingleColorGenerator(new Color(0x80, 0x80, 0x80)), new SingleColorGenerator(new Color(0x30, 0x30, 0x30)));

		FontGenerator fontGenerator = new RandomFontGenerator(20, 20);

		WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
		this.addFactory(new GimpyFactory(wordGenerator, wordToImage));
	}

}
