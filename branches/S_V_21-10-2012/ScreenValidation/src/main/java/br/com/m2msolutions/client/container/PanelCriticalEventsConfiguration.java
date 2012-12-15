package br.com.m2msolutions.client.container;

import java.util.ArrayList;

import br.com.m2msolutions.client.CriticalEventsConfigurationService;
import br.com.m2msolutions.client.CriticalEventsConfigurationServiceAsync;
import br.com.m2msolutions.client.DtoEventType;
import br.com.m2msolutions.client.SimpleGwtLogger;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PanelCriticalEventsConfiguration extends FormPanel {
	private ListField<DtoCategory> listCategory;
	private CheckBoxListView<DtoEventType> listCriticalEvents;
	private LabelField labelCategory;
	private LabelField labelCriticalEvents;
	private SearchBox searchBox;
	private Html htmlNotice;
	private CheckBoxListView listSortCriticalEvents;
	private Button buttonOk;
	private Button buttonCancel;

	CriticalEventsConfigurationServiceAsync eventsConfigurationService = CriticalEventsConfigurationService.Util.getInstance();

	public PanelCriticalEventsConfiguration() {
		initComponents();
	}

	private void initComponents() {
		setHeaderVisible(false);
		setLayout(new AbsoluteLayout());
		add(getListCategory(), new AbsoluteData(6, 37));
		add(getListCriticalEvents(), new AbsoluteData(161, 39));
		add(getLabelCategory(), new AbsoluteData(9, 11));
		add(getLabelCriticalEvents(), new AbsoluteData(159, 11));

		add(getSearchBox(), new AbsoluteData(295, 11));
		add(getHtmlNotice(), new AbsoluteData(7, 204));
		add(getListSortCriticalEvents(), new AbsoluteData(6, 243));
		addButton(getButtonCancel());
		addButton(getButtonOk());
	}

	private ListField<DtoCategory> getListCategory() {
		if (listCategory == null) {
			listCategory = new ListField<DtoCategory>();
			listCategory.setSize("131px", "148px");
			listCategory.setFieldLabel("New ListField");
			listCategory.addSelectionChangedListener(new SelectionChangedListener<DtoCategory>() {
				@Override
				public void selectionChanged(SelectionChangedEvent<DtoCategory> event) {
					DtoCategory category = event.getSelectedItem();
					if (category != null) {
						populateListCriticalEvents(category);
					}
				}
			});
		}
		return listCategory;
	}

	protected void populateListCriticalEvents(DtoCategory category) {
		eventsConfigurationService.getCriticalEventsByCategory(category, new AsyncCallback<ArrayList<DtoEventType>>() {
			@Override
			public void onFailure(Throwable caught) {
				SimpleGwtLogger.error("Get CriticalEventsByCategory Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<DtoEventType> criticalEvents) {
				updateListCriticalEventsContent(criticalEvents);
			}
		});
	}

	protected void updateListCriticalEventsContent(ArrayList<DtoEventType> criticalEvents) {
		listCriticalEvents.getStore().removeAll();
		listCriticalEvents.getStore().add(criticalEvents);
	}

	private CheckBoxListView<DtoEventType> getListCriticalEvents() {
		if (listCriticalEvents == null) {
			listCriticalEvents = new CheckBoxListView<DtoEventType>();
			listCriticalEvents.setSize("238px", "148px");
		}
		return listCriticalEvents;
	}

	private LabelField getLabelCategory() {
		if (labelCategory == null) {
			labelCategory = new LabelField("Categoria");
			labelCategory.setSize("54px", "15px");
		}
		return labelCategory;
	}

	private LabelField getLabelCriticalEvents() {
		if (labelCriticalEvents == null) {
			labelCriticalEvents = new LabelField("Eventos");
			labelCriticalEvents.setSize("44px", "15px");
		}
		return labelCriticalEvents;
	}

	private SearchBox getSearchBox() {
		if (searchBox == null) {
			searchBox = new SearchBox();
			searchBox.setSize("65px", "26px");
			// searchBox.setSize("120px", "26px");
			// searchBox.setSize("189px", "26px");
			searchBox.setSize(65, 26);
			searchBox.setChangeListener(new Listener<SearchBoxEvent>() {
				@Override
				public void handleEvent(SearchBoxEvent be) {
					runSearch(be.getInputValue());
				}
			});
		}
		return searchBox;
	}

	// TODO segundo a especificação: conforme o operador digita um nome de um
	// evento o filtro será aplicado a lista de eventos.
	protected void runSearch(String inputValue) {

	}

	private Html getHtmlNotice() {
		if (htmlNotice == null) {
			htmlNotice = new Html("As seguintes caracteristicas serão utilizadas na ordenação dos eventos no Widget de Eventos Criticos");
			htmlNotice.setSize("394px", "33px");
		}
		return htmlNotice;
	}

	private CheckBoxListView getListSortCriticalEvents() {
		if (listSortCriticalEvents == null) {
			listSortCriticalEvents = new CheckBoxListView();
			listSortCriticalEvents.setSize("393px", "146px");
		}
		return listSortCriticalEvents;
	}

	private Button getButtonOk() {
		if (buttonOk == null) {
			buttonOk = new Button("Ok");
			buttonOk.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// TODO deverá tambem alterar o estado da tela widget de
					// eventos criticos. Descobrir a melhor maneira de fazer
					saveConfiguration();
				}
			});
		}
		return buttonOk;
	}

	protected void saveConfiguration() {
		eventsConfigurationService.saveConfiguration(createConfiguration(), new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Boolean result) {

			}
		});
	}

	// TODO a partir do que está sendo exibido na tela será criado um Objeto
	// contento todas suas informações.
	private DtoCriticalEventsConfiguration createConfiguration() {
		return null;
	}

	private Button getButtonCancel() {
		if (buttonCancel == null) {
			buttonCancel = new Button("Cancelar");
			buttonCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {

				}
			});
		}
		return buttonCancel;
	}
}
