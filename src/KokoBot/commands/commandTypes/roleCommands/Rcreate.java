package KokoBot.commands.commandTypes.roleCommands;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcreate implements GenericEventFunctional{
	
	

		@Override
		public void onEvent(MessageReceivedEvent event) throws IOException {
			if(event.getMessage().getContentRaw().split(" ").length<2+1) {
				Utilities.sendMessage(event, "Not enough arguments, type -help rcreate to get syntax");
				return;
			}
			
			if(!event.getAuthor().isBot() && !KokoBot.guild.getMembersWithRoles(KokoBot.guild.getRolesByName("Role Manager", true).get(0)).contains(Utilities.getMember(event))) {
				Utilities.sendMessage(event, ("You do not have the \"Role Manager\" role!"));
				return;
			}

			String name = Utilities.messageSplit(event, " ", 1);
			String category = Utilities.messageSplit(event, " ", 2);
			Color color = new Color(Integer.parseInt(Utilities.fromMessageCharacter(event, "#", 6, "FFFFFF"), 16));
			String description = Utilities.fromMessage(event, "\"", "");
			
			

			
			CategorisedRole ExistingRole = Utilities.findRoleWithName(KokoBot.roles, name);
			boolean selfassignable = Utilities.messageSplit(event, " ", 0).contains(KokoBot.SelfAssignabilityCharacter);
			
			
			CategorisedRole Role = CategorisedRole.fromString(String.format("%s %s %s %s", category,'\''+name+'\'',selfassignable,'\"'+description+'\"'));
			
			//If the role exists
			
			if(ExistingRole!=null) {
				BufferedWriter bf = new BufferedWriter(new FileWriter(KokoBot.path, false));
				KokoBot.roles.set(KokoBot.roles.indexOf(ExistingRole), Role);
				for(int i = 0;i<KokoBot.roles.size();i++) {
				 	bf.write(KokoBot.roles.get(i).toString()+(i==KokoBot.roles.size()-1 ? "" : '\n'));
				}
				bf.close();
				
				Utilities.sendMessage(event, "Role " + name + " for Category " + category + " had been modified");
			} else {
				BufferedWriter bf = new BufferedWriter(new FileWriter(KokoBot.path,true));
				KokoBot.gc.createRole().setName(name).setColor(color).complete();
				Role = CategorisedRole.fromString(String.format("%s %s %s %s", category,'\''+name+'\'',selfassignable,'\"'+description+'\"'));
				KokoBot.roles.add(Role);
				bf.append("\n"+category+" \'"+name+"\' " + String.valueOf(selfassignable) + " \"" + description + "\"");
				bf.close();
				
				Utilities.sendMessage(event, "Role " + name + " for Category " + category + " had been created");
			}       
			
			
			
		}
		
}


