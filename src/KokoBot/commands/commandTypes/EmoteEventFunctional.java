package KokoBot.commands.commandTypes;

import java.io.IOException;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public interface EmoteEventFunctional {
	String onEvent(MessageReactionAddEvent event) throws IOException, InterruptedException;
}
