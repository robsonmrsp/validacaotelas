package br.com.m2msolutions.client.sinotic.canvas;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;


public class SinoticCanvas extends LayoutContainer{

	private Canvas canvas;
	private Context2d context2d;
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;

	private Timer timer;
	
	private ImageElement imgElement;
	 
	public SinoticCanvas(){

		canvas = Canvas.createIfSupported();
		
		setLayout(new FitLayout());
		
		if(canvas != null){
			
//			canvas.setWidth(Window.getClientWidth() + "px");
//			canvas.setHeight(Window.getClientHeight() + "px");
			
//			canvas.setWidth("725px");
//			canvas.setHeight("172px");
			canvas.setCoordinateSpaceWidth(WIDTH);
			canvas.setCoordinateSpaceHeight(HEIGHT);
			add(canvas);
			
			context2d = canvas.getContext2d();
			
			ImageResource imageResource = Images.INSTANCE.imagePoint();
			
			SafeUri uri= imageResource.getSafeUri();        
		    imgElement= ImageElement.as((new Image(uri)).getElement());
			
			timer = new Timer() {
				@Override
				public void run() {
					update();
				}
			};
			timer.scheduleRepeating(33);
		}

	}
	
	public void update(){
		draw();
	}

	public void draw() {
		
//		CssColor cor = CssColor.make("rgb(50,25,255)");
		double heightPath = 60;
		double xBeginningPath = 0;
		double yBeginningPath1 = (HEIGHT/2)-heightPath;
		double yBeginningPath2 = HEIGHT-heightPath;
		
		CanvasGradient canvasGradient1 = context2d.createLinearGradient(xBeginningPath, yBeginningPath1, xBeginningPath, yBeginningPath1+heightPath);
		canvasGradient1.addColorStop(0, "rgb(159,198,96)");
		canvasGradient1.addColorStop(0.5, "rgb(117,143,67)");
		canvasGradient1.addColorStop(0.5, "rgb(117,143,67)");
		context2d.setFillStyle(canvasGradient1);
		
		context2d.fillRect(xBeginningPath, yBeginningPath1, WIDTH, heightPath);
		
		
		CanvasGradient canvasGradient2 = context2d.createLinearGradient(xBeginningPath, yBeginningPath2, xBeginningPath, yBeginningPath2+heightPath);
		canvasGradient2.addColorStop(0, "rgb(159,198,96)");
		canvasGradient2.addColorStop(0.5, "rgb(117,143,67)");
		canvasGradient2.addColorStop(0.5, "rgb(117,143,67)");
		context2d.setFillStyle(canvasGradient2);
		
		context2d.fillRect(xBeginningPath, yBeginningPath2, WIDTH, heightPath);
		
		context2d.drawImage(imgElement, 300, 150);
	}

}
