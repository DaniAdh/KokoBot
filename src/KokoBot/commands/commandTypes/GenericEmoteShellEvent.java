package KokoBot.commands.commandTypes;

import net.dv8tion.jda.core.entities.Emote;

public class GenericEmoteShellEvent {

	public Emote emote;
	EmoteEventFunctional Event;
	public GenericEmoteShellEvent(Emote Emote, EmoteEventFunctional Event) {
		this.emote = Emote;
		this.Event = Event;
	}

}
