
package org.picfight.chatbot.lambda;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.strings.Strings;

public class Handlers {

	public static final Map<String, Handler> handlers = Collections.newMap();

	static final Handler helloHandler = (final HandleArgs args) -> {
		respond(args.bot, args.update, Responses.HelloStart);
		return true;
	};
	static final Handler versionHandler = (final HandleArgs args) -> {
		respond(args.bot, args.update, "Code version: " + Version.VERSION);
		return true;
	};
	static final Handler passportHandler = (final HandleArgs args) -> {
		respond(args.bot, args.update, Responses.Passport);
		return true;
	};
	static final Handler fightsHandler = (final HandleArgs args) -> {
		respond(args.bot, args.update, Responses.Fights);
		return true;
	};
	static final Handler helpHandler = (final HandleArgs args) -> {
		respond(args.bot, args.update, Responses.Help);
		return true;
	};

	static {
		handlers.put(Commands.START, helloHandler);
		handlers.put(Commands.VERSION, versionHandler);
		handlers.put(Commands.PASSPORT, passportHandler);
		handlers.put(Commands.FIGHTS, fightsHandler);
		handlers.put(Commands.HELP, helpHandler);
		handlers.put(Commands.VKPROFILE, VKProfile.vkProfileHandler);
	}

	public static boolean handle (final AbsSender bot, final TelegramUpdate update, final String text)
		throws TelegramApiException {
		if (text == null) {
			return false;
		}
		if ("".equals(text)) {
			return false;
		}

		final List<String> lines = Strings.split(text, " ");

		if (lines.size() == 0) {
			return false;
		}

		final String command = lines.getElementAt(0);
		lines.removeElementAt(0);

		final Handler h = handlers.get(command);
		if (h == null) {
			return false;
		}

		final HandleArgs args = new HandleArgs();
		args.bot = bot;
		args.update = update;
		args.command = command;
		args.arguments = lines;

		return h.handle(args);
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

class HandleArgs {
	public AbsSender bot;
	public TelegramUpdate update;
	public String command;
	public List<String> arguments;
}

interface Handler {
	public boolean handle (HandleArgs args) throws TelegramApiException;
}
