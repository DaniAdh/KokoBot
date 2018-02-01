package KokoBot.commands.commandTypes;

import net.dv8tion.jda.core.entities.Emote;

public class EmoteDuality {
	
	private String stringemote;
	private Emote emote;
	private boolean isemote;
	public EmoteDuality(String stringemote,Emote emote, boolean isemote) {
		this.stringemote = stringemote;
		this.emote = emote;
		this.isemote = isemote;
	}
	
	
	public Object getEmote() {
		if(stringemote=="") 
			return emote;
		else
			return stringemote; 
	}
	
	public boolean isEmote() {
		return isemote;
	}

}
