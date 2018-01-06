package KokoBot.commands.commandTypes;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface GenericEventFunctional {
	void onEvent(MessageReceivedEvent event);
}
