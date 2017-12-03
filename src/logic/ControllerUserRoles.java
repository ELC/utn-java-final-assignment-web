package logic;
import java.util.ArrayList;

import data.DataUserRoles;
import entities.UserRole;
public class ControllerUserRoles {
	private DataUserRoles dataRoles= new DataUserRoles();
	
	public  ArrayList<UserRole> getAll() throws Exception {
		return dataRoles.getAll();
		}
}
