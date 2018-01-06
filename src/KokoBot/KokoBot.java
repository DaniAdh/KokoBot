package KokoBot;
import javax.security.auth.login.LoginException;

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
	private static String Token = "Mzk4OTQxNzUwMjMzOTIzNTg0.DTF3Sg.nWBqsqWHkVtg1r7jxG5UlM1weVU";
	public static JDA jda;
	public static String Prefix = "!";
	
	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		jda = new JDABuilder(AccountType.BOT).setToken(Token).addEventListener(new KokoBot()).buildBlocking();
		CommandManager.InitializeCommands();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		Guild guild = event.getGuild();
		GuildController gc = new GuildController(guild);
		if(event.getAuthor().isBot()) {return;}
		//System.out.println(guild.getRoles());
		//gc.addSingleRoleToMember(guild.getMember(event.getAuthor()), guild.getRoleById("399132363809882112")).complete();
		CommandManager.TestForCommands(event);
		
		
	}
	
	

}
