/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package br.com.m2msolutions.server;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.m2msolutions.client.CriticalEventMessage;
import br.com.m2msolutions.client.CriticalEventsService;
import br.com.m2msolutions.client.DtoCriticalEventsInfo;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.service.RemoteEventServiceServlet;

public class CriticalEventsServiceImpl extends RemoteEventServiceServlet implements CriticalEventsService {

	private static final long serialVersionUID = 1L;

	@Override
	public DtoCriticalEventsInfo getCriticalEventsInfo() {
		return null;
	}

	@Override
	public Boolean startAutomaticUpdating() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean endAutomaticUpdating() {
		// TODO Auto-generated method stub
		return null;
	}

	//esse ator será remoto.
	//Pergunta:
		//Como assinar a um servico que está sendo executado em outra maquina
	class CriticalEventsActor extends UntypedActor {
		LoggingAdapter log = Logging.getLogger(getContext().system(), this);

		public void onReceive(Object message) throws Exception {
			if (message instanceof DtoCriticalEvent) {				
				DtoCriticalEvent messageEvent = (DtoCriticalEvent) message;
				Event theEvent = new CriticalEventMessage(messageEvent);
				addEvent(CriticalEventMessage.SERVER_MESSAGE_DOMAIN, theEvent);
			} else {
				unhandled(message);
			}
		}
	}
}
