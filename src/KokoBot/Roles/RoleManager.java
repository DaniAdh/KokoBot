package KokoBot.Roles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;

public class RoleManager {

	
	public static void InitialiseRoles() throws IOException {
		//TODO Find a way to separate roles
		for(Role role: KokoBot.guild.getRoles()) {
			KokoBot.roles.add(new CategorisedRole(RoleCategory.Test,role, false));
		}
		
		//BufferedWriter writer = new BufferedWriter(new FileWriter("Roles.txt"));
		String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
		BufferedReader reader = new BufferedReader(new FileReader(path+"src/KokoBot/Roles/Roles.txt"));
		Object[] Roles = reader.lines().toArray();
		reader.close();
		List<CategorisedRole> TextFileRoles = new LinkedList<CategorisedRole>();
		for(int i = 0;i < Roles.length;i++) {
			TextFileRoles.add(CategorisedRole.fromString((String) Roles[i]));
		}
		List<CategorisedRole> rolesDuplicate = KokoBot.roles;
		if(!TextFileRoles.containsAll(KokoBot.roles)) {
			rolesDuplicate.removeAll(TextFileRoles);
			BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt"));
			for(int i = 0;i<rolesDuplicate.size();i++) {
				bf.append(rolesDuplicate.get(i).toString()+"\n");
			}
			bf.close();
		}
		
		
	}
	
	
	
}
