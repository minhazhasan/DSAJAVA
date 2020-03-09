package com.minhaz.dsa;

import javafx.util.Pair;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problems {
	public String RPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for(String token : tokens) {
			
			if(!operator(token)) {
				try {
					stack.add(Integer.parseInt(token));
				} catch(NumberFormatException e) {
					return e.getMessage();
				}
			} else {
				
				if(stack.size() >= 2) {
					int val2 = stack.pop();
					int val1 = stack.pop();
					stack.add(calculate(token, val1, val2));
				} else {
					return Integer.toString(0);
				}
			}
		}
		
		return stack.pop().toString();
	}
	
	private boolean operator(String op) {
		return op.equals("/") || op.equals("*") 
				|| op.equals("+") || op.equals("-");
	}
	
	private int calculate(String token, int val1, int val2) {
		int result;
		switch(token) {
			case "/":
				if(val2 != 0)
					result = val1 / val2;
				else
					result = 0;
				break;
				
			case "*":
				result = val1 * val2;
				break;
			case "+":
				result = val1 + val2;
				break;
			case "-":
				result = val1 - val2;
				break;
			default:
				result = 0;
				break;
		}
		
		return result;
	}
	
	public LocalDate dateFromYear(int year) {
		YearMonth ym = YearMonth.of(year, Month.OCTOBER);
		LocalDate d = ym.atDay(1).with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.TUESDAY));
		return d;
	}
}
