package KokoBot.Roles;

import java.io.Serializable;

import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;

public class CategorisedRole implements Serializable{

	private static final long serialVersionUID = 1L;
	public String Category;
	public Role role;
	public boolean IsSelfAssignable;
	public CategorisedRole(String Category, Role role, boolean IsSelfAssignable) {
		this.Category = Category;
		this.role = role;
		this.IsSelfAssignable = IsSelfAssignable;
	}
	
	public String toString() {
		return (Category) + " \'" + role.getName() + "\' " + Boolean.toString(IsSelfAssignable);
	}
	
	public static CategorisedRole fromString(String a) {
		String[] Substrings = a.split(" "); 
		
		
		Role foundRole = null;
		for(Role role:KokoBot.guild.getRoles()) {
			if(role.getName().equals(a.split("\'")[1])) {
				foundRole = role;
			}
		}
		
		return new CategorisedRole(Substrings[0], foundRole, (Substrings[2].equals("true")));
	}
	
	public boolean isEqual(CategorisedRole cr) {
		return (cr.role.getName().equals(role.getName()))&&(cr.Category.equals(Category));
	}
	
	public boolean isEqualAssignability(CategorisedRole cr) {
		return (cr.IsSelfAssignable == IsSelfAssignable);
	}
	
	public String getName() {
		return role.getName();
	}

	
	
}
