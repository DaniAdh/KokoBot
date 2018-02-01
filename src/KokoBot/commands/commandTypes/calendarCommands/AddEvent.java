package KokoBot.commands.commandTypes.calendarCommands;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Calendar.CalendarManager;
import KokoBot.Calendar.Event;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AddEvent  implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length<3+1) {
			Utilities.sendMessage(event, "Not enough arguments, type -help to get syntax");
			return null;
		}
		
		String message = Utilities.getPaddedSubstringFromMessage(event, "\"", "No Message Inputted");
		String who = "";
		if(event.getMessage().getContentRaw().contains("\'")) {
			who=Utilities.getPaddedSubstringFromMessage(event, "\'", "me");
		}else {
			who=Utilities.SplitMessageAndGetIndex(event, " ", (int) (2+(message).chars().filter(num -> num == ' ').count()));
		}
		String date = Utilities.SplitMessageAndGetIndex(event, " ", (int) (3+(message+who).chars().filter(num -> num == ' ').count()));
		try {
			date += " " + Utilities.SplitMessageAndGetIndex(event, " ", (int) (4+(message+who).chars().filter(num -> num == ' ').count()));
		}catch(Exception e) {}
		MessageChannel channel = event.getChannel();
		boolean touser = who.toUpperCase().equals("ME");
		//AddEvent "Message" <Name/me/everybody> X hours/X minutes/XX:XX later/ dd/MM XX:XX / XX:XX / dd/MM
		if(!touser && !event.getGuild().getMembersWithRoles(Utilities.findRoleWithName(KokoBot.roles.get(event.getGuild().getId()), "Event Manager").role).contains(Utilities.getMember(event))) {
			Utilities.sendMessage(event, ("You do not have the \"Event Manager\" role!"));
			return null;
		}
		
		
		if(!(date.contains(":")||date.contains("/")||date.contains("hours")|date.contains("minute")||date.contains("later"))){
			Utilities.sendMessage(event, "Invalid date, Use one of the following: X hours, X minutes, XX:XX later, dd/MM XX:XX, XX:XX or dd/MM \nWhere dd is the day and MM is the month");
			return null;
		}
		

		
		Calendar cal = CalendarManager.DatefromString(date);
		String mention = "";
		if(touser) {
			mention = event.getAuthor().getAsMention();
		}
		if(who=="everybody") {
			mention = Utilities.getRoleByName("@everybody", event.getGuild()).getAsMention();
		}
		if(who.contains("Role:")) {
			mention = Utilities.getRoleByName(who.split("Role:")[1], event.getGuild()).getAsMention();
		}
		
		CalendarManager.AddEvent(new Event(mention + "§" + message, cal.getTime(), channel, event.getGuild()),event);
		
		
		if(touser) {who=event.getAuthor().getName();}
		return Utilities.sendMessage(event, ("Created event to send message: " + message + " to " + who + " at time: " + new SimpleDateFormat("dd/MM/yyyy kk:mm").format(cal.getTime())));
		
	}

}
