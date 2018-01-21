package KokoBot.commands.commandTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcommands implements GenericEventFunctional {

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length==1) {
			Utilities.sendMessage(event, "Specify a Category, to see categories type -rcategories");
			return;
		}
		
		String Category = Utilities.messageSplit(event, " ", 1);
		
		List<String> roles = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles) {
			if(role.Category.equals(Category) && (!Utilities.messageSplit(event, " ", 0).contains(KokoBot.SelfAssignabilityCharacter) || role.IsSelfAssignable)) {
				roles.add(role.getName());
			}
		}
		
		Utilities.sendPrivateMessage(event, "List of roles under the category " + Category + ": " + roles.toString().replace("[", "").replace("]", ""));

	}

}
