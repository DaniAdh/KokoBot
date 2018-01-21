package KokoBot.commands;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericCommand;
import KokoBot.commands.commandTypes.Radd;
import KokoBot.commands.commandTypes.Rcategories;
import KokoBot.commands.commandTypes.Rcommands;
import KokoBot.commands.commandTypes.Rcreate;
import KokoBot.commands.commandTypes.Rdesc;
import KokoBot.commands.commandTypes.Rpurge;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandManager {
	
	private static List<GenericCommand> Commands = new LinkedList<GenericCommand>();
	
	private static String Help = "";
	
	public static void InitializeCommands() {
		Commands.add(new GenericCommand("Mention", (event) -> Utilities.sendMessage(event, Utilities.getMember(event).getAsMention()),"Replies with \"Pong\""));
		Commands.add(new GenericCommand("Ping", (event) -> Utilities.sendMessage(event, "Pong"),"Replies with \"Pong\""));
		Commands.add(new GenericCommand("rcreate", new Rcreate(),"Syntax: -rcreate <Name> <Category> #<Hex value for color>, creates a non-self-assignable role with a name, category and color"));
		Commands.add(new GenericCommand("rscreate", new Rcreate(),"Syntax: -rscreate <Name> <Category> #<Hex value for color>, creates a self-assignable role with a name, category and color"));
		Commands.add(new GenericCommand("radd", new Radd(), "Syntax: -radd <Name>, creates a self-assignable role with a name, category and color"));
		Commands.add(new GenericCommand("rcategories", new Rcategories(), "Replies with a list of categories on this server"));
		Commands.add(new GenericCommand("rinfo", new Rcommands(), "Syntax: -rinfo <Category>, Sends the user a PM with a list of non-self-assignable roles with category <Category> on this server"));
		Commands.add(new GenericCommand("rsinfo", new Rcommands(), "Syntax: -rsinfo <Category>, Sends the user a PM with a list of self-assignable roles with category <Category> on this server"));
		Commands.add(new GenericCommand("rpurge", new Rpurge(), "Removes all roles with standard privilages from the server"));
		Commands.add(new GenericCommand("rdesc", new Rdesc(),"Syntax: -rdesc <Name> Return the description of a particular role"));
		Commands.add(new GenericCommand("help", (event) -> Utilities.sendPrivateMessage(event, Help), "Sends the user a PM containing this message"));
		for(GenericCommand command: Commands) {
			Help = Help + "-" + command.Name + ", " + command.desc + "\n";
		}
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
