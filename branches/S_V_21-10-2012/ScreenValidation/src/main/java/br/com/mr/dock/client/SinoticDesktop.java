package br.com.mr.dock.client;

import java.util.Date;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.core.DomQuery;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;

public class SinoticDesktop extends LayoutContainer {
	private LayoutContainer sinoticTopContainer;
	private LayoutContainer principalContainer;
	private LayoutContainer sinoticBottomContainer;
	private SinoticTaskBar sinoticTaskBar;
	private LayoutContainer clockContainer;
	private Html clock;

	public SinoticDesktop() {
		initComponents();
	}

	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		setSize(width, height);
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setStyleName("sinotic-desktop");
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				setSize(Window.getClientWidth(), Window.getClientHeight());
			}
		});
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				// setSize(event.getWidth(), event.getHeight());
				setSize(Window.getClientWidth(), Window.getClientHeight());
			}
		});
		getPrincipalContainer().setLayout(new BorderLayout());

		add(getPrincipalContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		getSinoticTopContainer().setLayout(new BorderLayout());
		add(getSinoticTopContainer(), new BorderLayoutData(LayoutRegion.NORTH, 46.0f));
		add(getSinoticBottomContainer(), new BorderLayoutData(LayoutRegion.SOUTH, 32));

	}

	private LayoutContainer getPrincipalContainer() {
		if (principalContainer == null) {
			principalContainer = new LayoutContainer() {
				
				@Override
				protected void onRender(Element parent, int index) {
					super.onRender(parent, index);
					Element el = DomQuery.selectNode("#sinotic-desktop");
					if (el == null) {
						el = DOM.createDiv();
					}
					getElement().appendChild(el);
				}
			};

			principalContainer.setBorders(false);
			principalContainer.setStyleName("sinotic-desktop");
			
			principalContainer.add(getLeftContainer(), new BorderLayoutData(LayoutRegion.WEST, 55.0f));
			principalContainer.add(getRigthContainer(), new BorderLayoutData(LayoutRegion.EAST, 55.0f));
			principalContainer.add(getCenterContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		}
		return principalContainer;
	}

	private LayoutContainer getSinoticTopContainer() {
		if (sinoticTopContainer == null) {
			sinoticTopContainer = new LayoutContainer();
			sinoticTopContainer.setSize("-1", "46");
			sinoticTopContainer.setId("sinoticTopContainer");
			sinoticTopContainer.setStyleName("sinotic-top-container");
			sinoticTopContainer.setBorders(false);
			BorderLayoutData bld_logoContainer = new BorderLayoutData(LayoutRegion.EAST, 120.0f);
			bld_logoContainer.setMinSize(120);
			bld_logoContainer.setMaxSize(120);
			sinoticTopContainer.add(getLogoContainer(), bld_logoContainer);
			sinoticTopContainer.add(getLayoutContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		}
		return sinoticTopContainer;
	}

	private LayoutContainer getSinoticBottomContainer() {
		if (sinoticBottomContainer == null) {
			sinoticBottomContainer = new LayoutContainer();
			sinoticBottomContainer.setId("sinoticBottomContainer");
			sinoticBottomContainer.setStyleName("sinotic-botton-container");
			sinoticBottomContainer.setBorders(false);
			sinoticBottomContainer.setLayout(new BorderLayout());
			getBottomMenu().setLayout(new RowLayout(Orientation.HORIZONTAL));
			sinoticBottomContainer.add(getBottomMenu(), new BorderLayoutData(LayoutRegion.CENTER));
			getClockContainer().setLayout(new CenterLayout());
			BorderLayoutData bld_clockContainer = new BorderLayoutData(LayoutRegion.EAST, 120.0f);
			bld_clockContainer.setMaxSize(120);
			sinoticBottomContainer.add(getClockContainer(), bld_clockContainer);
		}
		return sinoticBottomContainer;
	}

	public LayoutContainer getBottomMenu() {
		if (sinoticTaskBar == null) {
			sinoticTaskBar = new SinoticTaskBar();
			sinoticTaskBar.setSize("-1", "32");
			sinoticTaskBar.setId("sinoticTaskBar");
			sinoticTaskBar.setStyleName("sinotic-task-bar");
		}
		return sinoticTaskBar;
	}

	public void addTaskButton(Component component) {
		sinoticTaskBar.add(component);
	}

	public void addWindow(SinoticWindow window) {
		window.hide();
		Draggable dragger = new Draggable(window, window.getHeader());
		dragger.setContainer(this);
		dragger.setUseProxy(false);
//		principalContainer.add(window);
	}

	private LayoutContainer getClockContainer() {
		if (clockContainer == null) {
			clockContainer = new LayoutContainer();
			clockContainer.setStyleName("sinotic-clock");
			clockContainer.setBorders(false);
			clockContainer.add(getClock());
		}
		return clockContainer;
	}

	private Html getClock() {
		if (clock == null) {
			clock = new Html("12:05");
			timer.scheduleRepeating(1000);
		}
		return clock;
	}

	Timer timer = new Timer() {

		@Override
		public void run() {
			clock.setHtml(update(new Date()));
		}
	};
	private LayoutContainer logoContainer;
	private LayoutContainer layoutContainer;
	private Button btnNewButton;
	private Grid gridShortCuts;
	private LayoutContainer leftContainer;
	private LayoutContainer rigthContainer;
	private LayoutContainer centerContainer;
	private LayoutContainer layoutContainer_4;
	private Image image;
	private Image image_1;
	private Image image_2;
	private Image image_3;
	private Image image_4;
	private Image image_5;
	private Image image_6;
	private Image image_7;
	private Image image_8;

	public String update(final Date date) {
		// extract hours/minutes/seconds from the date
		final DateTimeFormat secondFormat = DateTimeFormat.getFormat("ss");
		final DateTimeFormat minuteFormat = DateTimeFormat.getFormat("mm");
		final DateTimeFormat hourFormat = DateTimeFormat.getFormat("hh");

		final String seconds = secondFormat.format(date);
		String minutes = minuteFormat.format(date);
		String hours = hourFormat.format(date);

		return hours + ":" + minutes + ":" + seconds;
	}

	private LayoutContainer getLogoContainer() {
		if (logoContainer == null) {
			logoContainer = new LayoutContainer();
			logoContainer.setStyleName("logo");
			logoContainer.setSize("120", "46");
			logoContainer.setBorders(false);
		}
		return logoContainer;
	}

	private LayoutContainer getLayoutContainer() {
		if (layoutContainer == null) {
			layoutContainer = new LayoutContainer();
			layoutContainer.setSize("500", "46");
			layoutContainer.setBorders(false);
		}
		return layoutContainer;
	}

	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("New Button");
		}
		return btnNewButton;
	}

	private Grid getGridShortCuts() {
		if (gridShortCuts == null) {
			gridShortCuts = new Grid(2, 4);
			gridShortCuts.setSize("507px", "259px");
			gridShortCuts.setWidget(0, 0, getImage_1());
			gridShortCuts.setWidget(0, 1, getImage_2());
			gridShortCuts.setWidget(0, 2, getImage_3());
			gridShortCuts.setWidget(0, 3, getImage_4());
			gridShortCuts.setWidget(1, 0, getImage_5());
			gridShortCuts.setWidget(1, 1, getImage_6());
			gridShortCuts.setWidget(1, 2, getImage_7());
			gridShortCuts.setWidget(1, 3, getImage_8());
			gridShortCuts.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(1, 3, HasVerticalAlignment.ALIGN_MIDDLE);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(1, 3, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);
			gridShortCuts.getCellFormatter().setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_MIDDLE);
		}
		return gridShortCuts;
	}

	private LayoutContainer getLeftContainer() {
		if (leftContainer == null) {
			leftContainer = new LayoutContainer();
			leftContainer.setBorders(false);
		}
		return leftContainer;
	}

	private LayoutContainer getRigthContainer() {
		if (rigthContainer == null) {
			rigthContainer = new LayoutContainer();
			rigthContainer.setBorders(false);
		}
		return rigthContainer;
	}

	private LayoutContainer getCenterContainer() {
		if (centerContainer == null) {
			centerContainer = new LayoutContainer();
			centerContainer.setBorders(false);
			centerContainer.setLayout(new CenterLayout());
//			centerContainer.add(getGridShortCuts());
		}
		return centerContainer;
	}

	public void addShortCut(Component component) {
	}

	private Image getImage_1() {
		if (image_1 == null) {
			image_1 = new Image("http://cdn1.iconfinder.com/data/icons/oxygen/64x64/apps/kchart.png");
		}
		return image_1;
	}
	private Image getImage_2() {
		if (image_2 == null) {
			image_2 = new Image("http://cdn1.iconfinder.com/data/icons/IMPRESSIONS/business/png/64/pie_chart.png");
		}
		return image_2;
	}
	private Image getImage_3() {
		if (image_3 == null) {
			image_3 = new Image("http://cdn1.iconfinder.com/data/icons/crystalproject/64x64/filesystems/Globe2.png");
		}
		return image_3;
	}
	private Image getImage_4() {
		if (image_4 == null) {
			image_4 = new Image("http://cdn1.iconfinder.com/data/icons/oxygen/64x64/apps/kchart.png");
		}
		return image_4;
	}
	private Image getImage_5() {
		if (image_5 == null) {
			image_5 = new Image("http://cdn1.iconfinder.com/data/icons/IMPRESSIONS/business/png/64/pie_chart.png");
		}
		return image_5;
	}
	private Image getImage_6() {
		if (image_6 == null) {
			image_6 = new Image("http://cdn1.iconfinder.com/data/icons/crystalproject/64x64/filesystems/Globe2.png");
		}
		return image_6;
	}
	private Image getImage_7() {
		if (image_7 == null) {
			image_7 = new Image("http://cdn1.iconfinder.com/data/icons/oxygen/64x64/apps/kchart.png");
		}
		return image_7;
	}
	private Image getImage_8() {
		if (image_8 == null) {
			image_8 = new Image("http://cdn1.iconfinder.com/data/icons/IMPRESSIONS/business/png/64/pie_chart.png");
		}
		return image_8;
	}
}
