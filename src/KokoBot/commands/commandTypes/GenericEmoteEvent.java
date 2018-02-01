package KokoBot.commands.commandTypes;

import java.io.IOException;
import java.util.function.Function;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class GenericEmoteEvent implements EmoteEventFunctional{

	
	public EmoteDuality emote;
	EmoteEventFunctional Event;
	String messageid;
	public Function CollectiveID;
	public GenericEmoteEvent(EmoteDuality Emote, EmoteEventFunctional Event, String messageid, Function F) {
		this.emote = Emote;
		this.Event = Event;
		this.messageid = messageid;
		this.CollectiveID = F;
	}
	
	public GenericEmoteEvent(GenericEmoteShellEvent event, String messageid) {
		this(event.emote,event.Event,messageid,obj->"");
	}
	
	
	public boolean ListenForEmote(MessageReactionAddEvent event) {
		String messageid = (CollectiveID.apply(event.getGuild()).equals(null)?this.messageid:(String) CollectiveID.apply(event.getGuild()));
		boolean rightEmote = false;
		if(emote.isEmote()) {
			rightEmote = event.getReaction().getReactionEmote().getEmote()==emote.getEmote();
		} else {
			rightEmote = event.getReactionEmote().getName().equals(emote.getEmote());
		}
		if(event.getMessageId().equals(messageid) && rightEmote) {
			return true;
		}
		
		return false;
	}


	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException {
		
		return "";
	}






}
