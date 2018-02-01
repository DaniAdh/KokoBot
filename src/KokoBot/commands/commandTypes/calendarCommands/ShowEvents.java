package KokoBot.commands.commandTypes.calendarCommands;

import java.io.IOException;
import java.text.SimpleDateFormat;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Calendar.CalendarManager;
import KokoBot.Calendar.Event;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
public class ShowEvents implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		Utilities.sendMessage(event, "There are " + CalendarManager.Events.size() + " Events:");
		for(Event Event: CalendarManager.Events.get(event.getGuild().getId())) {
			for(Member member: KokoBot.guild.get(event.getGuild().getId()).getMembers()) {
			if(Event.message.contains(member.getAsMention())) {continue;}
			}
			String to = "";
			try {
				to = KokoBot.jda.getRoleById(Event.message.split("§")[0].replace("<", "").replace(">", "").replace("@", "").replace("&", "")).getName();
			}catch(Exception e) {
				to = KokoBot.jda.getUserById(Event.message.split("§")[0].replace("<", "").replace(">", "").replace("@", "")).getName();
			}
			Utilities.sendMessage(event, new SimpleDateFormat("dd/MM/yyyy kk:mm").format(Event.date) + " \"" + Event.message.split("§")[1] + "\" to " + to);
		}
		return null;
	}

}
