package KokoBot.commands.commandTypes;

import java.io.IOException;

import KokoBot.commands.Command;
import KokoBot.commands.CommandManager;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GenericCommandWithUnicodeEmote implements Command{

	
	public GenericUnicodeEmoteShellEvent[] emotelisteners;
	
	public String Name;
	GenericEventFunctional Event;
	public String desc;
	public GenericCommandWithUnicodeEmote(String Name, GenericEventFunctional Event, String desc, GenericUnicodeEmoteShellEvent[] emotelisteners) {
		this.Name = Name;
		this.Event = Event;
		this.desc = desc;
		this.emotelisteners = emotelisteners;
	}
	




	@Override
	public boolean ListenForEvent(String Input) {
		return Input.startsWith(Name);
	}

	@Override
	public void onEvent(MessageReceivedEvent Message) throws IOException {
		Message msg = Event.onEvent(Message);
		for(GenericUnicodeEmoteShellEvent shell:emotelisteners) {
			CommandManager.unicodeemoteevents.add(new GenericUnicodeEmoteEvent(shell.emote, shell.Event,msg.getId(), shell.collectiveID));
		}
		 
	}


}
