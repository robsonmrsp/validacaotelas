package br.com.m2msolutions.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.m2msolutions.shared.dto.DtoAboutEvent;
import br.com.m2msolutions.shared.dto.DtoContact;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoRecord;
import br.com.m2msolutions.shared.dto.DtoVehicleAndLocation;

public class UtilData {

	static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
	public static final ArrayList<DtoCriticalEvent> ALL_EVENTS = new ArrayList<DtoCriticalEvent>();

	public static HashMap<Long, DtoExtraInfoEvent> EXTRA_INFO = new HashMap<Long, DtoExtraInfoEvent>();

	static {
		DtoCriticalEvent eventOne = new DtoCriticalEvent(1l, "", "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventOne.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventOne);
		DtoCriticalEvent eventTwo = new DtoCriticalEvent(2L, "", "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTwo.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTwo);
		DtoCriticalEvent eventTree = new DtoCriticalEvent(3L, "", "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTree.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTree);
		DtoCriticalEvent eventOne1 = new DtoCriticalEvent(1l, "", "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventOne1.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventOne1);
		DtoCriticalEvent eventTwo2 = new DtoCriticalEvent(2L, "", "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTwo2.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTwo2);
		DtoCriticalEvent eventTree3 = new DtoCriticalEvent(3L, "", "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTree3.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTree3);
		DtoCriticalEvent eventOne4 = new DtoCriticalEvent(1l, "", "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventOne4.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventOne4);
		DtoCriticalEvent eventTwo5 = new DtoCriticalEvent(2L, "", "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTwo5.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTwo5);
		DtoCriticalEvent eventTree6 = new DtoCriticalEvent(3L, "", "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTree6.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTree6);
		DtoCriticalEvent eventOne7 = new DtoCriticalEvent(1l, "", "113498", new Date(), "PANE", "CCO - 022", "280901", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventOne7.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventOne7);
		DtoCriticalEvent eventTwo8 = new DtoCriticalEvent(2L, "", "172723", new Date(), "MESSAGE", "CCO - 002", "20020902", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTwo8.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTwo8);
		DtoCriticalEvent eventTree9 = new DtoCriticalEvent(3L, "", "129763", new Date(), "ALERT", "CCO - 003", "280904", "IN_ATTENDANCE", "2 mim", "1 mim");
		eventTree9.setStartDateTimeAsText(format(new Date()));
		ALL_EVENTS.add(eventTree9);

		populateExtraInfo();
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

	private static void populateExtraInfo() {
		DtoExtraInfoEvent extraInfo = new DtoExtraInfoEvent();
		extraInfo.setAbout(new DtoAboutEvent(1L, "0100123", format(new Date()), "5 min", "CSO - 01", format(new Date())));
		extraInfo.setContact(new DtoContact("Kara \"Starbuck\" Thrace", "20081202", "(85) 3023-8909", "35", "http://editorial.sidereel.com/Images/Posts/sackhoff.jpg"));
		extraInfo.setVehicleLocation(new DtoVehicleAndLocation("300123", "Av. Dom manuel", "Messejana Centro", "Aço Forte", "Nsa. Senhora", -3.736549, -38.523804));
		ArrayList<DtoRecord> records = new ArrayList<DtoRecord>();

		records.add(new DtoRecord(1L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "Olá CSO, boa noite"));
		records.add(new DtoRecord(2L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "Boa noite Motorista! "));
		records.add(new DtoRecord(3L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "Algum problema! "));
		records.add(new DtoRecord(4L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "Não! nenhum! "));
		records.add(new DtoRecord(3L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "Em que podemos ajudar?"));
		records.add(new DtoRecord(4L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "Podemos Jantar agora!"));
		records.add(new DtoRecord(3L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "Claro que sim!"));
		records.add(new DtoRecord(4L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "Grato!"));
		extraInfo.setRecords(records);

		
		EXTRA_INFO.put(1L, extraInfo);

		DtoExtraInfoEvent extraInfo2 = new DtoExtraInfoEvent();
		extraInfo2.setAbout(new DtoAboutEvent(2L, "0134523", format(new Date()), "5 min", "CSO - 02", format(new Date())));
		extraInfo2.setContact(new DtoContact("Laura Roselin", "20081201", "(85) 3453-8709", "36", "http://upload.wikimedia.org/wikipedia/en/0/05/LauraRoslin.jpg"));

		extraInfo2.setVehicleLocation(new DtoVehicleAndLocation("376125", "R. Barao do Rio BRanco", "Messejana Centro", "Ceara motor", "Nsa. Senhora", -3.739282, -38.532331));
		ArrayList<DtoRecord> records2 = new ArrayList<DtoRecord>();

		records2.add(new DtoRecord(11L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "Informamos pane na via"));
		records2.add(new DtoRecord(12L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "ok!"));
		records2.add(new DtoRecord(12L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "Enviando reboque! "));

		extraInfo2.setRecords(records2);

		EXTRA_INFO.put(2L, extraInfo2);

		DtoExtraInfoEvent extraInfo3 = new DtoExtraInfoEvent();
		extraInfo3.setAbout(new DtoAboutEvent(3L, "1234562", format(new Date()), "13 min", "CSO - 23", format(new Date())));
		extraInfo3.setContact(new DtoContact("Gaius Baltar", "20081203", "(88) 4362-1105", "43", "http://media.battlestarwiki.org/images/thumb/7/73/Gaius_Baltar.jpg/200px-Gaius_Baltar.jpg"));

		extraInfo3.setVehicleLocation(new DtoVehicleAndLocation("37618", "Godofredo Maciel", "Centro Messejana ", "Ceara motor", "Via MEtro", -3.759282, -38.532331));
		ArrayList<DtoRecord> records3 = new ArrayList<DtoRecord>();

		records3.add(new DtoRecord(21L, "http://cdn1.iconfinder.com/data/icons/Basic_set2_Png/32/user1.png", "OPERATOR", format(new Date()), "Ultima Viagem do dia"));
		records3.add(new DtoRecord(22L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "ok!"));
		records3.add(new DtoRecord(22L, "http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/32/bus.png", "VEHICLE", format(new Date()), "Recebemos sua informação! "));

		extraInfo3.setRecords(records3);

		EXTRA_INFO.put(3L, extraInfo3);

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
