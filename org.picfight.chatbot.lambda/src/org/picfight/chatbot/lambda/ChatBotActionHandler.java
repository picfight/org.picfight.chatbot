
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class ChatBotActionHandler {

	static {
		ScarabeiDesktop.deploy();

	}
	private static final ID TELEGRAM_BOT_TOKEN = Names.newID("TELEGRAM_BOT_TOKEN");

	public ChatBotActionHandler () {

	}

	public static ChatBotActionHandler handler;

	public void handleRequest (final ChatRequestResponse requesthandler) {
		final Map<ID, Object> settings = SystemSettings.listAllSettings();
		L.d("SystemSettings", settings);

		final String token = SystemSettings.getStringParameter(TELEGRAM_BOT_TOKEN, null);

		requesthandler.output = requesthandler.input;
	}

}
