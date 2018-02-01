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
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;

public class Event implements ActionListener{
	
	public String message;
	public MessageChannel channel;
	public Date date;
	private static DateFormat df = new SimpleDateFormat("dd/MM kk:mm");
	private Timer timer;
	private Guild guild;
	
	public Event(String message, Date date, MessageChannel channel, Guild guild) {
		this.message = message;
		this.channel = channel;
		this.date = date;
		this.guild = guild;
		timer = new Timer((int)(date.getTime() - new Date().getTime()), this);
		timer.start();
	}
	
	public void Alert() {
		Utilities.sendMessage(channel, message.replaceAll("§", " "));
		List list2 = CalendarManager.Events.get(guild.getId());
		list2.remove(this);
		CalendarManager.Events.put(guild.getId(),list2);
		List<String> events = new ArrayList<String>();
		List list3 = CalendarManager.Events.get(guild.getId());
		list3.forEach(num -> events.add(num.toString()));
		CalendarManager.Events.put(guild.getId(),list3);
		try {
			FileUtilities.overrideFile(KokoBot.path+"Calendar/Events/"+guild.getId()+".txt", events);
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
		TextChannel channel = KokoBot.jda.getTextChannelById(Utilities.SplitMessageAndGetIndex(a, " ", (int)(2+chars.filter(num -> num ==' ').count())));
		return new Event(message, CalendarManager.DatefromString(datestr).getTime(), channel, channel.getGuild());
	}
	
	public String toString() {
		return FileUtilities.makeLine(new String[] {message, df.format(date), String.valueOf(channel.getIdLong())}, new String[] {"\"", "\'",""});
		
	}
	
	
}
