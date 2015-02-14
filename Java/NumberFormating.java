package com.cat.project;


public class NumberFormating {
	
	public static void main(String[] args){
		
		int num = 100000;
		System.out.println(formattedNum(num));
		
	}
	
	public static StringBuilder formattedNum(int num){
		
		StringBuilder result = new StringBuilder();
		String stringNum = Integer.toString(num);
		int numLength = stringNum.length();
		
		if(numLength <= 3){
			result.append(stringNum);
			return result;
		}
		
		int numOfCommas = numLength % 3 == 0 ? numLength / 3 - 1 : numLength / 3;
		
		int start = 0;
		int end = numLength % 3 == 0 ? 3 : numLength % 3;

		while (numOfCommas >= 0){
			if(numOfCommas == 0){
				result.append(stringNum.substring(start, end));
				break;
			}
			result.append(stringNum.substring(start, end));
			result.append(',');
			start = end;
			end += 3;
			numOfCommas--;
		}
		
		return result;
	}

}
