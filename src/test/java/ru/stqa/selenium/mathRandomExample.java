package ru.stqa.selenium;


public class mathRandomExample
{
    public static void main(String[] args)
    {
        int a = (int) ( Math.random() * 3 ) + 1; //про random https://vertex-academy.com/tutorials/ru/generaciya-sluchajnyx-chisel-v-java/
        System.out.printf("Result = " + a);

    }
}
