
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class ChatBotActionHandler {

	public ChatBotActionHandler () {
		ScarabeiDesktop.deploy();
	}

	public static ChatBotActionHandler handler;

	public void handleRequest (final ChatRequestResponse requesthandler) {
		requesthandler.output = requesthandler.input;
	}

}
