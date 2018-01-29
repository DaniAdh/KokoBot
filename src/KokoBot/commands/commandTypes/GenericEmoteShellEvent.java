package KokoBot.commands.commandTypes;

import java.util.function.Function;

import net.dv8tion.jda.core.entities.Emote;

public class GenericEmoteShellEvent {

	public Emote emote;
	EmoteEventFunctional Event;
	public Function collectiveID;
	public GenericEmoteShellEvent(Emote Emote, EmoteEventFunctional Event, Function F) {
		this.emote = Emote;
		this.Event = Event;
		collectiveID = F;
	}

}
