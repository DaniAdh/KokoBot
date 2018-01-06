package KokoBot.commands.commandTypes;

import java.io.IOException;

import KokoBot.commands.Command;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GenericCommand implements Command{

	String Name;
	GenericEventFunctional Event;
	public GenericCommand(String Name, GenericEventFunctional Event) {
		this.Name = Name;
		this.Event = Event;
	}
	
	
	@Override
	public boolean ListenForEvent(String Input) {
		return Input.startsWith(Name);
	}

	@Override
	public void onEvent(MessageReceivedEvent Message) throws IOException {
		Event.onEvent(Message);
		 
	}


}
