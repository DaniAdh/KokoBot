package KokoBot.commands.commandTypes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import KokoBot.KokoBot;
import KokoBot.Roles.RoleManager;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rpurge implements GenericEventFunctional {

	@Override
	public void onEvent(MessageReceivedEvent event) throws IOException {
		if(!event.getAuthor().getId().equals("279013703020576784")) {
			event.getChannel().sendMessage("Permission Denied").complete();
			return;
		}
		
		List<Role> roles = KokoBot.guild.getRoles();
		for(Role r:roles) {
			if(r.getPermissionsRaw()==KokoBot.guild.getPublicRole().getPermissionsRaw()) {
				r.delete().complete();
			}
		}
		
		String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
		BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt",false));
		bf.write("Test '@everyone' false");
		bf.close();
		RoleManager.InitialiseRoles();
		
	}

}
