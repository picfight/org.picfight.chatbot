
package org.picfight.chatbot.lambda;

public class Responses {

	private static final String NEW_LINE = "\n";

	private static String lines (final String... lines) {
		final StringBuilder b = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			b.append(lines[i]);
			b.append(NEW_LINE);
		}
		return b.toString();
	}

	public static final String HelloStart = lines(//
		"Привет пикфайтер!", //
		"Задавай мне любые вопросы. Список тем прилагается:", //
		Commands.PASSPORT, //
		Commands.FIGHTS, //
		Commands.HELP, //
		""//
	);

	public static final String Help = lines(//
		"Если есть проблемы с ботом, спроси об этом у лягухи:", //
		"@JFixbi", //
		""//
	);;

	public static final String Fights = lines(//
		"Список файтов пока не загружется.", //
		"Жди обновлений бота.", //
		""//
	);;

	public static final String Passport = lines(//
		"У тебя есть криптографический пасспорт пикфайтера?", //
		""//
	);;

}
