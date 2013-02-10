package br.com.mr.storageposition.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class WhoAreYouPanel extends DialogBox {
	private FlexTable flexTable;
	private Button btnNewButton;
	private Image image;
	private CellList<UserInfo> cellList;
	private ListDataProvider<UserInfo> usersDataProvider;
	private ScrollPanel scrollPanel;
	UserInfo selectedUser = null;

	public WhoAreYouPanel() {
		super(false);
		setHTML("Quem é você");
		initComponents();
	}

	private void initComponents() {
		setWidget(getFlexTable());
	}

	private FlexTable getFlexTable() {
		if (flexTable == null) {
			flexTable = new FlexTable();
			flexTable.setSize("236px", "218px");
			flexTable.setWidget(0, 0, getImage());
			flexTable.setWidget(1, 0, getScrollPanel());
			flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			flexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
			flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
			flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			flexTable.setWidget(2, 0, getBtnNewButton());
		}
		return flexTable;
	}

	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("New button");
			btnNewButton.setText("Ok");
			btnNewButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if(selectedUser != null){
						fireEvent(new ClickOkEvent(selectedUser));
//						hide();
					}
				}
			});
		}
		return btnNewButton;
	}

	public void addClickOkHandler(ClickOkHandler areYouHandler){
		addHandler(areYouHandler, ClickOkEvent.getType());
	}
	
	private Image getImage() {
		if (image == null) {
			image = new Image("http://cdn1.iconfinder.com/data/icons/humano2/48x48/actions/user-group-new.png");
		}
		return image;
	}

	private CellList<UserInfo> getCellList() {
		if (cellList == null) {
			cellList = new CellList<UserInfo>(new AbstractCell<UserInfo>() {
				@Override
				public void render(Context context, UserInfo value, SafeHtmlBuilder sb) {
					if (value == null) {
						return;
					}
					sb.appendHtmlConstant("<table>");

					// Add the contact image.
					sb.appendHtmlConstant("<tr><td rowspan='3'>");
					sb.appendHtmlConstant("<img src='" + value.getImageSrc() + "' height='32' width='32'>");
					sb.appendHtmlConstant("</td>");

					// Add the name and address.
					sb.appendHtmlConstant("<td style='font-size:95%;'>");
					sb.appendEscaped(value.getName());
					sb.appendHtmlConstant("</td></tr><tr><td>");
					sb.appendEscaped(value.getDeviceId());
					sb.appendHtmlConstant("</td></tr></table>");
				}
			});
			final SingleSelectionModel<UserInfo> selectionModel = new SingleSelectionModel<UserInfo>();
			cellList.setSelectionModel(selectionModel);

			selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				public void onSelectionChange(SelectionChangeEvent event) {
					selectedUser = selectionModel.getSelectedObject();
				}
			});
			cellList.setSize("100%", "76px");
			usersDataProvider = new ListDataProvider<UserInfo>(getUsers());
			usersDataProvider.addDataDisplay(cellList);
			cellList.setPageSize(10);
		}
		return cellList;
	}

	private List<UserInfo> getUsers() {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		UserInfo userInfo1 = new UserInfo("Marcio Robson", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Male_Dark.png", "MOTOROLA_01");
		UserInfo userInfo2 = new UserInfo("Marta Mirian", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Female_Light.png", "SAMSUNG_01");
		UserInfo userInfo3 = new UserInfo("Awdryn Cavalache", "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "SONY_01");
		UserInfo userInfo4 = new UserInfo("Marcio Robson", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Male_Dark.png", "MOTOROLA_01");
		UserInfo userInfo5 = new UserInfo("Marta Mirian", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Female_Light.png", "SAMSUNG_01");
		UserInfo userInfo6 = new UserInfo("Awdryn Cavalache", "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "SONY_01");
		UserInfo userInfo7 = new UserInfo("Marcio Robson", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Male_Dark.png", "MOTOROLA_01");
		UserInfo userInfo8 = new UserInfo("Marta Mirian", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Female_Light.png", "SAMSUNG_01");
		UserInfo userInfo9 = new UserInfo("Awdryn Cavalache", "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "SONY_01");
		UserInfo userInfo10 = new UserInfo("Marcio Robson", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Male_Dark.png", "MOTOROLA_01");
		UserInfo userInfo11 = new UserInfo("Marta Mirian", "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Female_Light.png", "SAMSUNG_01");
		UserInfo userInfo12 = new UserInfo("Awdryn Cavalache", "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "SONY_01");

		userInfos.add(userInfo1);
		userInfos.add(userInfo2);
		userInfos.add(userInfo3);
		userInfos.add(userInfo4);
		userInfos.add(userInfo5);
		userInfos.add(userInfo6);
		userInfos.add(userInfo7);
		userInfos.add(userInfo8);
		userInfos.add(userInfo9);
		userInfos.add(userInfo10);
		userInfos.add(userInfo11);
		userInfos.add(userInfo12);
		return userInfos;
	}

	private ScrollPanel getScrollPanel() {
		if (scrollPanel == null) {
			scrollPanel = new ScrollPanel();
			scrollPanel.setHeight("77px");
			scrollPanel.setWidget(getCellList());
		}
		return scrollPanel;
	}
}
