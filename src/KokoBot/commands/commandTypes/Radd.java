package KokoBot.commands.commandTypes;

import java.io.IOException;

import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Radd implements GenericEventFunctional{

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		Role role;
		try {
		role = KokoBot.guild.getRolesByName(event.getMessage().getContentRaw().split(" ")[1], true).get(0);
		}catch(Exception e){
			event.getChannel().sendMessage("Role " + event.getMessage().getContentRaw().split(" ")[1] + " does not exist!").complete();
			return;
		}
		
		for(int i = 0;i<KokoBot.roles.size();i++) {
			if(KokoBot.roles.get(i).getName().equals(role.getName())) {
				if(!KokoBot.roles.get(i).IsSelfAssignable) {
					event.getChannel().sendMessage("Role " + role.getName() + " is not self-assignable!").complete();
					return;
				}
			}
		}
		KokoBot.gc.addSingleRoleToMember(KokoBot.guild.getMember(event.getAuthor()), role).complete();
		event.getChannel().sendMessage("Added Role " + role.getName() + " to " + event.getAuthor().getName()).complete();
	}

}
