package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.EmoteEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class ErinfoForewards implements EmoteEventFunctional{

	
	
	@Override
	public void onEvent(MessageReactionAddEvent event) throws IOException {
		Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
		message.editMessage("");
		//TODO
		
		/*
		String Category = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		
		List<String> roles = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles) {
			if(role.Category.equals(Category) && (!Utilities.SplitMessageAndGetIndex(event, " ", 0).contains(KokoBot.SelfAssignabilityCharacter) || role.IsSelfAssignable)) {
				roles.add(role.getName());
			}
		}

		return Utilities.sendMessage(event, "List of roles under the category " + Category + ": " + roles.toString().replace("[", "").replace("]", ""));*/
		
	}



}
