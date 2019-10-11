
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.api.log.L;

public class JavaMainEntryPoint {

	public static void main (final String[] args) {
		final ChatRequestResponse requesthandler = new ChatRequestResponse();

		if (ChatBotActionHandler.handler == null) {
			ChatBotActionHandler.handler = new ChatBotActionHandler();
		}

		ChatBotActionHandler.handler.handleRequest(requesthandler);

		L.d("requesthandler", requesthandler);
	}

}
