package KokoBot.commands.commandTypes.roleCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.EmoteEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class ErinfoForewards implements EmoteEventFunctional{

	public static void RefreshRinfoEmotes(MessageReactionAddEvent event){
		event.getChannel().getMessageById(event.getMessageId()).complete().clearReactions().complete();
		event.getChannel().getMessageById(event.getMessageId()).complete().addReaction("▶").complete();
	}
	
	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException{
		Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
		if(event.getUser().isBot()) {
			return message.getId();
		}
		List<String> categories = new ArrayList<String>();
		
		for(CategorisedRole role: KokoBot.roles) {
			if(!categories.contains(role.Category)) {
				categories.add(role.Category);
			}
		}
		
		boolean selfassignable = message.getContentDisplay().contains("Self-Assignable");
		String CategoryOld = message.getContentRaw().split(" ")[(selfassignable?1:0) + 2].split("`")[0].replaceAll("\n", "");
		String Category = categories.get((categories.indexOf(CategoryOld)+1)%(categories.size()));
		
		String roles = "";
		
		for(CategorisedRole role: KokoBot.roles) {
			if(role.Category.equals(Category) && (!selfassignable || role.IsSelfAssignable)) {
				roles += role.getName() + ", " + role.Description + "\n";
			}
		}
		
		
		message.editMessage((selfassignable? "Self-Assignable " : "") + "Roles under " + Category + "\n```" + roles + "\n```").complete();
		ErinfoForewards.RefreshRinfoEmotes(event);
		return message.getId();
		
	}



}
