package KokoBot.commands.commandTypes;

import java.util.function.Function;

public class GenericUnicodeEmoteShellEvent {

	public String emote;
	EmoteEventFunctional Event;
	public Function collectiveID;
	public GenericUnicodeEmoteShellEvent(String Emote, EmoteEventFunctional Event,Function F) {
		this.emote = Emote;
		this.Event = Event;
		collectiveID = F;
	}

}
