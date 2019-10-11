
package org.picfight.chatbot.lambda;

import java.util.function.Function;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

public class TelegramBotSpecs {
	public String token;
	public String botusername;
	public String path;
	public Function<Update, BotApiMethod> onUpdate;
}
