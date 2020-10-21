package ru.appline.logic;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Result implements Serializable {
	
	private static final Result instance = new Result();

	private final Map<String, Integer> result;
	
	public static Result getInstance( ) {
		return instance;
	}
	
	private Result() {
		result = new HashMap<>();
	}
	
	public void add(String text, int res) {
		result.put(text, res);
	}
	
	public Map<String, Integer> getFromList() {	
		return result;
	}
	
}
