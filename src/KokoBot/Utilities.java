package KokoBot;

import java.util.List;

import KokoBot.Roles.CategorisedRole;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Utilities {

	public static String SplitMessageAndGetIndex(MessageReceivedEvent event, String split,int index) {
		return event.getMessage().getContentRaw().split(split)[index];
	}
	
	public static String SplitMessageAndGetIndex(String string, String split,int index) {
		return string.split(split)[index];
	}
	
	public static String getPaddedSubstringFromMessage(MessageReceivedEvent event, String padder, String defaultstring) {
		if(Utilities.getMessage(event).contains(padder)) {
			try {
				return Utilities.SplitMessageAndGetIndex(event, padder, 1);
			}catch(ArrayIndexOutOfBoundsException e){
				return defaultstring;
			}
		}
		return defaultstring;
		
	}
	
	public static Role getRoleByName(String roleName) {
		return KokoBot.guild.getRolesByName(roleName, true).get(0);
	}
	
	public static Emote getGenericEmoteByName(String emoteName) {
		return KokoBot.jda.getEmotesByName(emoteName, true).get(0);
	}
	
	public static String getPaddedSubstringFromMessage(String string, String padder, String defaultstring) {
		if(string.contains(padder)) {
			try {
				return Utilities.SplitMessageAndGetIndex(string, padder, 1);
			}catch(ArrayIndexOutOfBoundsException e){
				return defaultstring;
			}
		}
		return defaultstring;
		
	}
	
	public static String fromMessageCharacter(MessageReceivedEvent event , String startcharacter, int length, String def) {
		if(Utilities.getMessage(event).contains(startcharacter)) {
			try {
				return Utilities.getMessage(event).substring(Utilities.getMessage(event).indexOf(startcharacter)+1, Utilities.getMessage(event).indexOf(startcharacter)+length+1);
			}catch(ArrayIndexOutOfBoundsException e){
				return def;
			}
		}
		return def;
	}
	
	public static Message sendMessage(MessageReceivedEvent event, String message) {
		return event.getChannel().sendMessage(message).complete();
	}
	
	public static void sendMessage(MessageChannel channel, String message) {
		channel.sendMessage(message).complete();
	}
	
	public static String getMessage(MessageReceivedEvent event) {
		return event.getMessage().getContentRaw();
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
	
	public static Message sendPrivateMessage(MessageReceivedEvent event, String message) {
		return event.getAuthor().openPrivateChannel().complete().sendMessage(message).complete();
	}
}
