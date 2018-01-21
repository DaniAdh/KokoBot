package KokoBot.commands.commandTypes;

import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Radd implements GenericEventFunctional{

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		CategorisedRole role = Utilities.findRoleWithName(KokoBot.roles, Utilities.messageSplit(event, " ", 1));
		
		if(role==null) {
			Utilities.sendMessage(event, "Role " + Utilities.messageSplit(event, " ", 1) + " does not exist!");
			return;
		}
		
		if(!role.IsSelfAssignable) {
			event.getChannel().sendMessage("Role " + role.getName() + " is not self-assignable!").complete();
			return;
		}
			
		
		KokoBot.gc.addSingleRoleToMember(Utilities.getMember(event), role.role).complete();
		Utilities.sendMessage(event, "Added Role " + role.getName() + " to " + event.getAuthor().getName());
	}

}
