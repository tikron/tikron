/**
 * Copyright (c) 2011 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import de.tikru.commons.faces.util.Message;

/**
 * Basisklasse für Baens, die einen Dateiupload ermöglichen sollen. Die Bean wird normalerweise im Zusammenhang mit dem
 * Apache Tomahawk Tag t:inputFileUpload verwendet.
 * 
 * @link org.apache.myfaces.custom.fileupload.UploadedFile
 * 
 * @since 25.01.2011
 * @author Titus Kruse
 */
public abstract class AbstractFileUploadBean extends BaseBean {

	private static final long serialVersionUID = -2821601905279177321L;

	private UploadedFile uploadedFile;

	private String[] mimeTypes;

	/**
	 * Bestimmt gültige Dateitypen.
	 */
	public AbstractFileUploadBean(String[] mimeTypes) {
		this.mimeTypes = mimeTypes;
	}

	/**
	 * Action-Method "Uplaod". Prüft die Datei und ruft ggf. die Mehtode postProcess() auf.
	 * 
	 * @return Navigation-Case. Es wird die als Return-Point gespeicherte Seite angerufen oder "failed", falls ein Fehler
	 *         aufgetreten ist.
	 */
	public String upload() {
		boolean validFile = false;
		for (int i = 0; i < mimeTypes.length; i++) {
			if (uploadedFile.getContentType().startsWith(mimeTypes[i])) {
				validFile = true;
				break;
			}
		}
		if (!validFile) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_INVALID_MIMETYPE", new Object[] { uploadedFile.getName() });
			return "failed";
		} else {
			return postProcess();
		}
	}

	/**
	 * Verarbeitung, die nach erfolgreichem Upload aufgerufen wird.
	 * 
	 * @return Navigation-Case.
	 */
	protected abstract String postProcess();

	/**
	 * Liefert die hochgeladene Datei.
	 * 
	 * @return Die hochgeladene Daten.
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * Setzt die hochgeladene Datei. Wird durch MyFaces aufgerufen.
	 * 
	 * @param uploadedFile Die hochgeladene Datei.
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String[] getMimeTypes() {
		return mimeTypes;
	}

	public void setMimeTypes(String[] mimeTypes) {
		this.mimeTypes = mimeTypes;
	}

}
