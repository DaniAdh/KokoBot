package KokoBot.commands.commandTypes.NoteCommands;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class addNote implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		String Category = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		String pathtoFolder = KokoBot.path+"/commands/notebookbook/"+event.getGuild().getId()+"/"+event.getAuthor().getId()+"/"+Category+"/";
		String Rank = Utilities.SplitMessageAndGetIndex(event, "\"", 2).replaceAll(" ", "");
		String Note = Utilities.SplitMessageAndGetIndex(event, "\"", 1);
		int rank = 0;
		try {
			rank = Integer.valueOf(Rank);
		}catch(NumberFormatException e) {
			//TODO remove if works
			e.printStackTrace();
		}
		new File(pathtoFolder).mkdir();
		File file = new File(pathtoFolder+String.valueOf(rank)+"-"+event.getMessage().getCreationTime().toLocalTime().hashCode()+".txt");
		file.createNewFile();
		FileUtilities.overrideFile(file.getAbsolutePath(), Arrays.asList(Note));
		
		
		return Utilities.sendMessage(event, "Added Note to Category " + Category + " for User " + event.getAuthor().getName() + "!");
		
	}
	
}
