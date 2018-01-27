package KokoBot.Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;

import KokoBot.FileUtilities;
import KokoBot.Utilities;
import net.dv8tion.jda.core.entities.MessageChannel;
import KokoBot.KokoBot;

public class Event implements ActionListener{
	
	private String message;
	private MessageChannel channel;
	private Date date;
	private static DateFormat df = new SimpleDateFormat("dd/MM HH:mm");
	
	public Event(String message, Date date, MessageChannel channel) {
		this.message = message;
		this.channel = channel;
		this.date = date;
		new Timer((int)(new Date().getTime() - date.getTime()), this);
	}
	
	public void Alert() {
		Utilities.sendMessage(channel, message);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Alert();
	}

	public static Event fromString(String a) throws ParseException {
		
		return new Event(Utilities.getPaddedSubstringFromMessage(a, "\"", ""), df.parse(Utilities.getPaddedSubstringFromMessage(a, "\'", "")), KokoBot.guild.getTextChannelById(Utilities.SplitMessageAndGetIndex(a, " ", 2)));
	}
	
	public String toString() {
		return FileUtilities.makeLine(new String[] {message, df.format(date), channel.getId()}, new String[] {"\"", "\'",""});
		
	}
	
	
}
