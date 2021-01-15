package com.example.student.api.utils;

public class InputValidation {
    public static boolean isValidString(String input){
        if(input != null && input.length() != 0)
            return true;
        return false;
    }

    public static boolean isEqual(String input1, String input2){
        return input1.equals(input2);
    }
}
