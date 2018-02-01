package KokoBot.commands.commandTypes;

import java.util.function.Function;

public class GenericEmoteShellEvent {

	public EmoteDuality emote;
	EmoteEventFunctional Event;
	public Function collectiveID;
	public GenericEmoteShellEvent(EmoteDuality Emote, EmoteEventFunctional Event, Function F) {
		this.emote = Emote;
		this.Event = Event;
		collectiveID = F;
	}

}
