package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.ui.Image;

//TODO definir os eventos para serem lancados ao precionar os botoes e navegar sobre o slidePAnel.
public class MapNavigationToolBox extends LayoutContainer {
	private LayoutContainer tooboxContainer;
	private LayoutContainer slyderContainer;
	private Slider sliderPositions;

	private Image imageNext;
	private Image imagePrevius;
	private Image imageLast;
	private Image imageFirst;

	public MapNavigationToolBox() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorders(false);
		setSize("316", "23");
		add(getTooboxContainer(), new BorderLayoutData(LayoutRegion.WEST, 80.0f));
		add(getSlyderContainer(), new BorderLayoutData(LayoutRegion.CENTER));
	}

	private LayoutContainer getTooboxContainer() {
		if (tooboxContainer == null) {
			tooboxContainer = new LayoutContainer();
			tooboxContainer.setSize("85", "22");
			tooboxContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
			tooboxContainer.setBorders(false);
			tooboxContainer.add(getImageFirst());
			tooboxContainer.add(getImagePrevious());
			tooboxContainer.add(getImageNext());
			tooboxContainer.add(getImageLast());
		}
		return tooboxContainer;
	}

	private LayoutContainer getSlyderContainer() {
		if (slyderContainer == null) {
			slyderContainer = new LayoutContainer();
			slyderContainer.setBorders(false);
			slyderContainer.add(getSliderPositions());
		}
		return slyderContainer;
	}

	private Slider getSliderPositions() {
		if (sliderPositions == null) {
			sliderPositions = new Slider();
			// TODO definido pela quantidade de metricas durante a execução do
			// evento
			sliderPositions.setIncrement(1);
			// TODO definido pela quantidade de metricas durante a execução do
			// evento
			sliderPositions.setMaxValue(2000);
		}
		return sliderPositions;
	}

	private Image getImageNext() {
		if (imageNext == null) {
			imageNext = new Image("http://cdn1.iconfinder.com/data/icons/realistiK-new/22x22/actions/player_end.png");
			imageNext.setSize("18px", "18px");
		}
		return imageNext;
	}

	private Image getImagePrevious() {
		if (imagePrevius == null) {
			imagePrevius = new Image("http://cdn1.iconfinder.com/data/icons/Futurosoft%20Icons%200.5.2/22x22/actions/player_start.png");
			imagePrevius.setSize("18px", "18px");
		}
		return imagePrevius;
	}

	private Image getImageLast() {
		if (imageLast == null) {
			imageLast = new Image("http://cdn1.iconfinder.com/data/icons/realistiK-new/22x22/actions/player_fwd.png");
			imageLast.setSize("18px", "18px");
		}
		return imageLast;
	}

	private Image getImageFirst() {
		if (imageFirst == null) {
			imageFirst = new Image("http://cdn1.iconfinder.com/data/icons/realistiK-new/22x22/actions/player_rew.png");
			imageFirst.setSize("18px", "18px");
		}
		return imageFirst;
	}

}
