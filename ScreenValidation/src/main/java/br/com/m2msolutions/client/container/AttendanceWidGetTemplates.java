package br.com.m2msolutions.client.container;

//TODO AInda falta definir as constantes de internacionalização.
public class AttendanceWidGetTemplates {

	public static final String ABOUT_EVENT = createTemplateAboutEvent();
	public static final String VEHICLE_LOCATION = createTemplateVehicleLocation();
	public static final String EVENTS = createEventTemplate();
	public static final String RECORD_MESSAGES = createRecordsTemplate();
	public static final String CONTACTS = createContactsTemplate();

	private static String createTemplateVehicleLocation() {
		StringBuffer template = new StringBuffer();
		template.append("<div id=\"template-vehicle-location\"  class=\"vehicle-location\">");
		template.append("<table  ");
		template.append("<tr>");
		template.append("<td>Veículo: {" + DtoVehicleAndLocation.VEHICLE + "}</td>");
		template.append("<td>Endereço: {" + DtoVehicleAndLocation.ADDRESS + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td>Linha: {" + DtoVehicleAndLocation.BUSSERVICE_NAME + "}</td>");
		template.append("<td>Latitude: {" + DtoVehicleAndLocation.LATITUDE + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td>Empresa: {" + DtoVehicleAndLocation.COMPANY_NAME + "}</td>");
		template.append("<td>Longitude: {" + DtoVehicleAndLocation.LONGITUDE + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td>Proximo ponto: {" + DtoVehicleAndLocation.NEXT_STOP + "}</td>");
		template.append("</tr>");
		template.append("</table>");
		template.append("</div>");
		return template.toString();
	}

	private static String createContactsTemplate() {
		StringBuffer template = new StringBuffer();

		template.append("	<div id=\"template-contact\" class=\"contact\">");
		template.append("	<table>");
		template.append("		<tr>");
		template.append("			<td> <img id=\"image-message\" src=\"http://cdn1.iconfinder.com/data/icons/humano2/72x72/emblems/emblem-people.png\" </td>");
		template.append("			<td>");
		template.append("				 <div id=\"record\" class=\"record\">");
		template.append("					<br> Nome: Antonio Firmulano da Cunha Matos");
		template.append("					<br>Matricula:  097286358 ");
		template.append("					<br>Telefone: 3354-7689 ");
		template.append("					<br>Tempo de corrido do atendimento: 5m");
		template.append("					<br>Idade: 43 anos ");
		template.append("				 </div> ");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("	</table>");
		template.append("	</div>");

		return template.toString();
	}

	private static String createTemplateAboutEvent() {
		StringBuffer template = new StringBuffer();
		template.append("<div id=\"template-about-event\" class=\"about-event\">");
		template.append("<br> Numero do protocolo: {" + DtoAboutEvent.PROTOCOL + "} ");
		template.append("<br>Inicio do atendimento: {" + DtoAboutEvent.START_TIME + "} ");
		template.append("<br>Tempo de corrido do atendimento: {" + DtoAboutEvent.DURATION + "} ");
		template.append("<br>Atendente atual: Operador: {" + DtoAboutEvent.OPERATOR + "} ");
		template.append("<br>Conclusão do atendimento: {" + DtoAboutEvent.CONSLUSION + "}");
		template.append("</div>");
		return template.toString();
	}

	private static String createRecordsTemplate() {
		StringBuffer template = new StringBuffer();
		template.append("<div id=\"template-record-messages\" class=\"record-messages\">");
		template.append("<table>");
		template.append("<tr>");
		template.append("<td> <img id=\"image-message\" src=\"{" + DtoRecord.IMAGE_SRC + "}\" </td>");
		template.append("<td>");
		template.append("<div id=\"record\" class=\"record\">");
		template.append("[{" + DtoRecord.MESSAGE_DATE + "}] {" + DtoRecord.MESSAGE + "}");
		template.append("</div> ");
		template.append("</td>");
		template.append("</tr>");
		template.append("</table>");
		template.append("</div>");
		return template.toString();

	}

	private static String createEventTemplate() {
		StringBuffer template = new StringBuffer();

		template.append("<div id=\"template-events\">");
		template.append("<table>");
		template.append("<tr>");
		template.append("<td rowspan=\"2\"><img id=\"image-11\" src=\"{" + DtoEvent.IMAGE_SRC + "}\"/> </td>");
		template.append("<td> inicio: {" + DtoEvent.START_DATE_TIME + "} </td>");
		template.append("<td> Veiculo: {" + DtoEvent.VEHICLE_CODE + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td> Atendente: {" + DtoEvent.OPERATOR + "}</td>");
		template.append("<td> Protocolo: {" + DtoEvent.PROTOCOL + "}</td>");
		template.append("</tr>	");
		template.append("</table>");
		template.append("</div>");
		return template.toString();
	}

}
