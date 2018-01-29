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
		
		
		String Category;
		
		List<String> categories = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles) {
			if(!categories.contains(role.Category)) {
				categories.add(role.Category);
			}
		}
		
		try {
			Category = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		}catch(Exception e) {
			Category = categories.get(0);
		}
		
		boolean selfassignable = Utilities.SplitMessageAndGetIndex(event, " ", 0).contains(KokoBot.SelfAssignabilityCharacter);
		
		String roles = "";
		
		for(CategorisedRole role: KokoBot.roles) {
			if(role.Category.equals(Category) && (!selfassignable || role.IsSelfAssignable)) {
				roles += role.getName() + ", " + role.Description + "\n";
			}
		}
		
		Message msg = Utilities.sendMessage(event, (selfassignable? "Self-Assignable" : "") + " Roles under " + Category + "\n```" + roles.toString().replace("[", "").replace("]", "") + "\n```");
		msg.addReaction("▶").complete();
		return msg;

	}

}
