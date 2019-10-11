
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class JavaMainEntryPoint {

	public static void main (final String[] args) {
		final ChatRequestResponse requesthandler = new ChatRequestResponse();

		if (ChatBotActionHandler.handler == null) {
			ChatBotActionHandler.handler = new ChatBotActionHandler();
			SystemSettings.setExecutionMode(ExecutionMode.EARLY_DEVELOPMENT);
		}

		ChatBotActionHandler.handler.handleRequest(requesthandler);

		L.d("requesthandler", requesthandler);
	}

}
