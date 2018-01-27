package KokoBot.commands.commandTypes;

import java.io.IOException;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class GenericEmoteEvent implements EmoteEventFunctional{

	
	public Emote emote;
	EmoteEventFunctional Event;
	String messageid;
	public GenericEmoteEvent(Emote Emote, EmoteEventFunctional Event, String messageid) {
		this.emote = Emote;
		this.Event = Event;
		this.messageid = messageid;
	}
	
	public GenericEmoteEvent(GenericEmoteShellEvent event, String messageid) {
		this(event.emote,event.Event,messageid);
	}
	
	
	public boolean ListenForEmote(MessageReactionAddEvent event) {
		if(event.getMessageId()==this.messageid&&event.getReaction().getReactionEmote().getEmote()==emote) {
			return true;
		}
		return false;
	}


	@Override
	public void onEvent(MessageReactionAddEvent event) throws IOException {
		
		
	}






}
