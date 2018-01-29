package KokoBot.commands.commandTypes.calendarCommands;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;

import KokoBot.KokoBot;
import KokoBot.Calendar.CalendarManager;
import KokoBot.Calendar.Event;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ShowCalendar implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		int month = Calendar.getInstance().get(Calendar.MONTH);
		makeCalendar(month);
		Message msg = event.getChannel().sendMessage(CalendarManager.intmonthtostring.get(month) + ":").addFile(new File(KokoBot.path + "Calendar/EventCalendar.png")).complete();

		msg.clearReactions().complete();
		msg.addReaction("⏪").complete();
		msg.addReaction("◀").complete();
		msg.addReaction("▶").complete();
		msg.addReaction("⏩").complete();
		msg.addReaction("❗").complete();
		return msg;
	}

	
	protected void makeCalendar(int month) throws IOException {
		BufferedImage large=null;
	    large = ImageIO.read(new File(KokoBot.path+"Calendar/Calendar.png"));

	    BufferedImage small=null;

	    small = ImageIO.read(new File(KokoBot.path+"Calendar/Circle.png"));

	    int w = large.getWidth();
	    int h = large.getHeight();

	    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    
	    
	    
	    Graphics g = combined.getGraphics();
	    g.drawImage(large, 0, 0, null);
	    for(Event Event: CalendarManager.Events) {
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(Event.date);
	    	for(Member member: KokoBot.guild.getMembers()) {
				if(Event.message.contains(member.getAsMention())) {continue;}
			}
	    	if(calendar.get(Calendar.MONTH)==month) {
	    		int day = calendar.get(Calendar.DAY_OF_MONTH)-1;
	    		g.drawImage(small, 10+50*(day%7), (int) (20+50*(Math.floor(day/7))), null);
	    	}
	    }

	    ImageIO.write(combined, "PNG", new File(KokoBot.path + "Calendar/EventCalendar.png"));
	}
	
	
}
