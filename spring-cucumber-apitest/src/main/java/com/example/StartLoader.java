package com.example;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class StartLoader {
	
	HashMap map=new HashMap();
	
	@PostConstruct
	public void initLoader() {
		System.out.println(UUID.randomUUID());
	}

}
