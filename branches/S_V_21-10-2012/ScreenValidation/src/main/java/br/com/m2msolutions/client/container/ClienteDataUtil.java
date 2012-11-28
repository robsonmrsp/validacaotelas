package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;

import com.google.gwt.i18n.shared.DateTimeFormat;

public class ClienteDataUtil {

	static DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yy hh:mm");
	public static final ArrayList<DtoEvent> ALL_EVENTS = new ArrayList<DtoEvent>();

	public static HashMap<Long, DtoExtraInfoEvent> EXTRA_INFO = new HashMap<Long, DtoExtraInfoEvent>();

	static {
		ALL_EVENTS.add(new DtoEvent(1l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoEvent(2L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoEvent(3L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
	}

	public static ArrayList<DtoEvent> applyEventCISearch(String... regex) {
		ArrayList<DtoEvent> ret = new ArrayList<DtoEvent>();
		for (DtoEvent dtoEvent : ALL_EVENTS) {
			if (dtoEvent.matchCi(regex)) {
				ret.add(dtoEvent);
			}
		}
		return ret;
	}

	public static ArrayList<DtoEvent> applyEventSearch(String... regex) {
		ArrayList<DtoEvent> ret = new ArrayList<DtoEvent>();
		for (DtoEvent dtoEvent : ALL_EVENTS) {
			if (dtoEvent.match(regex)) {
				ret.add(dtoEvent);
			}
		}
		return ret;
	}

	public static String format(Date startTime) {
		return dateTimeFormat.format(startTime);
	}
}
