package ru.stqa.selenium;

import org.junit.Assert;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.Assert.assertEquals;

public class GetStringFromSubstring {

    public static void main(String[] args) {

        //https://www.codeflow.site/ru/article/java-substring
        String text = "Julia Evans was born on 25-09-1984. She is currently living in the USA (United States of America).";


        assertEquals("USA (United States of America).", text.substring(67));
        System.out.println(text.substring(67));

        assertEquals("USA (United States of America)", text.substring(67, text.length() - 1));
        System.out.println(text.substring(67, text.length() - 1));

        assertEquals("United States of America", text.substring(text.indexOf('(') + 1, text.indexOf(')')));
        System.out.println(text.substring(text.indexOf('(') + 1, text.indexOf(')')));

        assertEquals("1984", text.substring(text.lastIndexOf('-') + 1, text.indexOf('.')));
        System.out.println(text.substring(text.lastIndexOf('-') + 1, text.indexOf('.')));

        assertEquals("USA (United States of America)", text.substring(text.indexOf("USA"), text.indexOf(')') + 1));
        System.out.println(text.substring(text.indexOf("USA"), text.indexOf(')') + 1));


        //subSequence отличие от substring состоит в том, что он возвращает CharSequence вместо String и может использоваться только с определенной индекс начала и конца
        assertEquals("USA (United States of America)", text.subSequence(67, text.length() - 1));
        System.out.println(text.subSequence(67, text.length() - 1));


        //Использование регулярных выражений
        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            Assert.assertEquals("25-09-1984", matcher.group());
            System.out.println(matcher.group());
        }

        //Использование split
        String[] sentences = text.split("\\.");
        assertEquals("Julia Evans was born on 25-09-1984", sentences[0]);
        System.out.println(sentences[0] + "'\n'" + sentences[1]);


        //Использование Scanner
        String text2 = "1Julia Evans was born on 25-09-1984. She is currently living in the USA (United States of America(). 123";
        try (Scanner scanner = new Scanner(text2)) {
            while (scanner.hasNext()) {
                //do something with the tokens returned by scanner.next()
                scanner.useDelimiter("\\.");
                //assertEquals("Julia Evans was born on 25-09-1984", scanner.next());
                System.out.println(scanner.next());
            }
        }


        //Использование StringUtils
        assertEquals("United States of America(", substringBetween(text2, "(", ")"));
        System.out.println(substringBetween("1" + text2, "(", ")"));
        System.out.println(substringBetween(text2, "("));

        assertEquals("the USA (United States of America).", substringAfter(text, "living in "));
        System.out.println(substringAfter(text, "living in "));

        assertEquals("Julia Evans", substringBefore(text, " was born"));
        System.out.println(substringBefore(text, " was born"));
    }
    //https://www.codeflow.site/ru/article/java-substring
}