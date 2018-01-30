package KokoBot.commands;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericCommand;
import KokoBot.commands.commandTypes.GenericCommandWithUnicodeEmote;
import KokoBot.commands.commandTypes.GenericEmoteEvent;
import KokoBot.commands.commandTypes.GenericUnicodeEmoteEvent;
import KokoBot.commands.commandTypes.GenericUnicodeEmoteShellEvent;
import KokoBot.commands.commandTypes.calendarCommands.AddEvent;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarBackwards;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarFastForewards;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarForewards;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarRewind;
import KokoBot.commands.commandTypes.calendarCommands.EShowevents;
import KokoBot.commands.commandTypes.calendarCommands.ShowCalendar;
import KokoBot.commands.commandTypes.calendarCommands.ShowEvents;
import KokoBot.commands.commandTypes.roleCommands.ErinfoForewards;
import KokoBot.commands.commandTypes.roleCommands.Radd;
import KokoBot.commands.commandTypes.roleCommands.Rcategories;
import KokoBot.commands.commandTypes.roleCommands.Rcommands;
import KokoBot.commands.commandTypes.roleCommands.Rcreate;
import KokoBot.commands.commandTypes.roleCommands.Rdesc;
import KokoBot.commands.commandTypes.roleCommands.Rpurge;
import KokoBot.commands.commandTypes.roleCommands.help;
import KokoBot.commands.processCommands.Subscribe;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class CommandManager {
	
	private static List<GenericCommand> Commands = new LinkedList<GenericCommand>();
	private static List<GenericCommandWithUnicodeEmote> CommandsWithUnicode = new LinkedList<GenericCommandWithUnicodeEmote>();
	public static List<GenericEmoteEvent> emoteevents = new LinkedList<GenericEmoteEvent>();
	public static List<GenericUnicodeEmoteEvent> unicodeemoteevents = new LinkedList<GenericUnicodeEmoteEvent>();
	
	public static String Help = "";
	
	public static void InitializeCommands() {
		Commands.add(new GenericCommand("Ping", (event) -> Utilities.sendMessage(event, "Pong"),"Replies with \"Pong\""));
		Commands.add(new GenericCommand("rcreate", new Rcreate(),
				"Syntax: -rcreate <Name> <Category> #<Hex value for color>* \"description\"*, creates a non-self-assignable role with these attributes. * means optional"));
		Commands.add(new GenericCommand("rscreate", new Rcreate(),
				"Syntax: -rscreate <Name> <Category> #<Hex value for color>* \"description\"*,"
				+ " creates a self-assignable role with these attributes. * means optional \n "
				+ "NOTE: If the role already exists, these commands change its attributes, but due to how discord manages roles, colors of roles can only be changed manually"));
		Commands.add(new GenericCommand("radd", new Radd(),
				"Syntax: -radd <Name>, adds the role <Name> to the user"));
		Commands.add(new GenericCommand("rcategories", new Rcategories(),
				"Replies with a list of categories on this server"));
		CommandsWithUnicode.add(new GenericCommandWithUnicodeEmote("rinfo", new Rcommands(), 
				"Syntax: -rinfo, Shows the user a list of roles by category", 
				new GenericUnicodeEmoteShellEvent [] {new GenericUnicodeEmoteShellEvent("▶", new ErinfoForewards(), n->"")}));
		CommandsWithUnicode.add(new GenericCommandWithUnicodeEmote("rsinfo", new Rcommands(),
				"Syntax: -rsinfo, Shows the user a list of self-assignable roles by category",
				new GenericUnicodeEmoteShellEvent [] {new GenericUnicodeEmoteShellEvent("▶", new ErinfoForewards(), n->"")}));
		Commands.add(new GenericCommand("rpurge", new Rpurge(), 
				"Removes all roles with standard privilages from the server"));
		Commands.add(new GenericCommand("rdesc", new Rdesc(),
				"Syntax: -rdesc <Name> Return the description of a particular role"));
		
		Commands.add(new GenericCommand("addEvent", 
				new AddEvent(), "Syntax: addEvent \"Message\" 'Role:ROLENAME'/me/everybody time_indicator\n"
						+ "Where time_indicator is one of the following: X hours, X minutes, XX:XX later, dd/MM XX:XX, XX:XX or dd/MM \\nWhere dd is the day and MM is the month"));
		
		Commands.add(new GenericCommand("Events", 
				new ShowEvents(), "Shows Events"));
		CommandsWithUnicode.add(new GenericCommandWithUnicodeEmote("Calendar", 
				new ShowCalendar(), "Shows Calendar",
				new GenericUnicodeEmoteShellEvent [] {new GenericUnicodeEmoteShellEvent("▶", new ECalendarForewards(), n->ECalendarForewards.calendarMessageId), 
						new GenericUnicodeEmoteShellEvent("⏩", new ECalendarFastForewards(), n->ECalendarForewards.calendarMessageId),
						new GenericUnicodeEmoteShellEvent("◀", new ECalendarBackwards(), n->ECalendarForewards.calendarMessageId),
						new GenericUnicodeEmoteShellEvent("⏪", new ECalendarRewind(), n->ECalendarForewards.calendarMessageId),
						new GenericUnicodeEmoteShellEvent("❗", new EShowevents(), n->ECalendarForewards.calendarMessageId)}));
		Commands.add(new GenericCommand("Subscribe", 
				new Subscribe(), "Syntax: Subscribe URL \n Where URL is the URL to the videos page of the channel"));
		
		Commands.add(new GenericCommand("help", 
				new help(), "Sends the user a PM containing this message"));
		for(GenericCommand command: Commands) {
			Help = Help + "-" + command.Name + ", " + command.desc + "\n\n";
		}
		for(GenericCommandWithUnicodeEmote command: CommandsWithUnicode) {
			Help = Help + "-" + command.Name + ", " + command.desc + "\n\n";
		}
		Help = "```"+Help+"```";
	}
	
	public static void TestForCommands(MessageReceivedEvent MessageEvent) throws IOException {
		String Message = MessageEvent.getMessage().getContentDisplay();
		if(!Message.contains(KokoBot.Prefix)) {return;}
		
		for(GenericCommand command:Commands) {
			if(command.ListenForEvent(Message.substring(1, Message.length()))) {
				command.onEvent(MessageEvent);
			}
		}	
		
		for(GenericCommandWithUnicodeEmote command:CommandsWithUnicode) {
			if(command.ListenForEvent(Message.substring(1, Message.length()))) {
				command.onEvent(MessageEvent);
			}
		}
	}
	
	
	public static void TestForEmotes(MessageReactionAddEvent EmoteEvent) throws IOException {
		for(GenericEmoteEvent event:emoteevents) {
			if(event.ListenForEmote(EmoteEvent)) {
				event.onEvent(EmoteEvent);
			}
		}	
		for(GenericUnicodeEmoteEvent event:unicodeemoteevents) {
			if(event.ListenForEmote(EmoteEvent)) {
				event.onEvent(EmoteEvent);
			}
		}
	}
	
	
	
	
	
	
}
