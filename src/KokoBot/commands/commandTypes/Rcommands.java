package KokoBot.commands.commandTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Roles.CategorisedRole;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rcommands implements GenericEventFunctional {

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		if(event.getMessage().getContentRaw().split(" ").length==1) {
			event.getChannel().sendMessage("Specify a Category, to see categories type -rcategories").complete();
			return;
		}
		List<String> roles = new ArrayList<String>();
		for(CategorisedRole role: KokoBot.roles) {
			if(role.Category.equals(event.getMessage().getContentRaw().split(" ")[1]) && (event.getMessage().getContentRaw().contains("roles") || role.IsSelfAssignable)) {
				roles.add(role.getName());
			}
		}
		
		event.getAuthor().openPrivateChannel().complete().sendMessage("List of roles under the category " + event.getMessage().getContentRaw().split(" ")[1] + ": " + roles.toString().replace("[", "").replace("]", "")).complete();

	}

}
