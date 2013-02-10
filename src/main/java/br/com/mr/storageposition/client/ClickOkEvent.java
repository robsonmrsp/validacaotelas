package br.com.mr.storageposition.client;

import com.google.gwt.event.shared.GwtEvent;

public class ClickOkEvent extends GwtEvent<ClickOkHandler> {

	private static Type<ClickOkHandler> TYPE = new Type<ClickOkHandler>();

	private final UserInfo selectedUser;
	
	public static Type<ClickOkHandler> getType() {
		return TYPE;
	}


	public ClickOkEvent(UserInfo selectedUser) {
		this.selectedUser = selectedUser;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClickOkHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClickOkHandler handler) {
		handler.onClickOk(this);
	}

	public UserInfo getSelectedUser() {
		return selectedUser;
	}

}
