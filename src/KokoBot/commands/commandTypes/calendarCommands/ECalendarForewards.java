package KokoBot.commands.commandTypes.calendarCommands;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import KokoBot.KokoBot;
import KokoBot.Calendar.CalendarManager;
import KokoBot.commands.commandTypes.EmoteEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class ECalendarForewards extends ShowCalendar implements EmoteEventFunctional {

	
	public static void RefreshRinfoEmotes(MessageReactionAddEvent event){
		event.getChannel().getMessageById(calendarMessageId.get(event.getGuild().getId())).complete().clearReactions().complete();
		event.getChannel().getMessageById(calendarMessageId.get(event.getGuild().getId())).complete().addReaction("⏪").complete();
		event.getChannel().getMessageById(calendarMessageId.get(event.getGuild().getId())).complete().addReaction("◀").complete();
		event.getChannel().getMessageById(calendarMessageId.get(event.getGuild().getId())).complete().addReaction("▶").complete();
		event.getChannel().getMessageById(calendarMessageId.get(event.getGuild().getId())).complete().addReaction("⏩").complete();
		event.getChannel().getMessageById(calendarMessageId.get(event.getGuild().getId())).complete().addReaction("❗").complete();
	}
	
	public static Map<String,String> calendarMessageId = new HashMap<String,String>();
	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException, InterruptedException {
		Message msg = event.getChannel().getMessageById(event.getMessageId()).complete();
		
		int month = (int)CalendarManager.stringmonthtoint.get(msg.getContentRaw().replace(":", ""))+1%12;
		
		makeCalendar(month, event.getGuild().getId());
		

		msg.delete().complete();
		calendarMessageId.put(event.getGuild().getId(), event.getChannel().sendMessage(CalendarManager.intmonthtostring.get(month) + ":").addFile(new File(KokoBot.path + "Calendar/EventCalendar.png")).complete().getId());
		RefreshRinfoEmotes(event);
		return calendarMessageId.get(event.getGuild().getId());
	}

}
