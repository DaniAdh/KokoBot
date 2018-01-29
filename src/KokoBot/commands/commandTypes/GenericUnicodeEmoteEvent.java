package KokoBot.commands.commandTypes;

import java.io.IOException;
import java.util.function.Function;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class GenericUnicodeEmoteEvent implements EmoteEventFunctional{

	
	public String emote;
	EmoteEventFunctional Event;
	String messageid;
	public Function CollectiveID;
	public GenericUnicodeEmoteEvent(String Emote, EmoteEventFunctional Event, String messageid, Function F) {
		this.emote = Emote;
		this.Event = Event;
		this.messageid = messageid;
		this.CollectiveID = F;
	}
	
	public GenericUnicodeEmoteEvent(GenericUnicodeEmoteShellEvent event, String messageid) {
		this(event.emote,event.Event,messageid,num->"");
	}
	
	
	public boolean ListenForEmote(MessageReactionAddEvent event) {
		if(event.getMessageId().equals((CollectiveID.apply("").equals("")?messageid:CollectiveID.apply("")))&&event.getReactionEmote().getName().equals(emote)) {
			return true;
		}
		return false;
	}


	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException {
		
		try {
			messageid = Event.onEvent(event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return messageid;
	}






}
