package KokoBot.commands.commandTypes.NoteCommands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class nReturn implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		
		File file = new File(KokoBot.guildUserPath.get(event.getGuild().getId()).get(event.getAuthor().getId()));
		File[] files = file.listFiles();
		String Category = "";
		try {
			Category = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		}catch(Exception e) {}
		
		//TODO add rank system
		//TODO make the output more readeable
		if(Category!="") {
			File file1 = new File(KokoBot.guildUserPath.get(event.getGuild().getId()).get(event.getAuthor().getId()));
			File[] notes = file1.listFiles();
			
			for(File note:notes) {
				List<String> lines = FileUtilities.getLines(note.getAbsolutePath());
				String Note = "";
				for(int i = 0;i<lines.size();i++) {
					Note = Note + lines.get(i)+"\n";
				}
				Utilities.sendMessage(event, Note);
			}
				
			
		} else {
			for(File file1:files) {
				File[] notes = file1.listFiles();
				for(File note:notes) {
					List<String> lines = FileUtilities.getLines(note.getAbsolutePath());
					String Note = "";
					for(int i = 0;i<lines.size();i++) {
						Note = Note + lines.get(i)+"\n";
					}
					Utilities.sendMessage(event, Note);
					
					
				}
				
			}
		}
		
		
		return null;
	}

}
