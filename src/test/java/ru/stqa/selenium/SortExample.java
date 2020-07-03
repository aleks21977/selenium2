package ru.stqa.selenium;

// A sample Java program to sort an array of integers
// using Arrays.sort(). It by default sorts in
// ascending order
import java.util.Arrays;

public class SortExample
{
    public static void main(String[] args)
    {
        // Our arr contains 8 elements
        //int[] arr = {13, 7, 6, 45, 21, 9, 101, 102};

        String[] arr = {"cX4gg", "Ba34fb", "Cafgb6", "Xc", "ac", "Ab", "bx", "xb"};

        Arrays.sort(arr);
        System.out.printf("Modified arr[] : %s", Arrays.toString(arr));

    }
}
