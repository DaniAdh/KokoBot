package KokoBot.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.EmoteDuality;
import KokoBot.commands.commandTypes.GenericCommand;
import KokoBot.commands.commandTypes.GenericEmoteEvent;
import KokoBot.commands.commandTypes.GenericEmoteShellEvent;
import KokoBot.commands.commandTypes.NoteCommands.addNote;
import KokoBot.commands.commandTypes.NoteCommands.nReturn;
import KokoBot.commands.commandTypes.calendarCommands.AddEvent;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarBackwards;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarFastForewards;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarForewards;
import KokoBot.commands.commandTypes.calendarCommands.ECalendarRewind;
import KokoBot.commands.commandTypes.calendarCommands.EShowevents;
import KokoBot.commands.commandTypes.calendarCommands.ShowCalendar;
import KokoBot.commands.commandTypes.calendarCommands.ShowEvents;
import KokoBot.commands.commandTypes.processCommands.Subscribe;
import KokoBot.commands.commandTypes.roleCommands.ErinfoForewards;
import KokoBot.commands.commandTypes.roleCommands.Radd;
import KokoBot.commands.commandTypes.roleCommands.Rcategories;
import KokoBot.commands.commandTypes.roleCommands.Rcommands;
import KokoBot.commands.commandTypes.roleCommands.Rcreate;
import KokoBot.commands.commandTypes.roleCommands.Rdesc;
import KokoBot.commands.commandTypes.roleCommands.Rpurge;
import KokoBot.commands.commandTypes.roleCommands.help;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class CommandManager {
	
	private static List<GenericCommand> Commands = new LinkedList<GenericCommand>();
	public static Map<String,List<GenericEmoteEvent>> emoteevents = new HashMap<String,List<GenericEmoteEvent>>();
	
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
		Commands.add(new GenericCommand("rinfo", new Rcommands(), 
				"Syntax: -rinfo, Shows the user a list of roles by category", 
				new GenericEmoteShellEvent [] {new GenericEmoteShellEvent(new EmoteDuality("▶",null,false), new ErinfoForewards(), n->"")}));
		Commands.add(new GenericCommand("rsinfo", new Rcommands(),
				"Syntax: -rsinfo, Shows the user a list of self-assignable roles by category",
				new GenericEmoteShellEvent [] {new GenericEmoteShellEvent(new EmoteDuality("▶",null,false), new ErinfoForewards(), n->"")}));
		Commands.add(new GenericCommand("rpurge", new Rpurge(), 
				"Removes all roles with standard privilages from the server"));
		Commands.add(new GenericCommand("rdesc", new Rdesc(),
				"Syntax: -rdesc <Name> Return the description of a particular role"));
		
		Commands.add(new GenericCommand("addEvent", 
				new AddEvent(), "Syntax: addEvent \"Message\" 'Role:ROLENAME'/me/everybody time_indicator\n"
						+ "Where time_indicator is one of the following: X hours, X minutes, XX:XX later, dd/MM XX:XX, XX:XX or dd/MM \\nWhere dd is the day and MM is the month"));
		
		Commands.add(new GenericCommand("Events", 
				new ShowEvents(), "Shows Events"));
		Commands.add(new GenericCommand("Calendar", 
				new ShowCalendar(), "Shows Calendar",
				new GenericEmoteShellEvent [] {new GenericEmoteShellEvent(new EmoteDuality("▶",null,false), new ECalendarForewards(), n->ECalendarForewards.calendarMessageId.get(((Guild)n).getId())), 
						new GenericEmoteShellEvent(new EmoteDuality("⏩",null,false), new ECalendarFastForewards(), n->ECalendarForewards.calendarMessageId.get(((Guild)n).getId())),
						new GenericEmoteShellEvent(new EmoteDuality("◀",null,false), new ECalendarBackwards(), n->ECalendarForewards.calendarMessageId.get(((Guild)n).getId())),
						new GenericEmoteShellEvent(new EmoteDuality("⏪",null,false), new ECalendarRewind(), n->ECalendarForewards.calendarMessageId.get(((Guild)n).getId())),
						new GenericEmoteShellEvent(new EmoteDuality("❗",null,false), new EShowevents(), n->ECalendarForewards.calendarMessageId.get(((Guild)n).getId()))}));
		Commands.add(new GenericCommand("Subscribe", 
				new Subscribe(), "Syntax: Subscribe URL \n Where URL is the URL to the videos page of the channel"));
		
		Commands.add(new GenericCommand("help", 
				new help(), "Sends the user a PM containing this message"));
		
		Commands.add(new GenericCommand("nAdd", 
				new addNote(), "Syntax: -nAdd Category \"Note\" rank*\n* means optional, rank determines the priority of the note/which notes should be at the top of the category."));
		
		Commands.add(new GenericCommand("nReturn", 
				new nReturn(), "Syntax: -nReturn Category* \n* means optional, returns all notes in a category."));
		
		
		
		for(GenericCommand command: Commands) {
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
		
		
	}
	
	
	public static void TestForEmotes(MessageReactionAddEvent EmoteEvent) throws IOException {
		if(emoteevents.get(EmoteEvent.getGuild().getId())==null) {
			emoteevents.put(EmoteEvent.getGuild().getId(), new ArrayList<GenericEmoteEvent>());
		}
		for(GenericEmoteEvent event:emoteevents.get(EmoteEvent.getGuild().getId())) {
			if(event.ListenForEmote(EmoteEvent)) {
				event.onEvent(EmoteEvent);
			}
		}	
	}
	
	
	public static void InitialiseNotes(Guild guild) {
		KokoBot.guildUserPath.put(guild.getId(), new HashMap<String,String>());
		new File(KokoBot.path+"/commands/notebookbook/"+guild.getId()+"/").mkdir();
		File[] userNotes = new File(KokoBot.path+"/commands/notebookbook/"+guild.getId()+"/").listFiles();
		for(Member user:guild.getMembers()) {
			new File(KokoBot.path+"/commands/notebookbook/"+guild.getId()+"/"+user.getUser().getId()+"/").mkdir();
		}
			
		for(File file: userNotes) {
			Map<String,String> list2 = KokoBot.guildUserPath.get(guild.getId());
			list2.put(file.getName(), file.getAbsolutePath());
			KokoBot.guildUserPath.put(guild.getId(), list2);
		}
		
	}
	
	
	
	
	
}
