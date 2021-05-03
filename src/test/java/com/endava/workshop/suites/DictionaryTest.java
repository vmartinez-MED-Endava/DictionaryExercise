package com.endava.workshop.suites;

import com.endava.workshop.data.dataprovider.SearchDataProvider;
import com.endava.workshop.data.dataprovider.ValidationDataProvider;
import com.endava.workshop.services.Dictionary;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class DictionaryTest extends BaseTest{

    Dictionary dictionary;

    @BeforeClass(description = "Initialize the Dictionary for executing our Tests")
    public void loadDictionary(){
        dictionary = new Dictionary();

    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter")
    public void noInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString("");

        boolean isNullList = derivedWords == null;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter")
    public void spaceCharInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(" ");

        boolean isNullList = derivedWords == null;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter")
    public void specialCharacterInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString("!");

        boolean isNullList = derivedWords == null;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter")
    public void multipleSpecialCharactersInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString("! [");

        boolean isNullList = derivedWords == null;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input")
    public void twoValidCharsInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString("ab");

        boolean outputPowerOfTwo = derivedWords.size() == 0;

        Assert.assertTrue(outputPowerOfTwo, "The list of English Words is empty");
    }

    @Test(description = "Validate Dictionary output with long random valid input")
    public void validCharsInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString("abfgdg hfsds pho rer asa");

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        Assert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with default wordTest",
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        Assert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ",
            dataProvider= "ValidationProvider", dataProviderClass= ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        boolean valStringInDict1 = derivedWords.contains(valStr1);
        Assert.assertTrue(valStringInDict1, "Derived Word was found in Dictionary");

        boolean valStringInDict2 = derivedWords.contains(valStr2);
        Assert.assertTrue(valStringInDict2, "Derived Word was found in Dictionary");

        boolean valStringInDict3 = derivedWords.contains(valStr3);
        Assert.assertTrue(valStringInDict3, "Derived Word was found in Dictionary");
    }
}
