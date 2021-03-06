package KokoBot.commands.commandTypes.roleCommands;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcreate implements GenericEventFunctional{
	
	

		@Override
		public Message onEvent(MessageReceivedEvent event) throws IOException {
			if(event.getMessage().getContentRaw().split(" ").length<2+1) {
				Utilities.sendMessage(event, "Not enough arguments, type -help rcreate to get syntax");
				return null;
			}
			
			if(!event.getAuthor().isBot() && !event.getGuild().getMembersWithRoles(Utilities.findRoleWithName(KokoBot.roles.get(event.getGuild().getId()), "Role Manager").role).contains(Utilities.getMember(event))) {
				Utilities.sendMessage(event, ("You do not have the \"Role Manager\" role!"));
				return null;
			}

			String name = Utilities.SplitMessageAndGetIndex(event, " ", 1);
			String category = Utilities.SplitMessageAndGetIndex(event, " ", 2);
			Color color = new Color(Integer.parseInt(Utilities.fromMessageCharacter(event, "#", 6, "FFFFFF"), 16));
			String description = Utilities.getPaddedSubstringFromMessage(event, "\"", "");
			
			

			
			CategorisedRole ExistingRole = Utilities.findRoleWithName(KokoBot.roles.get(event.getGuild().getId()), name);
			boolean selfassignable = Utilities.SplitMessageAndGetIndex(event, " ", 0).contains(KokoBot.SelfAssignabilityCharacter);
			String characterisedstring = FileUtilities.makeLine(new String[] {category, name, String.valueOf(selfassignable), description}, new String[] {"","\'","","\""});
			
			CategorisedRole Role = CategorisedRole.fromString(characterisedstring,event.getGuild());
			
			//If the role exists
			
			if(ExistingRole!=null) {
				List list2 = KokoBot.roles.get(event.getGuild().getId());
				list2.set(KokoBot.roles.get(event.getGuild().getId()).indexOf(ExistingRole), Role);
				KokoBot.roles.put(event.getGuild().getId(),list2);
				
				FileUtilities.overrideFile(KokoBot.path+"Roles/Roles.txt", Arrays.asList(KokoBot.roles.toString().replace("[", "").replace("]", "").split(",")));
				 
				return Utilities.sendMessage(event, "Role " + name + " for Category " + category + " had been modified");
			} else {
				
				KokoBot.gc.get(event.getGuild().getId()).createRole().setName(name).setColor(color).complete();
				Role = CategorisedRole.fromString(characterisedstring,event.getGuild());
				KokoBot.roles.put(event.getGuild().getId(),Utilities.add(KokoBot.roles.get(event.getGuild().getId()), Role));
				FileUtilities.addToFile(KokoBot.path+"Roles/Roles.txt", characterisedstring);
				 
				return Utilities.sendMessage(event, "Role " + name + " for Category " + category + " had been created");
			}       
			
			
			
		}
		
}


