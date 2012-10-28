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
	
	@Source("br/com/m2msolutions/client/images/attendance128.png")
	ImageResource attendance128();
	
	@Source("br/com/m2msolutions/client/images/attendance24.png")
	ImageResource attendance24();

	@Source("br/com/m2msolutions/client/images/link.png")
	ImageResource link128();

	@Source("br/com/m2msolutions/client/images/rss.png")
	ImageResource rss128();

	@Source("br/com/m2msolutions/client/images/contract16.png")
	ImageResource contract16();

	@Source("br/com/m2msolutions/client/images/expand16.png")
	ImageResource expand16();

	@Source("br/com/m2msolutions/client/images/interrogation18.png")
	ImageResource  interrogation18();
	
	@Source("br/com/m2msolutions/client/images/alert24.png")
	ImageResource  alert24();
	
	@Source("br/com/m2msolutions/client/images/message24.png")
	ImageResource  message24();
	
	@Source("br/com/m2msolutions/client/images/pane24.png")
	ImageResource  pane24();
	
}