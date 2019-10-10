/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

//import java.io.IOException;
//import java.io.InputStream;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

//import org.apache.log4j.Logger;

//import de.tikru.commons.faces.util.Message;
//import de.tikron.manager.service.music.TrackService;
//import de.tikron.manager.service.music.TrackServiceJPAImpl.PlaylistException;
//import de.tikron.persistence.model.reviews.Volume;

/**
 * Backing Bean zum Hochladen einer M3U-Playlist. Die Playlist wird in Titeln gespeichert und diese einem Tonträger
 * hinzugefügt.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class PlaylistUploadBean extends AbstractFileUploadBean {

//	private static Logger log = LogManager.getLogger();

//	@ManagedProperty(value = "#{trackService}")
//	private TrackService trackService;

//	private Volume volume;

	private static final long serialVersionUID = -4785639397303799949L;
	private boolean compilation;

	public PlaylistUploadBean() {
		super(new String[] { "audio/x-mpegurl" });
	}

	public void preRenderView(ComponentSystemEvent e) {
	}

	/**
	 * Verarbeitet die Playlist.
	 * 
	 * @return Navigation-Case.
	 */
	@Override
	public String postProcess() {
		/*
		try {
			if (getUploadedFile() != null) {
				InputStream is = getUploadedFile().getInputStream();
				trackService.saveFromPlaylist(is, getVolume(), isCompilation());
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			Message.sendMessage(null, "de.tikron.manager.ERROR_ON_UPLOAD", null);
		} catch (PlaylistException e) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_ON_UPLOAD", null);
		}
		*/
		return "/pages/common/confirmation.xhtml";
	}

//	public Volume getVolume() {
//		return volume;
//	}

//	public void setVolume(Volume volume) {
//		this.volume = volume;
//	}

	public boolean isCompilation() {
		return compilation;
	}

	public void setCompilation(boolean compilation) {
		this.compilation = compilation;
	}

//	public void setTrackService(TrackService trackService) {
//		this.trackService = trackService;
//	}

}
