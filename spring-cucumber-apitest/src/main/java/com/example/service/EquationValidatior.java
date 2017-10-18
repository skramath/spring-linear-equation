package com.example.service;
/*
 * Support Linear equation having x and y 
 */
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.bean.CoefficentBean;
import com.example.bean.CreateBean;

@Service("eqnValidator")
public class EquationValidatior implements ValidatorService{
	
	public CoefficentBean getCoefficent(String expression1,String expression2){
		Function<String,String[]> split = (String s) -> 
		{
			String[] arr1 = new String[3];
			arr1 = s.split("[0-9]+(?<=[-+])|(?=[=+-])");
			for(String ar :arr1)
				System.out.println(ar);
			return arr1;
		};
		
		Function<String,Integer> convert = (String s) -> {
			System.out.println(s);
			String value = null;
			if (s.endsWith("y")) {
				value = (s.startsWith("+y")||(s.startsWith("-y")))? s.charAt(0)+"1" : (s.substring(0,s.indexOf("y")));
			}else if (s.endsWith("x")){
				value = (s.startsWith("+x")||(s.startsWith("-x")))? s.charAt(0)+"1" : (s.substring(0,s.indexOf("x")));
			}
			return Integer.parseInt(value);
		};
		
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
					System.out.println(a);
					arr2[2]=Integer.parseInt(a.substring(1,a.length()));
				}				
			}
			return arr2;
		};
		
		CreateBean create =  (str1,str2) -> {
			CoefficentBean coefficeentBean = new CoefficentBean(); 
			/*ArrayList<Integer> list = new ArrayList<Integer>();
			Collections.addAll(list,str1);
			Collections.addAll(list,str2);*/
			coefficeentBean.setX11(str1[0]);
			coefficeentBean.setY12(str1[1]);
			coefficeentBean.setK1(str1[2]);
			coefficeentBean.setX21(str2[0]);
			coefficeentBean.setY22(str2[1]);
			coefficeentBean.setK2(str2[2]);
			return coefficeentBean;
			
		};
		
		String[]  arr1 = split.apply(expression1);
		Integer[] arr2 = iterator.apply(arr1);
		String[]  arr3 = split.apply(expression2);
		Integer[] arr4 = iterator.apply(arr3);
		
		//printValues(x1,y1,k1,x2,y2,k2);
		//Integer[] result = {x1,y1,k1,x2,y2,k2};
		return create.create(arr2, arr4);
	}
	
	/*void printValues(int x1,int x2,int y1,int y2,int k1,int k2){
		System.out.println("co efficent of x11 :"+x1);
		System.out.println("co efficent of y11 :"+y1);
		System.out.println("co efficent of x12 :"+x2);
		System.out.println("co efficent of y12 :"+y2);
		System.out.println("co efficent of k1 :"+k1);
		System.out.println("co efficent of k2 :"+k2);
	}*/
}
