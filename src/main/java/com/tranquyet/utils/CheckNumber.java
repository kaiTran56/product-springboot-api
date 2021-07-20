package com.tranquyet.utils;

public class CheckNumber {
	
	public static boolean isNumber(String word) {
		try {
			Double.parseDouble(word);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static double convertToDouble(String word) {
		return isNumber(word) ? Double.parseDouble(word) : -1;
	}

}
