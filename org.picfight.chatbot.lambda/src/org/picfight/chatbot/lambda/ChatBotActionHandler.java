
package org.picfight.chatbot.lambda;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class ChatBotActionHandler {

	private static final String VERSION = "2000";

	static {
		ScarabeiDesktop.deploy();

	}
	private static final ID TELEGRAM_BOT_TOKEN = Names.newID("TELEGRAM_BOT_TOKEN");
	private static final ID TELEGRAM_BOT_USERNAME = Names.newID("TELEGRAM_BOT_USERNAME");

	private final AbsSender bot;

	public ChatBotActionHandler () {
		final String token = SystemSettings.getStringParameter(TELEGRAM_BOT_TOKEN, null);
		final String username = SystemSettings.getStringParameter(TELEGRAM_BOT_USERNAME, null);

		L.d("AwsTelegramBot version: " + VERSION);

		final TelegramBotSpecs specs = new TelegramBotSpecs();
		specs.token = token;
		specs.botusername = username;

		this.bot = TelegramFactory.newBot(specs);
	}

	public static ChatBotActionHandler handler;

	public void handleRequest (final ChatRequestResponse requesthandler) throws TelegramApiException {
		final TelegramUpdate update = requesthandler.input;
		final String text = update.message.text.toLowerCase();
		String responseText;
		if (text.equals("/version")) {
			responseText = "Version: " + VERSION;
		} else {
			responseText = "Echo> " + text;
		}

		final Long chatID = update.message.chatID;
		final SendMessage sendMessage = new SendMessage().setChatId(chatID).setText(responseText);
		L.d("Sending message: " + sendMessage);
		final Message message = this.bot.sendMessage(sendMessage);
		L.d("Message sent: " + message);

	}

}
