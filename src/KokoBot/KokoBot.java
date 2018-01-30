package KokoBot;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
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
	private static String Token = "";
	public static JDA jda;
	public static String Prefix = "-";
	public static String SelfAssignabilityCharacter = "s";
	public static String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26)+"src/KokoBot/";
	public static Guild guild;
	public static GuildController gc;
	
	public static List<CategorisedRole> roles = new LinkedList<CategorisedRole>();
	public static List<Event> events = new LinkedList<Event>();
	public static List<BackgroundProcess> backgroundProcesses = new LinkedList<BackgroundProcess>();
	
	//TODO override roles when making one with the same name
	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException, IOException {
		
		//Creates an instance of JDA, links with the bot through Token, attaches this class as an event listener
		jda = new JDABuilder(AccountType.BOT).setToken(Token).addEventListener(new KokoBot()).buildBlocking();
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		
		//Adds commands (Go to class for more detail)
		CommandManager.InitializeCommands();
		
		ProcessManager.InitialiseProcesses();

		
		try {
			CalendarManager.InitialiseEvents();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Creates Guild instance (Used to make roles on server)
		guild = jda.getGuildById("398952343435083778");
		gc = new GuildController(guild);
		
		//Writes missing roles to text file and reads from it to get categories and self-assignability
		RoleManager.InitialiseRoles();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
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
