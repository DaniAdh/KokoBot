package KokoBot.commands;

import java.util.LinkedList;
import java.util.List;
import KokoBot.KokoBot;
import KokoBot.Roles.CategorisedRole;
import KokoBot.commands.commandTypes.GenericCommand;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildManager;

public class CommandManager {
	
	private static List<Command> Commands = new LinkedList<Command>();
	
	public static void InitializeCommands() {
		Commands.add(new GenericCommand("Hello", (event) -> event.getChannel().sendMessage(new MessageBuilder().append("Hey there!").build()).complete()));
		Commands.add(new GenericCommand("Ping", (event) -> event.getChannel().sendMessage(new MessageBuilder().append("Pong").build()).complete()));
		/*
		Commands.add(new GenericCommand("rcreate", new GenericEventFunctional() {

			@Override
			public void onEvent(MessageReceivedEvent event) {
				String Message = event.getMessage().getContentDisplay();
				CategorisedRole ExistingRole;
				boolean Exists = false;
				for(CategorisedRole role:KokoBot.roles) {
					if(role.role.getName()==Message.split(" ")[1]) {
						Exists = true; 
						ExistingRole = role;
						break;
					}
				}
				if(Exists) {
					
				}
				
				
			}}));*/
	}
	
	public static void TestForCommands(MessageReceivedEvent MessageEvent) {
		String Message = MessageEvent.getMessage().getContentDisplay();
		if(!Message.contains(KokoBot.Prefix)) {return;}
		
		for(Command command:Commands) {
			if(command.ListenForEvent(Message.substring(1, Message.length()))) {
				command.onEvent(MessageEvent);
			}
		}	
	}
	

}
