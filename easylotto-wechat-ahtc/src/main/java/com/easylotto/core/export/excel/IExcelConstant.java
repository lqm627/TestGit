package com.easylotto.core.export.excel;

import java.util.HashMap;
import java.util.Map;

public class IExcelConstant {

	public final static Map<Integer, String> num2Letter;
	private final static String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static{
		char[] _temps = letter.toCharArray();
		int i = 0;
		num2Letter = new HashMap<Integer, String>(0);
		for(int len = _temps.length;i<len;i++){
			num2Letter.put(i, ""+_temps[i]);
			num2Letter.put(26+i, "A"+_temps[i]);
		}
	}
	
}
