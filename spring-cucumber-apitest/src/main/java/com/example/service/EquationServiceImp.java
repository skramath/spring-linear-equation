package com.example.service;

import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.example.bean.CoefficentBean;
import com.example.bean.EquationSolve;

@Service("eqnService")
public class EquationServiceImp implements EquationService{
	
	public CoefficentBean solve2x2LinearEquation( CoefficentBean coefficentBean )
	{
		Function<CoefficentBean,Double> f1 = coefficent -> {
			return (coefficent.getX11() * coefficent.getY22() - coefficent.x21 * coefficent.y12); 
		};
		double det = f1.apply(coefficentBean);
		Predicate<Double> p = d -> d == 0.0;
		if( p.test(det) )
	    {
		   	coefficentBean.setMessage("No Unique Solution");
	        return coefficentBean;    //No unique solution
	    }
		
		EquationSolve solve = (k1,k2,x1,x2) -> { return (k1*x1 - k2*x2)/det; };
		double x = solve.solve2x2LinearEquation(coefficentBean.getK1(), coefficentBean.getK2(), coefficentBean.getY22(),coefficentBean.getY12());
		double y = solve.solve2x2LinearEquation(coefficentBean.getK1(), coefficentBean.getK2(), coefficentBean.getX21(),coefficentBean.getX11());
		coefficentBean.setMessage("Values of X="+x+" and Y="+y);
	    return coefficentBean;
	 }

}
