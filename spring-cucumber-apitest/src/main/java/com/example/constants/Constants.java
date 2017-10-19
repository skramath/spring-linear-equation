package com.example.constants;

public interface Constants {
	
	
	public static final String SUCCESS_CODE 	  = "success"; 
    public static final String FAILED_CODE  	  = "failed";
    
    public static final String SERVER_UP    	  = "api testing server is running";
    public static final String SERVER_DOWN  	  = "api testing server is down";
     
    public static final String UUID_INAVLID       = "invalid uuid";
    public static final String UUID_AVAILABLE     = "slot is available";
    public static final String UUID_UNAVAILABLE   = "slot is unavailable, please try later";
    public static final String UUID_CLEARED       = "cleared uuid cache successfully";
    public static final String UUID_NOT_CLEARED   = "cleared uuid cache failed";
    
    public static final String INVALID_EXPRESSION = "this linear equation is not supported, validation failed :(";
    public static final String VALID_EXPRESSION   = "linear equation is validated successfully :)";
    
    public static final String SOLUTION_NOT_FOUND  = "this linear equation has no unique solution";
    public static final String SOLUTION_FOUND      = "linear equation solved successfully [x,y]:";
    
    public static final String COEFFICENT_FOUND     = "coefficent of linear equation [x,y]:";
    public static final String COEFFICENT_NOT_FOUND = "error in finding the coefficents";
    
    public static final String ERROR_OCCURED 		= "error occured in the system :";
    
    
}
