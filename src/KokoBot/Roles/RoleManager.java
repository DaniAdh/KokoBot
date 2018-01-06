package KokoBot.Roles;
import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;

public class RoleManager {

	
	public static void InitialiseRoles() {
		//TODO Find a way to separate roles
		for(Role role: KokoBot.guild.getRoles()) {
			KokoBot.roles.add(new CategorisedRole(RoleCategory.Test,role));
		}
		
	}
	
	
	
}
