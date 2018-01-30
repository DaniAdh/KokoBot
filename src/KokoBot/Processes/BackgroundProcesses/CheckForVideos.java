package KokoBot.Processes.BackgroundProcesses;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.Processes.BackgroundProcess;

public class CheckForVideos extends BackgroundProcess{

	public CheckForVideos(int timemillis) {
		super(timemillis);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("test");
		List<String> lines = new ArrayList<String>();
		try {
			lines = FileUtilities.getLines(KokoBot.path+"Processes/Subscriptions.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Map<String,Integer> timetoint = new HashMap<String,Integer>();
		timetoint.put("second", 0);
		timetoint.put("minute", 1);
		timetoint.put("hour", 2);
		timetoint.put("week", 3);
		timetoint.put("month", 4);
		
		
		for(String line:lines) {
			//URL§Title
			try {
				String oldtitle = line.split("§")[1];
				String URL = line.split("§")[0];
				Document doc;
				try {
					doc = Jsoup.connect("https://www.youtube.com/feeds/videos.xml?channel_id="+URL.replaceAll("https://www.youtube.com/channel/", "")).post();
				}catch(Exception e) {
					e.printStackTrace();
					return;
				}
				String name = doc.toString().split("name")[1].replaceAll(">", "").replaceAll("</", "");
				String title = doc.toString().split("media:title")[1].replaceAll(">", "").replaceAll("</", "").replaceAll("\n", "").replaceAll(" ", "");
				
				

				if(!title.equals(oldtitle)) {
					Utilities.sendMessage(KokoBot.guild.getTextChannelsByName("Videos", true).get(0), "New Video From " + name + "! \n https://www.youtube.com"); //TODO);
					List<String> newlines = FileUtilities.getLines(KokoBot.path + "Processes/Subscriptions.txt");
					newlines.remove(line);
					newlines.add(line.replace(oldtitle, title));
					FileUtilities.overrideFile(KokoBot.path + "Processes/Subscriptions.txt", newlines);
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	

}
