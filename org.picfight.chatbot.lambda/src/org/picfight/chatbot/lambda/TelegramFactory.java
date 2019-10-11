
package org.picfight.chatbot.lambda;

import org.telegram.telegrambots.bots.AbsSender;

public class TelegramFactory {

	public static AbsSender newBot (final TelegramBotSpecs specs) {
		return new TelegramBot(specs);
	}
}
