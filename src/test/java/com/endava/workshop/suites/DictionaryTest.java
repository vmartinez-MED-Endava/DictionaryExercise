package com.endava.workshop.suites;

import com.endava.workshop.services.Dictionary;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class DictionaryTest {

    Dictionary dictionary;

    @BeforeClass
    public void loadDictionary(){
        dictionary = new Dictionary();

    }

        /*public static void main(String[] args) {
        String s = "WORKING";
        //String [] sl = stringToStringArray(s);
        //System.out.println(sl[0]);
        //System.out.println(sl.length);

        Dictionary dr = new Dictionary();
        List<String> strs = dr.getEnglishWordsFromString(s);
        strs.forEach(word-> System.out.println(word));
        }*/


    @Test
    public void englishWordsValidation(){
        String word = "Working";
        String testWord1 = "work";
        String testWord2 = "king";
        String testWord3 = "win";


        List<String> derivedWords = dictionary.getEnglishWordsFromString(word);
        System.out.println(derivedWords.size());
        derivedWords.forEach(x-> System.out.println(x));

    }

    @Test
    public void stringToStringArrayValidation() {
        String str1  = "hello";
        String str2  = "";
        String str3  = "%$#%#&/&";
        String str4  = "hh ll";

        /*String[]arr1 = dictionary.stringToStringArray(str1);
        Assert.assertTrue(arr1.length == str1.length());


        String[]arr2 = dictionary.stringToStringArray(str2);
        Assert.assertTrue(arr2.length == 0);

        String[]arr3 = dictionary.stringToStringArray(str3);
        Assert.assertTrue(arr3.length == 0);

        String[]arr4 = dictionary.stringToStringArray(str4);
        Assert.assertTrue(arr4.length == str4.length());*/
    }

}
