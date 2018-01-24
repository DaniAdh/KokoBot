package KokoBot.Roles;

import KokoBot.KokoBot;
import net.dv8tion.jda.core.entities.Role;

public class CategorisedRole{


	public String Category;
	public String Description;
	public Role role;
	public boolean IsSelfAssignable;
	public CategorisedRole(String Category, Role role, boolean IsSelfAssignable, String Description) {
		this.Category = Category;
		this.role = role;
		this.IsSelfAssignable = IsSelfAssignable;
		this.Description = Description;
	}
	
	public String toString() {
		return Category + " \'" + role.getName() + "\' " + Boolean.toString(IsSelfAssignable) + " \"" + Description + "\"";
	}
	
	//Category 'Name' isselfassignable "description"
	public static CategorisedRole fromString(String a) {
		
		Role foundRole = null;
		for(Role role:KokoBot.guild.getRoles()) {
			if(role.getName().equals(a.split("\'")[1])) {
				foundRole = role;
			}
		}
		
		return new CategorisedRole(a.split(" ")[0], foundRole, a.indexOf("true")!=-1 && (a.split("\'")[1].indexOf("true")+a.indexOf("\'"))!=a.indexOf("true"), a.substring(a.indexOf("\"")+1, a.replaceFirst("\"", "").indexOf("\"")+1));
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
