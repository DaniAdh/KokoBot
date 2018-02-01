package KokoBot.Roles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import KokoBot.FileUtilities;
import KokoBot.KokoBot;
import KokoBot.Utilities;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;

public class RoleManager {

	
	public static void InitialiseRoles(Guild guild) throws IOException {
		
		new File(KokoBot.path+"Roles/"+guild.getId()+".txt").createNewFile();
		KokoBot.roles.put(guild.getId(), new ArrayList<CategorisedRole>());
		for(Role role: guild.getRoles()) {
			KokoBot.roles.put(guild.getId(),Utilities.add(KokoBot.roles.get(guild.getId()),new CategorisedRole("Default", role, false, "")));
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(KokoBot.path+"Roles/"+guild.getId()+".txt"));
		Iterator<String> Roles = reader.lines().iterator();
		List<CategorisedRole> TextFileRoles = new LinkedList<CategorisedRole>();
		
		while(Roles.hasNext()) {
			String a = Roles.next();
			TextFileRoles.add(CategorisedRole.fromString(a,guild));
		}
		
		reader.close();
		List<CategorisedRole> MissingRolesFromTextFile = new LinkedList<CategorisedRole>(KokoBot.roles.get(guild.getId()));
		
		
		
		if(!TextFileRoles.containsAll(KokoBot.roles.get(guild.getId()))) {
			MissingRolesFromTextFile.removeAll(TextFileRoles);
			for(CategorisedRole roletxt: TextFileRoles) {
				for(CategorisedRole roled: KokoBot.roles.get(guild.getId())) {
					if(roled.isEqual(roletxt)) {
						MissingRolesFromTextFile.remove(roled);
					}
				}
			}
			
			//BufferedWriter bf = new BufferedWriter(new FileWriter(KokoBot.path+"Roles/Roles.txt",true));
			for(int i = 0;i<MissingRolesFromTextFile.size();i++) {
				//bf.append((i==0? "\n" : "")+MissingRolesFromTextFile.get(i).toString()+(i==MissingRolesFromTextFile.size()-1? "" : "\n"));
				FileUtilities.addToFile(KokoBot.path+"Roles/"+guild.getId()+".txt", MissingRolesFromTextFile.get(i).toString());
			}
			//bf.close();
			
		}
		
		Comparator<CategorisedRole> comparator = Comparator.comparing(CategorisedRole::getName);
		
		BufferedReader reader1 = new BufferedReader(new FileReader(KokoBot.path+"Roles/"+guild.getId()+".txt"));
		Roles = reader1.lines().iterator();
		TextFileRoles = new LinkedList<CategorisedRole>();
		while(Roles.hasNext()) {
			String a = Roles.next();
			TextFileRoles.add(CategorisedRole.fromString(a,guild));
		}
		reader1.close();
		
		TextFileRoles.sort(comparator);
		for(int i = 0;i<KokoBot.roles.get(guild.getId()).size();i++) {
			if(!KokoBot.roles.get(guild.getId()).get(i).isEqualAssignability(TextFileRoles.get(i)) 
					|| !KokoBot.roles.get(guild.getId()).get(i).Category.equals(TextFileRoles.get(i).Category) 
					|| !KokoBot.roles.get(guild.getId()).get(i).Description.equals(TextFileRoles.get(i).Description)) {
				KokoBot.roles.get(guild.getId()).set(i, TextFileRoles.get(i));
			}
		}
		
		
		
	}
	
	
	
}
