package br.com.m2msolutions.client.container;

import br.com.m2msolutions.shared.dto.DtoAboutEvent;
import br.com.m2msolutions.shared.dto.DtoContact;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoRecord;
import br.com.m2msolutions.shared.dto.DtoVehicleAndLocation;

//TODO AInda falta definir as constantes de internacionalização.
public class AttendanceWidGetTemplates {

	public static final String ABOUT_EVENT = createNewAboutTemplate();
	public static final String VEHICLE_LOCATION = createNewVehicleLocationTemplate();
	public static final String EVENTS = createEventTemplate();
	public static final String RECORD_MESSAGES = createRecordsTemplate();
	public static final String CONTACTS = createNewContactsTemplate();

	private static String createTemplateVehicleLocation() {
		StringBuffer template = new StringBuffer();
		template.append("<span id=\"template-vehicle-location\"  class=\"vehicle-location\">");
		template.append("<br>");
		template.append("<table  ");
		template.append("<tr>");
		template.append("<td><span class=\"template-label\">Veículo</span>: {" + DtoVehicleAndLocation.VEHICLE + "}</td>");
		template.append("<td><span class=\"template-label\">Endereço</span>: {" + DtoVehicleAndLocation.ADDRESS + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td><span class=\"template-label\">Linha</span>: {" + DtoVehicleAndLocation.BUSSERVICE_NAME + "}</td>");
		template.append("<td><span class=\"template-label\">Latitude</span>: {" + DtoVehicleAndLocation.LATITUDE + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td><span class=\"template-label\">Empresa</span>: {" + DtoVehicleAndLocation.COMPANY_NAME + "}</td>");
		template.append("<td><span class=\"template-label\">Longitude</span>: {" + DtoVehicleAndLocation.LONGITUDE + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td><span class=\"template-label\">Proximo ponto</span>: {" + DtoVehicleAndLocation.NEXT_STOP + "}</td>");
		template.append("</tr>");
		template.append("</table>");
		template.append("</span>");
		return template.toString();
	}

