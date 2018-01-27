 package KokoBot.commands.commandTypes;

import java.io.IOException;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface GenericEventFunctional {
	Message onEvent(MessageReceivedEvent event) throws IOException;
}
