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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("criticalEventsService")
public interface CriticalEventsService extends RemoteService {

	/**
	 * Deve startar o mecanismo de DATA PUSH para alimentar o widget, bem como
	 * retornar a lista dos ultimos X eventos que AINDA não foram resolvidos
	 * (novos ou em atendimento), ordenados pela DATA de criação dos mesmos
	 * 
	 * 
	 * @return o suficiente para que a tela seja populada na sua chamada
	 */
	// TODO definir o valor de X provavelmente serão todos os eventos existentes
	public DtoCriticalEventsInfo getCriticalEventsInfo();
	
	/**
	 * Inicia a atualização automática da tela. De preferencia será por data-push
	 * @return  true caso consiga iniciar  false em caso contrário 
	 */
	public Boolean startAutomaticUpdating();
	/**
	 * finaliza a atualização automática da tela
	 * @return  true caso consiga terminar false em caso contrário
	 */
	public Boolean endAutomaticUpdating();

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static CriticalEventsServiceAsync instance;

		public static CriticalEventsServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(CriticalEventsService.class);
			}
			return instance;
		}
	}

}
