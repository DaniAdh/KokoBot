package KokoBot.commands.commandTypes;

import java.io.IOException;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public interface EmoteEventFunctional {
	void onEvent(MessageReactionAddEvent event) throws IOException;
}
