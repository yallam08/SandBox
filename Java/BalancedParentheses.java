package com.yallam.sandbox;

public class BalancedParentheses {

    public static void main(String[] args) {

        String[] tests = {"(((()()()())))", ")()(", "()((((())))()()()()(", ")(", "()", "(((())))"};
        for (String test : tests) {
            int count = 0;
            for (int i = 0; i < test.length(); i++) {
                if (test.charAt(i) == '(') {
                    count++;
                } else if (test.charAt(i) == ')' && count > 0) {
                    count--;
                } else {
                    count = -1;
                    break;
                }
            }
            System.out.println("\"" + test + "\"" + (count == 0 ? ": Balanced" : ": Not Balanced"));
        }
        
    }

}
