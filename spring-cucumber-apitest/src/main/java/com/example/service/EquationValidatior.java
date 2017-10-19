package com.example.service;
/*
 * Support Linear equation having x and y 
 */
import java.util.function.Function;
import java.util.function.Predicate;

import com.example.bean.CoefficentBean;
import com.example.bean.CreateBean;
import com.example.constants.Constants;

import org.springframework.stereotype.Service;

@Service("eqnValidator")
public class EquationValidatior implements ValidatorService{
	
	/* Function used to split the expression using regex */
	Function<String,String[]> split = (String s) -> 
	{
		String[] arr1 = new String[3];
		arr1 = s.split("[0-9]+(?<=[-+])|(?=[=+-])");
		return arr1;
	};
	
	/* Function is used to convert String values into Integer */
	Function<String,Integer> convert = (String s) -> {
		
		Predicate<String> p1 = checkX -> checkX.equals("x");
		Predicate<String> p2 = checkY -> checkY.equals("y");
		
		String value = null;
		if(s.equals("x")|| s.equals("y"))
			s="1"+s;
		if (s.endsWith("y")) {
			value = (s.startsWith("+y")||(s.startsWith("-y")))? s.charAt(0)+"1" : (s.substring(0,s.indexOf("y")));
		}else if (s.endsWith("x")){
			value = (s.startsWith("+x")||(s.startsWith("-x")))? s.charAt(0)+"1" : (s.substring(0,s.indexOf("x")));
		}
		return Integer.parseInt(value);
	};
	
	/*Util Helper function to remove extra characters*/
	Function<String[], Integer[]> iterator = (strArray) ->
	{
		Integer[] arr2 = new Integer[3];
		for(String a :strArray){
			a=a.replace(" ","");
			if(a.contains("x")){
				arr2[0] = convert.apply(a);
			}
			if(a.contains("y")){
				arr2[1] = convert.apply(a);
			}
			if(a.contains("=")){
				arr2[2]=Integer.parseInt(a.substring(1,a.length()));
			}				
		}
		return arr2;
	};
	
	/*
	 * Setting coeffiecentBean
	 */
	CreateBean create =  (str1,str2) -> {
		CoefficentBean coefficeentBean = new CoefficentBean(); 
		coefficeentBean.setX11(str1[0]);
		coefficeentBean.setY12(str1[1]);
		coefficeentBean.setK1(str1[2]);
		coefficeentBean.setX21(str2[0]);
		coefficeentBean.setY22(str2[1]);
		coefficeentBean.setK2(str2[2]);
		return coefficeentBean;
	};
	
	/*
	 * Extract coefficent from argument expressions
	 */
	public CoefficentBean getCoefficent(String expression1,String expression2){
		try{
			String[] arr1  = split.apply(expression1);
			Integer[] arr2 = iterator.apply(arr1);
			String[] arr3  = split.apply(expression2);
			Integer[] arr4 = iterator.apply(arr3);
			return create.create(arr2, arr4);			
		}catch(Exception exe){
			System.out.println(Constants.INVALID_EXPRESSION +"["+ exe.getMessage() +"]");
			return new CoefficentBean(0, 0, Constants.INVALID_EXPRESSION +"["+ exe.getMessage() +"]");
		}
	}
	
	/*
	* Extract coefficent (x and y from an expression
	*/
	public CoefficentBean getCoefficent(String expression1){
		try{
			if(expression1 != null){	
				String[]  arr1  = split.apply(expression1);
				Integer[] arr2  = iterator.apply(arr1);
				for(Integer ar :arr2)
					System.out.print(ar+",");
				if((arr2[0]!=null) && (arr2[1]!=null) && (arr1.length == 3)){
					return new CoefficentBean(arr2[0], arr2[1], Constants.VALID_EXPRESSION);
				}
				return new CoefficentBean(0.0, 0.0, Constants.INVALID_EXPRESSION);	
			}
			return new CoefficentBean(0, 0, Constants.INVALID_EXPRESSION);
		}catch(Exception exe){
			System.out.println(Constants.INVALID_EXPRESSION +"["+ exe.getMessage() +"]");
			return new CoefficentBean(0, 0, Constants.INVALID_EXPRESSION +"["+ exe.getMessage() +"]");
		}
		
	}
}
