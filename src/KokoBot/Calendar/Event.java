package KokoBot.Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.Timer;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Event implements ActionListener{
	
	public String message;
	public MessageChannel channel;
	public Date date;
	private static DateFormat df = new SimpleDateFormat("dd/MM kk:mm");
	private Timer timer;
	
	public Event(String message, Date date, MessageChannel channel) {
		this.message = message;
		this.channel = channel;
		this.date = date;
		timer = new Timer((int)(date.getTime() - new Date().getTime()), this);
		timer.start();
	}
	
	public void Alert() {
		Utilities.sendMessage(channel, message.replaceAll("§", " "));
		CalendarManager.Events.remove(this);
		List<String> events = new ArrayList<String>();
		CalendarManager.Events.forEach(num -> events.add(num.toString()));
		try {
			FileUtilities.overrideFile(KokoBot.path+"Calendar/Events.txt", events);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Alert();
		timer.stop();
	}

	public static Event fromString(String a) throws ParseException {
		
		String message = Utilities.getPaddedSubstringFromMessage(a, "\"", "");
		String datestr = Utilities.getPaddedSubstringFromMessage(a, "\'", "");
		IntStream chars = (message+datestr).chars();
		return new Event(message, CalendarManager.DatefromString(datestr).getTime(), KokoBot.jda.getTextChannelById(Utilities.SplitMessageAndGetIndex(a, " ", (int)(2+chars.filter(num -> num ==' ').count()))));
	}
	
	public String toString() {
		return FileUtilities.makeLine(new String[] {message, df.format(date), String.valueOf(channel.getIdLong())}, new String[] {"\"", "\'",""});
		
	}
	
	
}
