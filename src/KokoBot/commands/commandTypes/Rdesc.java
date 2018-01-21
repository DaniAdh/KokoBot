package KokoBot.commands.commandTypes;

import java.io.IOException;

import KokoBot.Utilities;
import KokoBot.KokoBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rdesc implements GenericEventFunctional{

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length==1) {
			Utilities.sendMessage(event, "Specify a role, enter -rinfo <Category> to get a list of roles for that category");
			return;
		}
		Utilities.sendMessage(event, Utilities.findRoleWithName(KokoBot.roles, Utilities.messageSplit(event, " ", 1)).Description);
		
	}

}
