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
package br.com.m2msolutions.client;

import java.util.List;

import br.com.m2msolutions.client.container.DtoPredefinedMessage;
import br.com.m2msolutions.shared.dto.DtoContact;
import br.com.m2msolutions.shared.dto.DtoContactParameters;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoOperator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CriticalEventsAttendanceService")
public interface CriticalEventsAttendanceService extends RemoteService {

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static CriticalEventsAttendanceServiceAsync instance;

		public static CriticalEventsAttendanceServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(CriticalEventsAttendanceService.class);
			}
			return instance;
		}
	}
	
	List<DtoContact> getContactList(DtoContactParameters dtoContactParameters);
	
	Boolean updateContactForAttendance(DtoContact dtoContact);
	
	List<DtoOperator> getOperators();

	Boolean transferAttendance(DtoCriticalEvent actualEvent, DtoOperator operator);

	DtoExtraInfoEvent findExtraInfoEvent(DtoCriticalEvent selectedItem);

	Boolean closeAttencance(DtoCriticalEvent actualEvent);

	Boolean savePredefinedMessage(DtoPredefinedMessage predefinedMessage);

	Boolean removePredefinedMessage(DtoPredefinedMessage predefinedMessage);

	Boolean updatePredefinedMessage(DtoPredefinedMessage predefinedMessage);
}
