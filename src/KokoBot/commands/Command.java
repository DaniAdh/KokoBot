package KokoBot.commands;

import java.io.IOException;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
	
	boolean ListenForEvent(String Message);
	void onEvent(MessageReceivedEvent Message) throws IOException;
}
