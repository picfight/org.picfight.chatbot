
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.api.json.Json;

public class ChatRequestResponse {

	public ChatRequestResponse () {
		this.build = "10001";
	}

	public TelegramUpdate input;
	public Object output;

	public String build;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}

}
