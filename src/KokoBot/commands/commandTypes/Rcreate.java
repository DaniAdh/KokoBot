package KokoBot.commands.commandTypes;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Roles.CategorisedRole;
import KokoBot.Roles.RoleManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcreate implements GenericEventFunctional{
	
	

		@Override
		public void onEvent(MessageReceivedEvent event) throws IOException {
			if(event.getMessage().getContentRaw().split(" ").length<4) {
				event.getChannel().sendMessage("Not enough arguments, type -rhelp rcreate to get syntax").complete();
				return;
			}
			
			if(!event.getAuthor().isBot() && !KokoBot.guild.getMembersWithRoles(KokoBot.guild.getRolesByName("Role Manager", true).get(0)).contains(KokoBot.guild.getMember(event.getAuthor()))) {
				event.getChannel().sendMessage(new MessageBuilder().append("You do not have the \"Role Manager\" role!").build()).complete();
				return;
				}
			String Message = event.getMessage().getContentDisplay();
			boolean Exists = false;
			CategorisedRole ExistingRole = null;
			
			for(CategorisedRole role:KokoBot.roles) {
				if(role.role.getName().equals(Message.split(" ")[1])) {
					Exists = true;
					ExistingRole = role;
					break;
				}
			}   
			
			
			
			if(Exists) {
				String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
				BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt"));
				KokoBot.roles.set(KokoBot.roles.indexOf(ExistingRole), CategorisedRole.fromString(String.format("%s %s %s", Message.split(" ")[2],'\''+Message.split(" ")[1]+'\'',(Message.contains("rscreate")))));
				for(int i = 0;i<KokoBot.roles.size();i++) {
					bf.write(KokoBot.roles.get(i).toString()+(i==KokoBot.roles.size()-1 ? "" : '\n'));
				}
				bf.close();
				event.getChannel().sendMessage("Role " + Message.split(" ")[1] + " for Category " + Message.split(" ")[2] + " had been modified").complete();
			} else {
				String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
				BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt",true));
				KokoBot.gc.createRole().setName(Message.split(" ")[1]).setColor(new Color(Integer.parseInt(Message.split(" ")[3].substring(1, 7),16))).complete();
				System.out.println(String.format("%s %s %s", Message.split(" ")[2],'\''+Message.split(" ")[1]+'\'',Message.contains("rscreate")));
				KokoBot.roles.add(CategorisedRole.fromString(String.format("%s %s %s", Message.split(" ")[2],'\''+Message.split(" ")[1]+'\'',Message.contains("rscreate"))));
				bf.append("\n"+Message.split(" ")[2]+" \'"+Message.split(" ")[1]+"\' "+" false");
				bf.close();
				event.getChannel().sendMessage("Role " + Message.split(" ")[1] + " for Category " + Message.split(" ")[2] + " had been created").complete();
			}       
			
			
			
		}
		
}


