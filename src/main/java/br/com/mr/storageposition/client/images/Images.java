package br.com.mr.storageposition.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Images extends ClientBundle {

	public static final Images INSTANCE = GWT.create(Images.class);

	@Source("br/com/mr/storageposition/client/images/findme.png")
	ImageResource findme();
}