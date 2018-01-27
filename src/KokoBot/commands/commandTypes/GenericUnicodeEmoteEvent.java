package KokoBot.commands.commandTypes;

import java.io.IOException;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class GenericUnicodeEmoteEvent implements EmoteEventFunctional{

	
	public String emote;
	EmoteEventFunctional Event;
	String messageid;
	public GenericUnicodeEmoteEvent(String Emote, EmoteEventFunctional Event, String messageid) {
		this.emote = Emote;
		this.Event = Event;
		this.messageid = messageid;
	}
	
	public GenericUnicodeEmoteEvent(GenericUnicodeEmoteShellEvent event, String messageid) {
		this(event.emote,event.Event,messageid);
	}
	
	
	public boolean ListenForEmote(MessageReactionAddEvent event) {
		if(event.getMessageId().equals(this.messageid)&&event.getReactionEmote().getName().equals(emote)) {
			return true;
		}
		return false;
	}


	@Override
	public void onEvent(MessageReactionAddEvent event) throws IOException {
		
		Event.onEvent(event);
	}






}
