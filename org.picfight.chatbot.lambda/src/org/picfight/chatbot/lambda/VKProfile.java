
package org.picfight.chatbot.lambda;

public class VKProfile {

	public static final String LINK = "link"

	public static final String VKMenu = Responses.lines(//
		"Привет пикфайтер!", //
		"Доступные комманды:", //
		"", //
		"Привязать VK профиль:", //
		Commands.VKPROFILE + " " + LINK + " %ссылка на твой vk профиль%", //
		"Пример: " + Commands.VKPROFILE + " " + LINK + " https://vk.com/jfixbi", // , //
		"", //
		"Помощь", //
		Commands.HELP, //
		""//
	);

	static final Handler vkProfileHandler = (final HandleArgs args) -> {
		if (args.arguments.size() == 0) {
			Handlers.respond(args.bot, args.update, VKProfile.VKMenu);
			return true;
		}

		if (args.arguments.contains(LINK)) {
			if (args.arguments.size() > 2) {
				final String supposedlink = args.arguments.getElementAt(1);

			}
		}

		Handlers.respond(args.bot, args.update, VKProfile.VKMenu);
		return true;
	};

}
