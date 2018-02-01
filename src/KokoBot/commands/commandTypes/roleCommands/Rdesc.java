package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rdesc implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length==1) {
			Utilities.sendMessage(event, "Specify a role, enter -rinfo <Category> to get a list of roles for that category");
			return null;
		}
		try {
			return Utilities.sendMessage(event, Utilities.findRoleWithName(KokoBot.roles.get(event.getGuild().getId()), Utilities.SplitMessageAndGetIndex(event, " ", 1)).Description);
		}catch(IllegalArgumentException e) {
			return Utilities.sendMessage(event, "No description found :(, add one using -rcreate");
		}
	}

}
