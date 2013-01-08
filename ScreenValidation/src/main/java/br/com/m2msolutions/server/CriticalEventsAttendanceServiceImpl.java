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

import java.util.List;

import br.com.m2msolutions.client.CriticalEventsAttendanceService;
import br.com.m2msolutions.client.container.DtoPredefinedMessage;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoOperator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CriticalEventsAttendanceServiceImpl extends RemoteServiceServlet implements CriticalEventsAttendanceService {
	@Override
	public List<DtoOperator> getOperators() {
		return null;
	}

	@Override
	public Boolean transferAttendance(DtoCriticalEvent actualEvent, DtoOperator operator) {
		return null;
	}

	@Override
	public DtoExtraInfoEvent findExtraInfoEvent(DtoCriticalEvent event) {
		return UtilData.EXTRA_INFO.get(event.getId());
	}

	@Override
	public Boolean closeAttencance(DtoCriticalEvent actualEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean savePredefinedMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removePredefinedMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePredefinedMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		return null;
	}
}
