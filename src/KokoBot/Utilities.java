package KokoBot;

import java.util.List;

import KokoBot.Roles.CategorisedRole;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Utilities {

	public static String messageSplit(MessageReceivedEvent event, String split,int index) {
		return event.getMessage().getContentRaw().split(split)[index];
	}
	
	public static void sendMessage(MessageReceivedEvent event, String message) {
		event.getChannel().sendMessage(message).complete();
	}
	
	public static CategorisedRole findRoleWithName(List<CategorisedRole> roles, String name) {
		for(int i = 0;i<roles.size();i++) {
			if(roles.get(i).getName().equals(name)) {
				return roles.get(i);
			}
		}
		return null;
	}
	
	
	
	public static Member getMember(MessageReceivedEvent event) {
		return KokoBot.guild.getMember(event.getAuthor());
	}
	
	public static void sendPrivateMessage(MessageReceivedEvent event, String message) {
		event.getAuthor().openPrivateChannel().complete().sendMessage(message).complete();
	}
}
