package KokoBot.Roles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;

public class RoleManager {

	
	public static void InitialiseRoles() throws IOException {
		
		for(Role role: KokoBot.guild.getRoles()) {
			KokoBot.roles.add(new CategorisedRole("Test", role, false, ""));
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(KokoBot.path));
		Iterator<String> Roles = reader.lines().iterator();
		List<CategorisedRole> TextFileRoles = new LinkedList<CategorisedRole>();
		
		while(Roles.hasNext()) {
			String a = Roles.next();
			TextFileRoles.add(CategorisedRole.fromString(a.split("\"")[0]));
		}
		
		reader.close();
		List<CategorisedRole> MissingRolesFromTextFile = new LinkedList<CategorisedRole>(KokoBot.roles);
		
		
		
		if(!TextFileRoles.containsAll(KokoBot.roles)) {
			MissingRolesFromTextFile.removeAll(TextFileRoles);
			for(CategorisedRole roletxt: TextFileRoles) {
				for(CategorisedRole roled: KokoBot.roles) {
					if(roled.isEqual(roletxt)) {
						MissingRolesFromTextFile.remove(roled);
					}
				}
			}
			
			BufferedWriter bf = new BufferedWriter(new FileWriter(KokoBot.path,true));
			for(int i = 0;i<MissingRolesFromTextFile.size();i++) {
				bf.append((i==0? "\n" : "")+MissingRolesFromTextFile.get(i).toString()+(i==MissingRolesFromTextFile.size()-1? "" : "\n"));
			}
			bf.close();
		}
		
		Comparator<CategorisedRole> comparator = Comparator.comparing(CategorisedRole::getName);
		
		BufferedReader reader1 = new BufferedReader(new FileReader(KokoBot.path));
		Roles = reader1.lines().iterator();
		TextFileRoles = new LinkedList<CategorisedRole>();
		while(Roles.hasNext()) {
			String a = Roles.next();
			TextFileRoles.add(CategorisedRole.fromString(a));
		}
		reader1.close();
		
		TextFileRoles.sort(comparator);
		for(int i = 0;i<KokoBot.roles.size();i++) {
			if(!KokoBot.roles.get(i).isEqualAssignability(TextFileRoles.get(i)) || !KokoBot.roles.get(i).Category.equals(TextFileRoles.get(i).Category) || !KokoBot.roles.get(i).Description.equals(TextFileRoles.get(i).Description)) {
				KokoBot.roles.set(i, TextFileRoles.get(i));
			}
		}
		
		
		
	}
	
	
	
}
