package KokoBot.commands;

import java.util.LinkedList;
import java.util.List;
import KokoBot.KokoBot;

import KokoBot.commands.commandTypes.ReplyCommand;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandManager {
	
	private static List<Command> Commands = new LinkedList<Command>();
	
	public static void InitializeCommands() {
		Commands.add(new ReplyCommand("Hello", (Message) -> Message.getChannel().sendMessage("Hey There!").complete()));
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
