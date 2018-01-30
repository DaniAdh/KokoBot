package KokoBot.commands.commandTypes.calendarCommands;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Calendar.CalendarManager;
import KokoBot.Calendar.Event;
import KokoBot.commands.commandTypes.EmoteEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class EShowevents implements EmoteEventFunctional {

	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException, InterruptedException {
		Message msg = event.getChannel().getMessageById(event.getMessageId()).complete();
		
		int month = (int)CalendarManager.stringmonthtoint.get(msg.getContentRaw().replace(":", ""));
		int i = 0;
		for(Event Event: CalendarManager.Events) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(Event.date);
			if(cal.get(Calendar.MONTH)!=month) {continue;}
			String to = "";
			try {
				to = KokoBot.jda.getRoleById(Event.message.split("§")[0].replace("<", "").replace(">", "").replace("@", "").replace("&", "")).getName();
			}catch(Exception e) {
				to = KokoBot.jda.getUserById(Event.message.split("§")[0].replace("<", "").replace(">", "").replace("@", "")).getName();
				continue;
			}
			Utilities.sendMessage(event.getChannel(), new SimpleDateFormat("dd/MM/yyyy kk:mm").format(Event.date) + " \"" + Event.message.split("§")[1] + "\" to " + to);
			i++;
		}
		if(i==0) {
			Utilities.sendMessage(event.getChannel(), "No Event Found");
		}
		return null;
	}

}
