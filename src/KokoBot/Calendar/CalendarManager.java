package KokoBot.Calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import KokoBot.KokoBot;

public class CalendarManager {

	public static void InitialiseEvents() throws ParseException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(KokoBot.path+"Calendar/Events.txt"));
		Iterator<String> Roles = reader.lines().iterator();
		List<Event> Events = new LinkedList<Event>();
		
		while(Roles.hasNext()) {
			String a = Roles.next();
			Events.add(Event.fromString(a));
		}
		
		reader.close();
		
	}
	
}
