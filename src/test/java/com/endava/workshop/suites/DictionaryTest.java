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
    private final String EMPTY_STRING = "";
    private final String ONE_SPACED_STRING = " ";
    private final String SPECIAL_CHARACTER = "!";
    private final String TWO_SPECIAL_CHARACTERS = "! [";
    private final String TWO_VALID_CHARACTERS = "ab";
    private final String ONE_RANDOM_SENTENCE = "abfgdg hfsds pho rer asa";
    private final String LOWERCASE_TEST_WORD = "king";
    private final String CAPITALIZE_TEST_WORD = "King";
    private final String UPPERCASE_TEST_WORD = "KING";

    @BeforeClass(description = "Initialize the Dictionary for executing our Tests")
    public void loadDictionary(){
        dictionary = new Dictionary();
    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter", priority = 1)
    public void noInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(EMPTY_STRING);

        boolean isNullList = (derivedWords == null)?true:false;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter", priority = 2)
    public void spaceCharInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_SPACED_STRING);

        boolean isNullList = (derivedWords == null)?true:false;;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter", priority = 3)
    public void specialCharacterInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(SPECIAL_CHARACTER);

        boolean isNullList = (derivedWords == null)?true:false;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter", priority = 4)
    public void multipleSpecialCharactersInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(TWO_SPECIAL_CHARACTERS);

        boolean isNullList = (derivedWords == null)?true:false;

        Assert.assertTrue(isNullList == true, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input", priority = 5)
    public void twoValidCharsInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(TWO_VALID_CHARACTERS);

        boolean outputPowerOfTwo = derivedWords.size() == 0;

        Assert.assertTrue(outputPowerOfTwo, "The list of English Words is empty");
    }

    @Test(description = "Validate Dictionary output with long random valid input", priority = 6)
    public void validCharsInputOnDictionaryValidation(){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE);

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        Assert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with default wordTest", priority = 7,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        Assert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ", priority = 8,
            dataProvider= "ValidationProvider", dataProviderClass= ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3){
        List<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        boolean validationStringFound1 = derivedWords.contains(valStr1);
        Assert.assertTrue(validationStringFound1, "Derived Word was found in Dictionary");

        boolean validationStringFound2 = derivedWords.contains(valStr2);
        Assert.assertTrue(validationStringFound2,"Derived Word was found in Dictionary");

        boolean validationStringFound3 = derivedWords.contains(valStr3);
        Assert.assertTrue(validationStringFound3, "Derived Word was found in Dictionary");
    }

    @Test(description = "Validate a lowercase String gets converted into a UpperCase string", priority = 9,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void validateLowercaseToUppercaseConversion(String wordTest){
        dictionary.getEnglishWordsFromString(wordTest);

        boolean upperCaseValidation1 = dictionary.containsString(UPPERCASE_TEST_WORD);
        Assert.assertTrue(upperCaseValidation1, "KING Word was found in Dictionary");

        boolean upperCaseValidation2 = dictionary.containsString(CAPITALIZE_TEST_WORD);
        Assert.assertTrue(upperCaseValidation2, "King Word was found in Dictionary");

        boolean upperCaseValidation3 = dictionary.containsString(LOWERCASE_TEST_WORD);
        Assert.assertTrue(upperCaseValidation3, "king Word was found in Dictionary");
    }
}
