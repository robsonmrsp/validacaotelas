package br.com.mr.storageposition.client;

import br.com.mr.storageposition.shared.UserInfoPosition;
import de.novanic.eventservice.client.event.Event;

public class UserInfoPositionEvent implements Event {
	private UserInfoPosition userInfoPosition;

	public UserInfoPositionEvent() {
	}

	public UserInfoPositionEvent(UserInfoPosition userInfoPosition) {
		super();
		this.userInfoPosition = userInfoPosition;
	}

	public UserInfoPosition getUserInfoPosition() {
		return userInfoPosition;
	}

	public void setUserInfoPosition(UserInfoPosition userInfoPosition) {
		this.userInfoPosition = userInfoPosition;
	}
}
