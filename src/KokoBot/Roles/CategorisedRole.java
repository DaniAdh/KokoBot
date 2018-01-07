package KokoBot.Roles;

import java.io.Serializable;

import net.dv8tion.jda.core.entities.Role;

import KokoBot.KokoBot;

public class CategorisedRole implements Serializable{

	private static final long serialVersionUID = 1L;
	RoleCategory Category;
	public Role role;
	boolean IsSelfAssignable;
	public CategorisedRole(RoleCategory Category, Role role, boolean IsSelfAssignable) {
		this.Category = Category;
		this.role = role;
		this.IsSelfAssignable = IsSelfAssignable;
	}
	
	public String toString() {
		return (Category.name() + " \'" + role.getName() + "\' " + Boolean.toString(IsSelfAssignable));
	}
	
	public static CategorisedRole fromString(String a) {
		String[] Substrings = a.split(" "); 
		
		
		Role foundRole = null;
		for(CategorisedRole role:KokoBot.roles) {
			if(role.role.getName().equals(a.split("\'")[1])) {
				foundRole = role.role;
			}
		}
		
		return new CategorisedRole(RoleCategory.valueOf(Substrings[0]), foundRole, (Substrings[2].equals("true")));
	}
	
	public boolean isEqual(CategorisedRole cr) {
		return (cr.role.getName()==role.getName())&&(cr.Category==Category);
	}
	
	
}
