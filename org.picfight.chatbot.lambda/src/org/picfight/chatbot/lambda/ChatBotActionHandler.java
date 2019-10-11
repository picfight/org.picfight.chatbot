
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class ChatBotActionHandler {

	public ChatBotActionHandler () {
		ScarabeiDesktop.deploy();
	}

	public static ChatBotActionHandler handler;

	public void handleRequest (final ChatRequestResponse requesthandler) {
		final Map<ID, Object> settings = SystemSettings.listAllSettings();
		L.d("SystemSettings", settings);

		requesthandler.output = requesthandler.input;
	}

}
