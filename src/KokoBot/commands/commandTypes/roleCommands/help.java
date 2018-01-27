package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;

import KokoBot.Utilities;
import KokoBot.commands.CommandManager;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class help implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		return Utilities.sendPrivateMessage(event, CommandManager.Help);
	}

}
