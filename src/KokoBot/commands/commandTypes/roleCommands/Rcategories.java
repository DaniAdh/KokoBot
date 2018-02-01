package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcategories implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		List<String> categories = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles.get(event.getGuild().getId())) {
			if(!categories.contains(role.Category)) {
				categories.add(role.Category);
			}
		}
		
		return Utilities.sendMessage(event, "List of Categories: " + categories.toString().replace("[", "").replace("]", ""));
		
	}

}
