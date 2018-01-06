package KokoBot.Roles;

import net.dv8tion.jda.core.entities.Role;

public class CategorisedRole {
	RoleCategory Category;
	Role role;
	public CategorisedRole(RoleCategory Category, Role role) {
		this.Category = Category;
		this.role = role;
	}
	
	
	
}
