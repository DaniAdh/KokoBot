package KokoBot.commands.commandTypes.calendarCommands;

import java.io.IOException;

import KokoBot.KokoBot;
import KokoBot.Utilities;
import KokoBot.commands.commandTypes.GenericEventFunctional;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AddEvent  implements GenericEventFunctional{

	@Override
	public Message onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length!=3+1) {
			Utilities.sendMessage(event, "Not enough arguments, type -help to get syntax");
			return null;
		}
		
		String who = Utilities.SplitMessageAndGetIndex(event, " ", 1);
		String date = Utilities.getMessage(event).split(" ")[2] + Utilities.getMessage(event).split(" ")[3];
		//AddEvent <Name/me/everybody> <X hours/X minutes/XX:XX later/ dd/MM XX:XX / XX:XX / dd/MM>
		if(who.toUpperCase()!="ME" && !KokoBot.guild.getMembersWithRoles(Utilities.findRoleWithName(KokoBot.roles, "Event Manager").role).contains(Utilities.getMember(event))) {
			Utilities.sendMessage(event, ("You do not have the \"Event Manager\" role!"));
			return null;
		}
		
		
		return Utilities.sendMessage(event, ("Created event " +"!"));
		
	}

}
