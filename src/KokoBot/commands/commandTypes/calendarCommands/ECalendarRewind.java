package KokoBot.commands.commandTypes.calendarCommands;

import java.io.File;
import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Calendar.CalendarManager;
import KokoBot.commands.commandTypes.EmoteEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class ECalendarRewind extends ShowCalendar implements EmoteEventFunctional {

	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException, InterruptedException {
		Message msg = event.getChannel().getMessageById(event.getMessageId()).complete();
		
		int month = 0;
		
		makeCalendar(month);

		msg.delete().complete();
		ECalendarForewards.calendarMessageId = event.getChannel().sendMessage(CalendarManager.intmonthtostring.get(month) + ":").addFile(new File(KokoBot.path + "Calendar/EventCalendar.png")).complete().getId();
		ECalendarForewards.RefreshRinfoEmotes(event);
		return ECalendarForewards.calendarMessageId;	
		}

}
