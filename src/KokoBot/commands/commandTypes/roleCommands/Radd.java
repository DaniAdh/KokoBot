package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Radd implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		CategorisedRole role = Utilities.findRoleWithName(KokoBot.roles, Utilities.SplitMessageAndGetIndex(event, " ", 1));
		
		if(role==null) {
			Utilities.sendMessage(event, "Role " + Utilities.SplitMessageAndGetIndex(event, " ", 1) + " does not exist!");
			return null;
		}
		
		if(!role.IsSelfAssignable) {
			event.getChannel().sendMessage("Role " + role.getName() + " is not self-assignable!").complete();
			return null;
		}
			
		
		KokoBot.gc.addSingleRoleToMember(Utilities.getMember(event), role.role).complete();
		return Utilities.sendMessage(event, "Added Role " + role.getName() + " to " + event.getAuthor().getName());
	}

}
