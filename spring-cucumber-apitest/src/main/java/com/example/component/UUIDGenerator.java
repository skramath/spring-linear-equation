package com.example.component;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 
 * @author sarath
 * UUID Generator used to create Unique Identifers used in request parameter.
 * Created UUID will be available in the server cache.
 * 
 */
@Component
public class UUIDGenerator {
	
	HashMap<Integer, String> uidMap  = new HashMap<Integer, String>();
	List<UUID> 			   uids    = new ArrayList<UUID>();
	
	@PostConstruct
	public void generateUUID(){
		
		uids.add(UUID.randomUUID());
		uids.add(UUID.randomUUID());
		uids.add(UUID.randomUUID());
		uids.add(UUID.randomUUID());
	}	
	
	public UUID retieveUUID(){
		
		UUID uid =  uids.get(uidMap.size());
		uidMap.put(uids.indexOf(uid)+1,uid.toString());
		return uid;
	}
	
	//testing -> use test to break the validation
	public boolean validateUID(String uid){
		if(uid.equals("test")) 
			return true;
		return uidMap.containsValue(uid);
	}
	
	public boolean  clearUID(){
		uidMap.clear();
		return uidMap.isEmpty();
	}
	
	public boolean isUIDAvailable(){
		return uids.size() > uidMap.size();
	}

}
