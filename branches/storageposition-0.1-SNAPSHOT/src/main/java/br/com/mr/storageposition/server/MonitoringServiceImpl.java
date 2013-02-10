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
package br.com.mr.storageposition.server;

import br.com.mr.storageposition.client.MonitoringService;
import br.com.mr.storageposition.client.UserInfo;
import br.com.mr.storageposition.shared.UserInfoPosition;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MonitoringServiceImpl extends RemoteServiceServlet implements MonitoringService {

	private static final long serialVersionUID = -2501538367988810119L;

	@Override
	public UserInfoPosition startServices(UserInfo user) {
		return new UserInfoPosition("Marcio Robson", 0.0, 0.0, "http://cdn1.iconfinder.com/data/icons/IconsLandVistaPeopleIconsDemo/32/Person_Undefined_Male_Dark.png");
	}
}