	private static String createNewVehicleLocationTemplate() {
		StringBuffer template = new StringBuffer();
		template.append("<span id=\"template-vehicle-location\"  class=\"vehicle-location\">");
		template.append("<table >");
		template.append("<tr>");
		template.append("<td>	");
		template.append("	<table >");

		template.append("		<tr>");
		template.append("			<td>");
		template.append("					<span class=\"template-label\">Veículo</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("					{" + DtoVehicleAndLocation.VEHICLE + "}");
		template.append("			</td>");
		template.append("		</tr>");

		template.append("		<tr>");
		template.append("			<td>");
		template.append("					<span class=\"template-label\">Linha</span>: ");
		template.append("			</td>");
		template.append("			<td>");
		template.append("					{" + DtoVehicleAndLocation.BUSSERVICE_NAME + "}");
		template.append("			</td>");
		template.append("		</tr>");

		template.append("		<tr>");
		template.append("			<td>");
		template.append("					<span class=\"template-label\">Empresa</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("					{" + DtoVehicleAndLocation.COMPANY_NAME + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("					<span class=\"template-label\">Proximo ponto</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("						{" + DtoVehicleAndLocation.NEXT_STOP + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("	</table>");
		template.append("</td>");
		template.append("<td style=\"vertical-align: top !important;  padding-left: 20px;\">	<table  >");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("					<span class=\"template-label\">Endereço</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("					{" + DtoVehicleAndLocation.ADDRESS + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("		    <td>");
		template.append("					<span class=\"template-label\">Latitude</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("					{" + DtoVehicleAndLocation.LATITUDE + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("					<span class=\"template-label\">Longitude</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("					{" + DtoVehicleAndLocation.LONGITUDE + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("	</table>");
		template.append("</td>");
		template.append("</tr>");
		template.append("</table>");
		template.append("");
		template.append("</span>");
		return template.toString();
	}

	private static String createContactsTemplate() {
		StringBuffer template = new StringBuffer();

		template.append("	<span id=\"template-contact\" class=\"contact\">");
		template.append("<br>");
		template.append("	<table>");
		template.append("		<tr>");
		template.append("			<td> <img style=\"width: 72px; height: 72px;\" id=\"image-message\" src=\"{" + DtoContact.IMAGE_SRC + "}\" </td>");
		template.append("			<td>");
		template.append("				 <span id=\"record\" class=\"record\">");
		template.append("					<span class=\"template-label\">Nome</span>: {" + DtoContact.NAME + "}");
		template.append("					<br><span class=\"template-label\">Matricula</span>:  {" + DtoContact.MATRICULA + "} ");
		template.append("					<br><span class=\"template-label\">Telefone</span>: {" + DtoContact.PHONE + "} ");
		template.append("					<br><span class=\"template-label\">Idade</span>: {" + DtoContact.AGE + "} ");
		template.append("				 </span> ");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("	</table>");
		template.append("	</span>");

		return template.toString();
	}

	private static String createNewContactsTemplate() {
		StringBuffer template = new StringBuffer();

		template.append("	<span id=\"template-contact\" class=\"contact\">");
		template.append("<br>");
		template.append("	<table>");
		template.append("		<tr>");
		template.append("			<td> <img style=\"width: 72px; height: 72px;\" id=\"image-message\" src=\"{" + DtoContact.IMAGE_SRC + "}\" </td>");
		template.append("			<td>");
		template.append("				 <span id=\"record\" class=\"record\">");
		template.append("				<table >");
		template.append("					<tr>");
		template.append("						<td>");
		template.append("							<span class=\"template-label\">Nome</span>:");
		template.append("						</td>");
		template.append("						<td>");
		template.append("							{" + DtoContact.NAME + "}");
		template.append("						</td>");
		template.append("					</tr>");
		template.append("					<tr>");
		template.append("						<td>");
		template.append("							<span class=\"template-label\">Matricula</span>: ");
		template.append("						</td>");
		template.append("						<td>");
		template.append("							{" + DtoContact.MATRICULA + "}");
		template.append("						</td>");
		template.append("					</tr>");
		template.append("					<tr>");
		template.append("						<td>");
		template.append("							<span class=\"template-label\">Telefone</span>:");
		template.append("						</td>");
		template.append("						<td>");
		template.append("							{" + DtoContact.PHONE + "}");
		template.append("						</td>");
		template.append("					</tr>");
		template.append("					<tr>");
		template.append("						<td>");
		template.append("							<span class=\"template-label\">Idade</span>:");
		template.append("						</td>");
		template.append("						<td>");
		template.append("							{" + DtoContact.AGE + "}");
		template.append("						</td>");
		template.append("					</tr>");
		template.append("				</table>");
		template.append("				 </span> ");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("	</table>");
		template.append("	</span>");

		return template.toString();
	}

	private static String createTemplateAboutEvent() {
		StringBuffer template = new StringBuffer();
		template.append("<span id=\"template-about-event\" class=\"about-event\">");
		template.append("<br><span class=\"template-label\">Numero do protocolo</span>: {" + DtoAboutEvent.PROTOCOL + "} ");
		template.append("<br><span class=\"template-label\">Inicio do atendimento</span>: {" + DtoAboutEvent.START_TIME + "} ");
		template.append("<br><span class=\"template-label\">Tempo de corrido do atendimento</span>: {" + DtoAboutEvent.DURATION + "} ");
		template.append("<br><span class=\"template-label\">Operador</span>: {" + DtoAboutEvent.OPERATOR + "} ");
		template.append("<br><span class=\"template-label\">Conclusão do atendimento</span>: {" + DtoAboutEvent.CONSLUSION + "}");
		template.append("</span>");
		return template.toString();
	}

	private static String createRecordsTemplate() {
		StringBuffer template = new StringBuffer();
		template.append("<span id=\"template-record-messages\" class=\"record-messages\">");
		template.append("<table>");
		template.append("<tr>");
		template.append("<td> <img id=\"image-message\" src=\"{" + DtoRecord.IMAGE_SRC + "}\" </td>");
		template.append("<td>");
		template.append("<span id=\"record\" class=\"record\">");
		template.append("[{" + DtoRecord.MESSAGE_DATE + "}] {" + DtoRecord.MESSAGE + "}");
		template.append("</span> ");
		template.append("</td>");
		template.append("</tr>");
		template.append("</table>");
		template.append("</span>");
		return template.toString();

	}

	private static String createEventTemplate() {
		// TODO internacionalização
		StringBuffer template = new StringBuffer();

		template.append("<span id=\"template-events\">");
		template.append("<table>");
		template.append("<tr>");
		template.append("<td rowspan=\"2\"><img id=\"image-11\" src=\"{" + DtoCriticalEvent.IMAGE_SRC + "}\"/> </td>");
		template.append("<td><span class=\"template-label\">inicio</span>: {" + DtoCriticalEvent.START_DATE_TIME + "} </td>");
		template.append("<td><span class=\"template-label\">Veiculo</span>: {" + DtoCriticalEvent.VEHICLE_CODE + "}</td>");
		template.append("</tr>");
		template.append("<tr>");
		template.append("<td><span class=\"template-label\">Atendente</span>: {" + DtoCriticalEvent.OPERATOR + "}</td>");
		template.append("<td><span class=\"template-label\">Protocolo</span>: {" + DtoCriticalEvent.PROTOCOL + "}</td>");
		template.append("</tr>	");
		template.append("</table>");
		template.append("</span>");
		return template.toString();
	}

	private static String createNewAboutTemplate() {
		StringBuffer template = new StringBuffer();
		template.append("<span id=\"template-events\">");
		template.append("		<table>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("				<span class=\"template-label\">Numero do protocolo</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("				{" + DtoAboutEvent.PROTOCOL + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("				<span class=\"template-label\">Inicio do atendimento</span>: ");
		template.append("			</td>");
		template.append("			<td>");
		template.append("				{" + DtoAboutEvent.START_TIME + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("				<span class=\"template-label\">Tempo de corrido do atendimento</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("				{" + DtoAboutEvent.DURATION + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("				<span class=\"template-label\">Operador</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("				{" + DtoAboutEvent.OPERATOR + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("		<tr>");
		template.append("			<td>");
		template.append("				<span class=\"template-label\">Conclusão do atendimento</span>:");
		template.append("			</td>");
		template.append("			<td>");
		template.append("				{" + DtoAboutEvent.CONSLUSION + "}");
		template.append("			</td>");
		template.append("		</tr>");
		template.append("	</table>");
		return template.toString();
	}
}
