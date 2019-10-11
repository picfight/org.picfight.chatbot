
package org.picfight.chatbot.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.jfixby.scarabei.api.log.L;

public class LambdaEntryPoint implements RequestHandler<Object, Object> {

	@Override
	public Object handleRequest (final Object input, final Context context) {
		final ChatRequestResponse requesthandler = new ChatRequestResponse();
		requesthandler.input = input;

		if (ChatBotActionHandler.handler == null) {
			ChatBotActionHandler.handler = new ChatBotActionHandler();
		}

		ChatBotActionHandler.handler.handleRequest(requesthandler);

		L.d("requesthandler", requesthandler);

		return requesthandler.output;
	}
}
