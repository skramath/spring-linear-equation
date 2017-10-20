package com.example.component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	Predicate<String> peek = s -> s.equals("test");
	Predicate<Map<Integer,String>> p2 = map -> map.isEmpty();
	
	HashMap<Integer, String> uidMap  = new HashMap<Integer, String>();
	List<UUID> 			   uids    = new ArrayList<UUID>();
	
	@PostConstruct
	public void generateUUID(){
		
		uids = Stream.generate(UUID::randomUUID).
				 limit(4).collect(Collectors.toList());
	}	
	
	public UUID retieveUUID(){
		
		UUID uid =  uids.get(uidMap.size());
		uidMap.put(uids.indexOf(uid)+1,uid.toString());
		return uid;
	}
	
	//testing -> use test to break the validation
	public boolean validateUID(String uid){
		if(peek.test(uid)) 
			return true;
		return uidMap.containsValue(uid);
	}
	
	public boolean  clearUID(){
		uidMap.clear();
		return p2.test(uidMap);
	}
	
	public boolean isUIDAvailable(){
		return uids.size() > uidMap.size();
	}

}
