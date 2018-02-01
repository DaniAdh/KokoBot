package KokoBot.commands.commandTypes.processCommands;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Subscribe implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		String URL = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		if(!(URL.contains("https://www.youtube.com/channel/"))) {
			Utilities.sendMessage(event, "The URL needs to be leading to the channel EX: https://www.youtube.com/channel/UCCvdjsJtifsZoShjcAAHZpA");
			return null;
		}
		
		List<String> lines = FileUtilities.getLines(KokoBot.path+"/Processes/Subscriptions.txt");
		lines.forEach(n->lines.set(lines.indexOf(n),n.split("§")[0]));
		if(lines.contains(URL)) {
			Utilities.sendMessage(event, "Already Subscribed!");
			return null;
		}
		Document doc = null;
		try {
		doc = Jsoup.connect("https://www.youtube.com/feeds/videos.xml?channel_id="+URL.replaceAll("https://www.youtube.com/channel/", "")).post();
		}catch(Exception e) {
			Utilities.sendMessage(event, "Error accessing page, " + KokoBot.jda.getUsersByName("Dani", false).get(0).getAsMention() + " fix it!");
			return null;
		}
		String name = doc.toString().split("name")[1].replaceAll(">", "").replaceAll("</", "");
		FileUtilities.addToFile(KokoBot.path+"/Processes/Subscriptions.txt",URL + "§" +doc.toString().split("media:title")[1].replaceAll(">", "").replaceAll("</", "").replaceAll("\n", "").replaceAll(" ", ""));
		
		
		
		return Utilities.sendMessage(event, "Successfully Subscribed to " + name);
	}
	
}
