package KokoBot.commands.commandTypes;

import java.io.IOException;
import java.util.function.Function;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class GenericEmoteEvent implements EmoteEventFunctional{

	
	public Emote emote;
	EmoteEventFunctional Event;
	String messageid;
	public Function CollectiveID;
	public GenericEmoteEvent(Emote Emote, EmoteEventFunctional Event, String messageid, Function F) {
		this.emote = Emote;
		this.Event = Event;
		this.messageid = messageid;
		this.CollectiveID = F;
	}
	
	public GenericEmoteEvent(GenericEmoteShellEvent event, String messageid) {
		this(event.emote,event.Event,messageid,obj->"");
	}
	
	
	public boolean ListenForEmote(MessageReactionAddEvent event) {
		if((event.getMessageId().equals((CollectiveID.apply("").equals("")?messageid:CollectiveID.apply(""))))&&event.getReaction().getReactionEmote().getEmote()==emote) {
			return true;
		}
		return false;
	}


	@Override
	public String onEvent(MessageReactionAddEvent event) throws IOException {
		
		return "";
	}






}
