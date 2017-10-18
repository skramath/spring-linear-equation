package com.example.service;

import java.util.UUID;

public interface UIDService {
	
	public UUID retrieveUID();
	public boolean validateUID(String uid);
	public boolean clearUID();
	
}
