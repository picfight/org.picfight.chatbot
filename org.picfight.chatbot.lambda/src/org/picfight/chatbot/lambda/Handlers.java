
package org.picfight.chatbot.lambda;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;

public class Handlers {

	public static final Map<String, Handler> handlers = Collections.newMap();

	static final Handler helloHandler = (bot, update, text) -> {
		respond(bot, update, Responses.HelloStart);
		return true;
	};
	static final Handler versionHandler = (bot, update, text) -> {
		respond(bot, update, "Code version: " + Version.VERSION);
		return true;
	};
	static final Handler passportHandler = (bot, update, text) -> {
		respond(bot, update, Responses.Passport);
		return true;
	};
	static final Handler fightsHandler = (bot, update, text) -> {
		respond(bot, update, Responses.Fights);
		return true;
	};
	static final Handler helpHandler = (bot, update, text) -> {
		respond(bot, update, Responses.Help);
		return true;
	};

	static {
		handlers.put(Commands.START, helloHandler);
		handlers.put(Commands.VERSION, versionHandler);
		handlers.put(Commands.PASSPORT, passportHandler);
		handlers.put(Commands.FIGHTS, fightsHandler);
		handlers.put(Commands.HELP, helpHandler);
	}

	public static boolean handle (final AbsSender bot, final TelegramUpdate update, final String text)
		throws TelegramApiException {

		final Handler h = handlers.get(text);
		if (h == null) {
			return false;
		}
		return h.handle(bot, update, text);
	}

	public static final void respond (final AbsSender bot, final TelegramUpdate update, final String responseText)
		throws TelegramApiException {
		final Long chatID = update.message.chatID;
		final SendMessage sendMessage = new SendMessage().setChatId(chatID).setText(responseText);
		L.d("Sending message: " + sendMessage);
		sendMessage.enableMarkdown(true);

		bot.execute(sendMessage);

		L.d("Message sent: " + sendMessage);
	}

}

interface Handler {
	public boolean handle (final AbsSender bot, final TelegramUpdate update, final String text) throws TelegramApiException;
}
