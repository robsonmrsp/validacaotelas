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

import java.util.ArrayList;

import br.com.m2msolutions.client.container.DtoCategory;
import br.com.m2msolutions.client.container.DtoCriticalEventsConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("criticalEventsConfigurationService")
public interface CriticalEventsConfigurationService extends RemoteService {

	/**
	 * Devolve os eventos que foram associados a uma determinada categoria.
	 * 
	 * @param category
	 * @return
	 */
	ArrayList<DtoEventType> getCriticalEventsByCategory(DtoCategory category) throws Exception;

	/**
	 * Salva a configuração que foi "Montada" na tela. Respeitando as ordens
	 * definidas.
	 * 
	 * Provavelmente apos salvar esta categoria, será necessário comunicar com o
	 * mecanismo de atores para que este possa se atualizar segundo o novo
	 * formato da configuração.
	 **/
	Boolean saveConfiguration(DtoCriticalEventsConfiguration createConfiguration) throws Exception;

	public static class Util {
		private static CriticalEventsConfigurationServiceAsync instance;

		public static CriticalEventsConfigurationServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(CriticalEventsConfigurationService.class);
			}
			return instance;
		}
	}

}
