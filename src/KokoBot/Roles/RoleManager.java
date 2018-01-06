package KokoBot.Roles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;

public class RoleManager {

	
	public static void InitialiseRoles() throws IOException {
		//TODO Find a way to separate roles
		for(Role role: KokoBot.guild.getRoles()) {
			KokoBot.roles.add(new CategorisedRole(RoleCategory.Test,role, false));
		}
		
		//BufferedWriter writer = new BufferedWriter(new FileWriter("Roles.txt"));
		BufferedReader reader = new BufferedReader(new FileReader("Roles.txt"));
		reader.close();
		System.out.println(KokoBot.roles.toString());
		
		
	}
	
	
	
}
