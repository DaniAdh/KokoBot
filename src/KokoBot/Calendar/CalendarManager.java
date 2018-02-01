package KokoBot.Calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CalendarManager {

	public static HashMap<Integer,String> intmonthtostring = new HashMap<Integer, String>();
	public static Map<String, Integer> stringmonthtoint;
	
	public static Map<String,List<Event>> Events = new HashMap<String,List<Event>>();
	public static void InitialiseEvents(Guild guild) throws ParseException, IOException {
		intmonthtostring.put(0, "January");
		intmonthtostring.put(1, "February");
		intmonthtostring.put(2, "March");
		intmonthtostring.put(3, "April");
		intmonthtostring.put(4, "May");
		intmonthtostring.put(5, "June");
		intmonthtostring.put(6, "July");
		intmonthtostring.put(7, "August");
		intmonthtostring.put(8, "September");
		intmonthtostring.put(9, "October");
	    intmonthtostring.put(10, "November");
	    intmonthtostring.put(11, "December");
	    stringmonthtoint = 
			    CalendarManager.intmonthtostring.entrySet().stream()
			    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
	    File file = new File(KokoBot.path+"Calendar/Events/"+guild.getId()+".txt");
	    
	    file.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(KokoBot.path+"Calendar/Events/"+guild.getId()+".txt"));
		Iterator<String> events = reader.lines().iterator();
		List<String> ActiveEvents = new ArrayList<String>();
		
		if(Events.get(guild.getId())==null) {
			Events.put(guild.getId(), new LinkedList<Event>());
		}
		
		while(events.hasNext()) {
			String a = events.next();
			if(Utilities.getPaddedSubstringFromMessage(a, "\'", "")=="") {continue;}
			if(CalendarManager.DatefromString(Utilities.getPaddedSubstringFromMessage(a, "\'", "")).getTime().after(Calendar.getInstance().getTime())) {
				ActiveEvents.add(a);
			}
			Events.put(guild.getId(),Utilities.add(Events.get(guild.getId()), Event.fromString(a)));
			

		}
		
		reader.close();
		FileUtilities.overrideFile(KokoBot.path+"Calendar/Events/"+guild.getId()+".txt", ActiveEvents);
		
	}
	
	public static void AddEvent(Event event, MessageReceivedEvent event2) {
		try {
			FileUtilities.addToFile(KokoBot.path+"Calendar/Events/"+event2.getGuild().getId()+".txt", event.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Events.put(event2.getGuild().getId(),Utilities.add(Events.get(event2.getGuild().getId()), event));
		
	}

	public static Calendar DatefromString(String date) {
		Calendar cal = Calendar.getInstance();
		if(date.contains("hours")) {
			cal.add(Calendar.HOUR_OF_DAY, Integer.valueOf(date.split(" ")[0]));
		}else if(date.contains("minute")) {
			cal.add(Calendar.MINUTE, Integer.valueOf(date.split(" ")[0]));
		}else if(date.contains("later")) {
			cal.add(Calendar.HOUR_OF_DAY, Integer.valueOf(date.split(" ")[0].split(":")[0]));
			cal.add(Calendar.MINUTE, Integer.valueOf(date.split(" ")[0].split(":")[1]));
		}else if(date.contains("/")&&date.contains(":")) {
			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date.split(" ")[0].split("/")[0]));
			cal.set(Calendar.MONTH, Integer.valueOf(date.split(" ")[0].split("/")[1])-1);
			cal.set(Calendar.HOUR, Integer.valueOf(date.split(" ")[1].split(":")[0]));
			cal.set(Calendar.MINUTE, Integer.valueOf(date.split(" ")[1].split(":")[1]));
		}else if(date.contains(":")) {
			cal.set(Calendar.HOUR, Integer.valueOf(date.split(":")[0]));
			cal.set(Calendar.MINUTE, Integer.valueOf(date.split(":")[1]));
		}else if(date.contains("/")) {
			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date.split("/")[0]));
			cal.set(Calendar.MONTH, Integer.valueOf(date.split("/")[1])-1);
		}
		return cal;
	}
	
}
