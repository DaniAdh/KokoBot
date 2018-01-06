package KokoBot.commands.commandTypes;

import KokoBot.commands.Command;
import KokoBot.commands.commandTypes.ReplyEventFunctional;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ReplyCommand implements Command{

	String Name;
	ReplyEventFunctional Event;
	public ReplyCommand(String Name, ReplyEventFunctional Event) {
		this.Name = Name;
		this.Event = Event;
	}
	
	
	@Override
	public boolean ListenForEvent(String Input) {
		return Input.startsWith(Name);
	}

	@Override
	public void onEvent(MessageReceivedEvent Message) {
		Event.onEvent(Message);
		
	}


}
