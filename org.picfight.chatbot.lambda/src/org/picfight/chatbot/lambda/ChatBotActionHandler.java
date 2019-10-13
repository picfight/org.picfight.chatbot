
package org.picfight.chatbot.lambda;

import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class ChatBotActionHandler {

	private static final ID TELEGRAM_BOT_TOKEN = Names.newID("TELEGRAM_BOT_TOKEN");
	private static final ID TELEGRAM_BOT_USERNAME = Names.newID("TELEGRAM_BOT_USERNAME");

	private final AbsSender bot;

	public ChatBotActionHandler () {
		final String token = SystemSettings.getStringParameter(TELEGRAM_BOT_TOKEN, null);
		final String username = SystemSettings.getStringParameter(TELEGRAM_BOT_USERNAME, null);

		final TelegramBotSpecs specs = new TelegramBotSpecs();
		specs.token = token;
		specs.botusername = username;

		this.bot = TelegramFactory.newBot(specs);
	}

	public static ChatBotActionHandler handler;

	public void handleRequest (final ChatRequestResponse requesthandler) throws TelegramApiException {
		final TelegramUpdate update = requesthandler.input;
		final String text = update.message.text.toLowerCase();
		final String responseText;

		final boolean success = Handlers.handle(this.bot, update, text);
		if (success) {
			return;
		}
		Handlers.respond(this.bot, update, "Echo: " + text);
	}

}
