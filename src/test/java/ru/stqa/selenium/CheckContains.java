package ru.stqa.selenium;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckContains {

    public static void main(String[] args) {
        ArrayList<String> categoryIds = new ArrayList<String>();
        categoryIds.add("0");
        categoryIds.add("1");
        categoryIds.add("2");

        //проверка есть ли id в массиве
        if (categoryIds.contains("2")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
