package br.com.m2msolutions.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Images extends ClientBundle {

	public static final Images INSTANCE = GWT.create(Images.class);

	@Source("br/com/m2msolutions/client/images/alert-book_.png")
	ImageResource alert128();
	
	@Source("br/com/m2msolutions/client/images/calendar.png")
	ImageResource calendar128();

	@Source("br/com/m2msolutions/client/images/link.png")
	ImageResource link128();

	@Source("br/com/m2msolutions/client/images/rss.png")
	ImageResource rss128();
}