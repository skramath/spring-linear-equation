package com.example.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.component.UUIDGenerator;

@Service("uidService")
public class UIDServiceImp implements UIDService{

	@Autowired
	UUIDGenerator uidGenerator;

	@Override
	public UUID retrieveUID() {
		if(uidGenerator.isUIDAvailable())
			return uidGenerator.retieveUUID();
		return null;
	}

	@Override
	public boolean validateUID(String uid) {
		return uidGenerator.validateUID(uid);
	}

	@Override
	public boolean clearUID() {
		return uidGenerator.clearUID();
		
	}
	
	
	
	

}
