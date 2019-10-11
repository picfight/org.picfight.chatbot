
package org.picfight.chatbot.lambda;

import com.jfixby.scarabei.api.json.Json;

public class ChatRequestResponse {

	public Object input;
	public Object output;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}

}
