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

public class Rcommands implements GenericEventFunctional {

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		System.out.println("Test");
		if(event.getMessage().getContentRaw().split(" ").length==1) {
			Utilities.sendMessage(event, "Specify a Category, to see categories type -rcategories");
			return null;
		}
		
		String Category = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		
		List<String> roles = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles) {
			if(role.Category.equals(Category) && (!Utilities.SplitMessageAndGetIndex(event, " ", 0).contains(KokoBot.SelfAssignabilityCharacter) || role.IsSelfAssignable)) {
				roles.add(role.getName());
			}
		}

		return Utilities.sendMessage(event, "List of roles under the category " + Category + ": " + roles.toString().replace("[", "").replace("]", ""));

	}

}
