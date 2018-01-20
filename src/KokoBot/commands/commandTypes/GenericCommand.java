package KokoBot.commands.commandTypes;

import java.io.IOException;

import KokoBot.commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GenericCommand implements Command{

	public String Name;
	GenericEventFunctional Event;
	public String desc;
	public GenericCommand(String Name, GenericEventFunctional Event, String desc) {
		this.Name = Name;
		this.Event = Event;
		this.desc = desc;
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
