package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;

import com.google.gwt.i18n.shared.DateTimeFormat;

public class ClienteDataUtil {

	static DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yy hh:mm");
	public static final ArrayList<DtoCriticalEvent> ALL_EVENTS = new ArrayList<DtoCriticalEvent>();

	public static HashMap<Long, DtoExtraInfoEvent> EXTRA_INFO = new HashMap<Long, DtoExtraInfoEvent>();

	static {
		ALL_EVENTS.add(new DtoCriticalEvent(1l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(2L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(3L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(4l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(5L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(6L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(7l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(8L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(9L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(11l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(21L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(31L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(11l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(21L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(31L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(11l, Images.INSTANCE.pane22().getSafeUri().asString(), "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(21L, Images.INSTANCE.message22().getSafeUri().asString(), "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim"));
		ALL_EVENTS.add(new DtoCriticalEvent(31L, Images.INSTANCE.alert22().getSafeUri().asString(), "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim"));
	}

	public static ArrayList<DtoCriticalEvent> applyEventCISearch(String... regex) {
		ArrayList<DtoCriticalEvent> ret = new ArrayList<DtoCriticalEvent>();
		for (DtoCriticalEvent dtoEvent : ALL_EVENTS) {
			if (dtoEvent.matchCi(regex)) {
				ret.add(dtoEvent);
			}
		}
		return ret;
	}

	public static ArrayList<DtoCriticalEvent> applyEventSearch(String... regex) {
		ArrayList<DtoCriticalEvent> ret = new ArrayList<DtoCriticalEvent>();
		for (DtoCriticalEvent dtoEvent : ALL_EVENTS) {
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
