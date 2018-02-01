package KokoBot;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.security.auth.login.LoginException;

import KokoBot.Calendar.CalendarManager;
import KokoBot.Calendar.Event;
import KokoBot.Processes.BackgroundProcess;
import KokoBot.Processes.ProcessManager;
import KokoBot.Roles.CategorisedRole;
import KokoBot.Roles.RoleManager;
import KokoBot.commands.CommandManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

public class KokoBot extends ListenerAdapter{	
	private static String Token = "Mzk4OTQxNzUwMjMzOTIzNTg0.DTKGmw.3Rpmwb5CleechxS2dV3CSstChws";
	public static JDA jda;
	public static String Prefix = "-";
	public static String SelfAssignabilityCharacter = "s";
	public static String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26)+"src/KokoBot/";
	public static Map<String,Guild> guild = new HashMap<String,Guild>();
	public static Map<String,GuildController> gc = new HashMap<String,GuildController>();
	public static Map<String,Map<String,String>> guildUserPath = new HashMap<String,Map<String,String>>();
	
	public static Map<String,List<CategorisedRole>> roles = new HashMap<String,List<CategorisedRole>>();
	public static Map<String,List<Event>> events = new HashMap<String,List<Event>>();
	public static List<BackgroundProcess> backgroundProcesses = new LinkedList<BackgroundProcess>();
	

	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException, IOException {
		//Creates an instance of JDA, links with the bot through Token, attaches this class as an event listener
		jda = new JDABuilder(AccountType.BOT).setToken(Token).addEventListener(new KokoBot()).buildBlocking();
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		
		//Adds commands (Go to class for more detail)
		CommandManager.InitializeCommands();
		
		
		
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		if(guild.get(event.getGuild().getId()) == null) {
			guild.put(event.getGuild().getId(), event.getGuild());
			gc.put(event.getGuild().getId(), new GuildController(event.getGuild()));
		
			try {
				CalendarManager.InitialiseEvents(event.getGuild());
				RoleManager.InitialiseRoles(event.getGuild());
				ProcessManager.InitialiseProcesses(event.getGuild());
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			CommandManager.InitialiseNotes(event.getGuild());
			
		}
		if(event.getAuthor().isBot() || !event.getChannel().getName().equals("bot")) {return;}
		try {
			CommandManager.TestForCommands(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	
	
	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		if(event.getMember().getUser().isBot() || !event.getChannel().getName().equals("bot")) {return;}
		try {
			CommandManager.TestForEmotes(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
