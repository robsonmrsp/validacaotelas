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

import java.util.ArrayList;

import br.com.m2msolutions.client.CriticalEventsConfigurationService;
import br.com.m2msolutions.client.DtoEventType;
import br.com.m2msolutions.client.container.DtoCategory;
import br.com.m2msolutions.client.container.DtoCriticalEventsConfiguration;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CriticalEventsConfigurationServiceImpl extends RemoteServiceServlet implements CriticalEventsConfigurationService {
	private static final long serialVersionUID = 7896776137153226685L;

	@Override
	public ArrayList<DtoEventType> getCriticalEventsByCategory(DtoCategory category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean saveConfiguration(DtoCriticalEventsConfiguration createConfiguration) {
		// TODO Auto-generated method stub
		return null;
	}
}
