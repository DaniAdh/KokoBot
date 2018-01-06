package KokoBot.Roles;

public enum RoleCategory {
	//TODO Insert Wanted Categories Here
	Test(true);
	
	boolean IsSelfAssignable;
	RoleCategory(boolean IsSelfAssignable){
		this.IsSelfAssignable = IsSelfAssignable;
	}
}
