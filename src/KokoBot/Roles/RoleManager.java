package KokoBot.Roles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
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
		

		String path = String.format("%s/%s", System.getProperty("user.dir"), RoleManager.class.getPackage().getName().replace(".", "/")).substring(0, 26);
		BufferedReader reader = new BufferedReader(new FileReader(path+"src/KokoBot/Roles/Roles.txt"));
		Iterator<String> Roles = reader.lines().iterator();
		List<CategorisedRole> TextFileRoles = new LinkedList<CategorisedRole>();
		while(Roles.hasNext()) {
			String a = Roles.next();
			TextFileRoles.add(CategorisedRole.fromString(a));
		}
		reader.close();
		System.out.println(TextFileRoles);
		List<CategorisedRole> rolesDuplicate = new LinkedList<CategorisedRole>(KokoBot.roles);
		
		if(!TextFileRoles.containsAll(KokoBot.roles)) {
			rolesDuplicate.removeAll(TextFileRoles);
			for(CategorisedRole roletxt: TextFileRoles) {
				for(CategorisedRole roled: KokoBot.roles) {
					if(roled.isEqual(roletxt)) {
						rolesDuplicate.remove(roled);
					}
				}
			}
			
			
			System.out.println(rolesDuplicate);
			BufferedWriter bf = new BufferedWriter(new FileWriter(path+"src/KokoBot/Roles/Roles.txt",true));
			for(int i = 0;i<rolesDuplicate.size();i++) {
				bf.append((i==0? "\n" : "")+rolesDuplicate.get(i).toString()+(i==rolesDuplicate.size()-1? "" : "\n"));
			}
			bf.close();
		}
		
		
	}
	
	
	
}
