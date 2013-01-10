package br.com.m2msolutions.client.container;

import java.util.List;

import br.com.m2msolutions.shared.dto.DtoVehicleAndLocation;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SliderEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

//TODO definir os eventos para serem lancados ao precionar os botoes e navegar sobre o slidePAnel.
public class MapNavigationToolBox extends LayoutContainer {
	private LayoutContainer tooboxContainer;
	private LayoutContainer slyderContainer;
	private Slider sliderPositions;

	public static final EventType onClickNext = new EventType();
	public static final EventType onClickPrevious = new EventType();
	public static final EventType onClickLast = new EventType();
	public static final EventType onClickFirst = new EventType();
	public static final EventType onSliderChange = new EventType();

	private Image imageNext;
	private Image imagePrevius;
	private Image imageLast;
	private Image imageFirst;
	private List<DtoVehicleAndLocation> positionsList;
	DtoVehicleAndLocation actualVehicleAndLocation = null;

	public MapNavigationToolBox() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorders(true);
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
			sliderPositions.addListener(Events.Change, new Listener<SliderEvent>() {
				@Override
				public void handleEvent(SliderEvent be) {
					int index = be.getNewValue();
					MapNavigationEvent mapEvent = new MapNavigationEvent(MapNavigationToolBox.this, getExactPosition(index));
					fireEvent(onSliderChange, mapEvent);
				}
			});
		}
		return sliderPositions;
	}

	protected DtoVehicleAndLocation getExactPosition(int index) {
		if (positionsList.isEmpty()) {
			return null;
		}
		if (index > positionsList.size() - 1) {
			return positionsList.get(index - 1);
		}
		return null;
	}

	private Image getImageNext() {
		if (imageNext == null) {
			imageNext = new Image("http://cdn1.iconfinder.com/data/icons/realistiK-new/22x22/actions/player_end.png");
			imageNext.setSize("18px", "18px");
			imageNext.getElement().getStyle().setCursor(Cursor.POINTER);
			imageNext.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					MapNavigationEvent mapEvent = new MapNavigationEvent(MapNavigationToolBox.this, getNextPosition());
					fireEvent(onClickNext, mapEvent);
				}
			});
		}
		return imageNext;
	}

	private Image getImagePrevious() {
		if (imagePrevius == null) {
			imagePrevius = new Image("http://cdn1.iconfinder.com/data/icons/Futurosoft%20Icons%200.5.2/22x22/actions/player_start.png");
			imagePrevius.setSize("18px", "18px");
			imagePrevius.getElement().getStyle().setCursor(Cursor.POINTER);
			imagePrevius.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					MapNavigationEvent mapEvent = new MapNavigationEvent(MapNavigationToolBox.this, getPreviousPosition());
					fireEvent(onClickPrevious, mapEvent);
				}
			});
		}
		return imagePrevius;
	}

	private Image getImageLast() {
		if (imageLast == null) {
			imageLast = new Image("http://cdn1.iconfinder.com/data/icons/realistiK-new/22x22/actions/player_fwd.png");
			imageLast.setSize("18px", "18px");
			imageLast.getElement().getStyle().setCursor(Cursor.POINTER);
			imageLast.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					MapNavigationEvent mapEvent = new MapNavigationEvent(MapNavigationToolBox.this, getLastPosition());
					fireEvent(onClickLast, mapEvent);
				}
			});
		}
		return imageLast;
	}

	private Image getImageFirst() {
		if (imageFirst == null) {
			imageFirst = new Image("http://cdn1.iconfinder.com/data/icons/realistiK-new/22x22/actions/player_rew.png");
			imageFirst.setSize("18px", "18px");
			imageFirst.getElement().getStyle().setCursor(Cursor.POINTER);
			imageFirst.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					MapNavigationEvent mapEvent = new MapNavigationEvent(MapNavigationToolBox.this, getFirstPosition());
					fireEvent(onClickFirst, mapEvent);
				}
			});
		}
		return imageFirst;
	}

	public void setData(List<DtoVehicleAndLocation> positionsList) {
		this.setPositionsList(positionsList);
	}

	// /TODO navegará sobre a lista de vehiclePositions e irá para a proxima
	// posição.
	protected DtoVehicleAndLocation getNextPosition() {
		if (getPositionsList().isEmpty()) {
			return null;
		}
		if (actualVehicleAndLocation == null) {
			actualVehicleAndLocation = getPositionsList().get(0);
		} else {
			int indexOf = getPositionsList().indexOf(actualVehicleAndLocation);
			if (indexOf + 1 < getPositionsList().size())
				actualVehicleAndLocation = getPositionsList().get(indexOf + 1);
		}
		return actualVehicleAndLocation;
	}

	protected DtoVehicleAndLocation getPreviousPosition() {
		if (getPositionsList().isEmpty()) {
			return null;
		}
		if (actualVehicleAndLocation == null) {
			actualVehicleAndLocation = getPositionsList().get(0);
		} else {
			int indexOf = getPositionsList().indexOf(actualVehicleAndLocation);
			if (indexOf - 1 > 0)
				actualVehicleAndLocation = getPositionsList().get(indexOf - 1);
		}
		return actualVehicleAndLocation;
	}

	protected DtoVehicleAndLocation getLastPosition() {
		if (getPositionsList().isEmpty()) {
			return null;
		}
		if (actualVehicleAndLocation == null) {
			actualVehicleAndLocation = getPositionsList().get(0);
		} else {
			actualVehicleAndLocation = getPositionsList().get(getPositionsList().size() - 1);
		}
		return actualVehicleAndLocation;
	}

	protected DtoVehicleAndLocation getFirstPosition() {
		if (getPositionsList().isEmpty()) {
			return null;
		}
		if (actualVehicleAndLocation == null) {
			actualVehicleAndLocation = getPositionsList().get(0);
		} else {
			int indexOf = getPositionsList().indexOf(actualVehicleAndLocation);
			if (indexOf + 1 < getPositionsList().size())
				actualVehicleAndLocation = getPositionsList().get(0);
		}
		return actualVehicleAndLocation;
	}

	public List<DtoVehicleAndLocation> getPositionsList() {
		return positionsList;
	}

	public void setPositionsList(List<DtoVehicleAndLocation> positionsList) {
		this.positionsList = positionsList;
	}

	public void addClickNextListener(Listener<MapNavigationEvent> listener) {
		addListener(onClickNext, listener);
	}

	public void addClickPreviousListener(Listener<MapNavigationEvent> listener) {
		addListener(onClickPrevious, listener);
	}

	public void addClickLastListener(Listener<MapNavigationEvent> listener) {
		addListener(onClickLast, listener);
	}

	public void addClickFirstListener(Listener<MapNavigationEvent> listener) {
		addListener(onClickFirst, listener);
	}

	public void addSlideChangeListener(Listener<MapNavigationEvent> listener) {
		addListener(onSliderChange, listener);
	}
}
