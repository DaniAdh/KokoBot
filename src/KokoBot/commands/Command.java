package KokoBot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
	
	boolean ListenForEvent(String Message);
	void onEvent(MessageReceivedEvent Message);
}
