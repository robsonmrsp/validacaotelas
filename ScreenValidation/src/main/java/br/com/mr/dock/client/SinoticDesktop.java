package br.com.mr.dock.client;

import java.util.Date;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.core.DomQuery;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

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

	public void addWindow(DockWindow window) {
		Draggable dragger = new Draggable(window, window.getHeader());
		dragger.setContainer(this);
		dragger.setUseProxy(false);
		principalContainer.add(window);
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
}
