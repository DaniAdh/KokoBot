package KokoBot.commands;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Roles.CategorisedRole;
import KokoBot.Roles.RoleManager;
import KokoBot.commands.commandTypes.GenericCommand;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandManager {
	
	private static List<Command> Commands = new LinkedList<Command>();
	
	public static void InitializeCommands() {
		Commands.add(new GenericCommand("Hello", (event) -> event.getChannel().sendMessage(new MessageBuilder().append("Hey there!").build()).complete()));
		Commands.add(new GenericCommand("Ping", (event) -> event.getChannel().sendMessage(new MessageBuilder().append("Pong").build()).complete()));
		Commands.add(new GenericCommand("rcreate", new GenericEventFunctional() { // -rcreate Name Category r g b

			@Override
			public void onEvent(MessageReceivedEvent event) throws IOException {
				String Message = event.getMessage().getContentDisplay();
				boolean Exists = false;
				CategorisedRole ExistingRole = null;
				
				for(CategorisedRole role:KokoBot.roles) {
					if(role.role.getName()==Message.split(" ")[1]) {
						Exists = true;
						ExistingRole = role;
						break;
					}
				}   
				
				
				
				if(Exists) {
					String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
					BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt"));
					KokoBot.roles.set(KokoBot.roles.indexOf(ExistingRole), CategorisedRole.fromString(String.format("%s %s %s", Message.split(" ")[2],'\''+Message.split(" ")[1]+'\'',false)));
					for(int i = 0;i<KokoBot.roles.size();i++) {
						bf.write(KokoBot.roles.get(i).toString());
					}
					bf.close();
				} else {
					String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
					BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt",true));
					KokoBot.gc.createRole().setName(Message.split(" ")[1]).setColor(new Color(Integer.valueOf(Message.split(" ")[3]),Integer.valueOf(Message.split(" ")[4]),Integer.valueOf(Message.split(" ")[5]))).complete();
					KokoBot.roles.add(CategorisedRole.fromString(String.format("%s %s %s", Message.split(" ")[2],'\''+Message.split(" ")[1]+'\'',false)));
					//TODO figure out why the roles file gets cleared every time it uses rcreate
					bf.append("\n"+Message.split(" ")[2]+" \'"+Message.split(" ")[1]+"\' "+" false");
					bf.close();
				}       
				
			}}));
	}
	
	public static void TestForCommands(MessageReceivedEvent MessageEvent) throws IOException {
		String Message = MessageEvent.getMessage().getContentDisplay();
		if(!Message.contains(KokoBot.Prefix)) {return;}
		
		for(Command command:Commands) {
			if(command.ListenForEvent(Message.substring(1, Message.length()))) {
				command.onEvent(MessageEvent);
			}
		}	
	}
	

}
