package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;

import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import KokoBot.KokoBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rdesc implements GenericEventFunctional{

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length==1) {
			Utilities.sendMessage(event, "Specify a role, enter -rinfo <Category> to get a list of roles for that category");
			return;
		}
		try {
			Utilities.sendMessage(event, Utilities.findRoleWithName(KokoBot.roles, Utilities.messageSplit(event, " ", 1)).Description);
		}catch(IllegalArgumentException e) {
			Utilities.sendMessage(event, "No description found :(, add one using -rcreate");
		}
	}

}
