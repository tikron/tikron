/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.assembler.gallery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.webapp.model.gallery.BasicCategoryDTO;
import de.tikron.webapp.model.gallery.BasicPictureDTO;
import de.tikron.webapp.model.gallery.ImageDTO;
import de.tikron.webapp.model.gallery.PictureDTO;
import de.tikron.webapp.util.SeoUtils;
import de.tikru.commons.util.FormattedTextCompiler;

/**
 * Component class mapping from/to data transfer objects of type Picture. 
 *
 * @date 02.01.2015
 * @author Titus Kruse
 */
@Component
public class PictureDTOAssembler {
	
	private FormattedTextCompiler textCompiler = FormattedTextCompiler.getInstance();
	
	private SeoUtils seoUtils = SeoUtils.getInstance();
	
	private CategoryDTOAssembler categoryDTOAssembler;
	
	private ImageDTOAssembler imageDTOAssembler;
	
	public PictureDTO toDTO(Picture picture) {
		BasicCategoryDTO category = categoryDTOAssembler.toBasicDTO(picture.getCategory());
		String description = textCompiler.compile(picture.getDescription());
		String seoName = seoUtils.adjustRequestParameterValue(picture.getDisplayName());
		ImageDTO image = imageDTOAssembler.toDTO(picture);
		return new PictureDTO(picture.getId(), category, picture.getName(), picture.getTitle(), description, picture.getImageName(), seoName, image);
	}
	
	public BasicPictureDTO toBasicDTO(Picture picture) {
		BasicCategoryDTO category = categoryDTOAssembler.toBasicDTO(picture.getCategory());
		String seoName = seoUtils.adjustRequestParameterValue(picture.getDisplayName());
		ImageDTO image = imageDTOAssembler.toDTO(picture);
		return new BasicPictureDTO(picture.getId(), category, picture.getName(), picture.getTitle(), picture.getImageName(), seoName, image);
	}
	
	public List<BasicPictureDTO> toBasicDTOList(Collection<Picture> pictures) {
		List<BasicPictureDTO> dtoList = new ArrayList<BasicPictureDTO>(pictures.size());
		for (Picture picture : pictures) {
			dtoList.add(toBasicDTO(picture));
		}
		return dtoList;
	}

	@Autowired
	public void setCategoryDTOAssembler(CategoryDTOAssembler categoryDTOAssembler) {
		this.categoryDTOAssembler = categoryDTOAssembler;
	}

	@Autowired
	public void setImageDTOAssembler(ImageDTOAssembler imageDTOAssembler) {
		this.imageDTOAssembler = imageDTOAssembler;
	}

}
