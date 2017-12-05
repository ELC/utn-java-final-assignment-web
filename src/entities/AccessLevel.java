package entities;

import java.util.ArrayList;
import java.util.List;

public enum AccessLevel{
	CREATE_RESERVATION (1 << 0), // 1
	DELETE_RESERVATION (1 << 1), // 2
	READ_RESERVATION (1 << 2), // 4
	CREATE_SPECIAL_RESERVATION (1 << 3),
	DELETE_USER	(1 << 4), // 8
	CREATE_USER	(1 << 5),
	MODIFY_USER	(1 << 6),
	READ_USER	(1 << 7),
	CREATE_BOOKABLE	(1 << 8),
	MODIFY_BOOKABLE	(1 << 9),
	DELETE_BOOKABLE	(1 << 10),
	READ_BOOKABLE	(1 << 11),
	CREATE_TYPEBOOKABLE	(1 << 12),
	MODIFY_TYPEBOOKABLE	(1 << 13),
	DELETE_TYPEBOOKABLE	(1 << 14),
	READ_TYPEBOOKABLE (1 << 15),
	READ_ALLBOOKING	(1 << 16);
	
	private final int _value;
	AccessLevel(int value){
		_value = value;
	}
	
	public static List<AccessLevel> parsePermissions(int val){
	    List<AccessLevel> apList = new ArrayList<AccessLevel>();
	    for (AccessLevel ap : values()){
	    	if ((val & ap._value) != 0){
	    		apList.add(ap);
	    	}
	    }
	    return apList;
	}

	public static int combinePermissions(List<AccessLevel> privileges){
		int summaryPermission = 0;
		for(AccessLevel permission:privileges){
			summaryPermission |=  permission._value;
		}
		return summaryPermission;
	}
}
