package KokoBot.commands.commandTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcategories implements GenericEventFunctional{

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		List<String> categories = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles) {
			if(!categories.contains(role.Category)) {
				categories.add(role.Category);
			}
		}
		
		Utilities.sendMessage(event, "List of Categories: " + categories.toString().replace("[", "").replace("]", ""));
		
	}

}
