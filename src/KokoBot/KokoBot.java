package KokoBot;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.login.LoginException;

import KokoBot.Roles.CategorisedRole;
import KokoBot.Roles.RoleManager;
import KokoBot.commands.CommandManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

public class KokoBot extends ListenerAdapter{	
	private static String Token = "Mzk4OTQxNzUwMjMzOTIzNTg0.DTKGmw.3Rpmwb5CleechxS2dV3CSstChws";
	public static JDA jda;
	public static String Prefix = "-";
	public static Guild guild;
	public static GuildController gc;
	
	public static List<CategorisedRole> roles = new LinkedList<CategorisedRole>();
	
	
	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException, IOException {
		jda = new JDABuilder(AccountType.BOT).setToken(Token).addEventListener(new KokoBot()).buildBlocking();
		CommandManager.InitializeCommands();
		guild = jda.getGuildById("398952343435083778");
		gc = new GuildController(guild);
		RoleManager.InitialiseRoles();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		if(event.getAuthor().isBot() || !event.getChannel().getName().equals("bot")) {return;}
		System.out.println(event.getGuild());
		//gc.addSingleRoleToMember(guild.getMember(event.getAuthor()), guild.getRoleById("399132363809882112")).complete();
		CommandManager.TestForCommands(event);
		
		
	}
	
	

}
