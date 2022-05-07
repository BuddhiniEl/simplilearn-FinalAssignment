package com.simplilearn;

public class DataUtils {
	public static String getPrice(String priceText) {
		String[] eles = priceText.split(" ");
		return eles[1];
	}
}
