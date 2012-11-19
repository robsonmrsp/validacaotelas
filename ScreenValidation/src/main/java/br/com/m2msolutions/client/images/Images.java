package br.com.m2msolutions.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Images extends ClientBundle {

	public static final Images INSTANCE = GWT.create(Images.class);

	@Source("br/com/m2msolutions/client/images/settings128.png")
	ImageResource settings128();
	
	@Source("br/com/m2msolutions/client/images/form128.png")
	ImageResource form128();
	
	@Source("br/com/m2msolutions/client/images/report128.png")
	ImageResource report128();
	
	@Source("br/com/m2msolutions/client/images/alert-book_.png")
	ImageResource alert128();
	
	@Source("br/com/m2msolutions/client/images/calendar.png")
	ImageResource calendar128();
	
	@Source("br/com/m2msolutions/client/images/attendance128.png")
	ImageResource attendance128();
	
	@Source("br/com/m2msolutions/client/images/attendance24.png")
	ImageResource attendance24();
	
	@Source("br/com/m2msolutions/client/images/attendance22.png")
	ImageResource attendance22();

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
	
	@Source("br/com/m2msolutions/client/images/alert22.png")
	ImageResource  alert22();

	@Source("br/com/m2msolutions/client/images/alert16.png")
	ImageResource  alert16();
	
	@Source("br/com/m2msolutions/client/images/message24.png")
	ImageResource  message24();
	
	@Source("br/com/m2msolutions/client/images/message22.png")
	ImageResource  message22();
	
	@Source("br/com/m2msolutions/client/images/message16.png")
	ImageResource  message16();
	
	@Source("br/com/m2msolutions/client/images/pane24.png")
	ImageResource  pane24();
	
	@Source("br/com/m2msolutions/client/images/pane22.png")
	ImageResource  pane22();
	
	@Source("br/com/m2msolutions/client/images/pane16.png")
	ImageResource  pane16();
	
	@Source("br/com/m2msolutions/client/images/map128.png")
	ImageResource  map128();
	
	@Source("br/com/m2msolutions/client/images/index128.png")
	ImageResource  index128();
	
	@Source("br/com/m2msolutions/client/images/printer16.png")
	ImageResource  printer16();
	
	@Source("br/com/m2msolutions/client/images/find16.png")
	ImageResource  find16();
	
	@Source("br/com/m2msolutions/client/images/editor16.png")
	ImageResource  edit16();
	
	@Source("br/com/m2msolutions/client/images/vehicle.png")
	ImageResource  vehicle16();
	
	@Source("br/com/m2msolutions/client/images/driver14.png")
	ImageResource  driver14();
}