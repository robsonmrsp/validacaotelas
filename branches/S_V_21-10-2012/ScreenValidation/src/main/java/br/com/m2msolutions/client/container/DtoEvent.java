package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoEvent extends BaseModelData {
	private static final long serialVersionUID = 6308342747740478807L;

	public static final String IMAGE_SRC = "imageSrc";
	public static final String DESCRIPTION = "description";

	public DtoEvent(String src, String desc) {
		setImageSrc(src);
		setDescription(desc);
	}

	public String getImageSrc() {
		return get(IMAGE_SRC);
	}

	public void setImageSrc(String imageSrc) {
		set(IMAGE_SRC, imageSrc);
	}

	public String getDescription() {
		return get(DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DESCRIPTION, description);
	}
}
