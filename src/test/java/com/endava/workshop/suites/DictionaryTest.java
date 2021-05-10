package com.endava.workshop.suites;

import com.endava.workshop.data.dataprovider.SearchDataProvider;
import com.endava.workshop.data.dataprovider.ValidationDataProvider;
import com.endava.workshop.services.Dictionary;
import com.endava.workshop.utils.exceptions.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import java.util.List;



public class DictionaryTest extends BaseTest{

    Dictionary dictionary;
    private final String NULL_OBJECT = null;
    private final String EMPTY_STRING = "";
    private final String ONE_SPACED_STRING = " ";
    private final String SPECIAL_CHARACTER = "!";
    private final String TWO_SPECIAL_CHARACTERS = "! [";
    private final String TWO_VALID_CHARACTERS = "ab";
    private final String ONE_RANDOM_SENTENCE_WITH_SPACES = "abfgdg hfsds pho rer asa";
    private final String ONE_RANDOM_SENTENCE_WITHOUT_SPACES = "abfgdghfsdsphorerasa";
    private final String LOWERCASE_TEST_WORD = "king";
    private final String CAPITALIZE_TEST_WORD = "King";
    private final String UPPERCASE_TEST_WORD = "KING";

    @BeforeClass(description = "Initialize the Dictionary for executing our Tests")
    public void loadDictionary(){
        dictionary = new Dictionary();
    }


    @Test(description = "Validate Dictionary output when null is typed as an inpút parameter", expectedExceptions = NullException.class, priority = 0)
    public void nullInputDictionaryValidation() throws HandlerException {
        List<String>  derivedWords = dictionary.getEnglishWordsFromString(NULL_OBJECT);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter",
            expectedExceptions = EmptyInputParameterException.class, priority = 1)
    public void noInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter",
            expectedExceptions = IncorrectInputParameter.class, priority = 2)
    public void spaceCharInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_SPACED_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter",
            expectedExceptions = IncorrectInputParameter.class, priority = 3)
    public void specialCharacterInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(SPECIAL_CHARACTER);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter",
            expectedExceptions = IncorrectInputParameter.class, priority = 4)
    public void multipleSpecialCharactersInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(TWO_SPECIAL_CHARACTERS);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input", priority = 5)
    public void twoValidCharsInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(TWO_VALID_CHARACTERS);

        boolean outputPowerOfTwo = derivedWords.size() == 0;

        softAssert.assertTrue(outputPowerOfTwo, "The list of English Words is empty");
    }

    @Test(description = "Validate Dictionary output with long random input with spaces in it ",
            expectedExceptions = IncorrectInputParameter.class, priority = 6)
    public void invalidLongCharsInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE_WITH_SPACES);

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        softAssert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with long random input without spaces in it ", priority = 7)
    public void longCharsInputOnDictionaryValidation() throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        softAssert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with default wordTest", priority = 8,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest) throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        boolean outputPowerOfTwo = derivedWords.size() > 0;

        softAssert.assertTrue(outputPowerOfTwo, "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ", priority = 9,
            dataProvider= "ValidationProvider", dataProviderClass= ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3) throws HandlerException {
        List<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        boolean validationStringFound1 = derivedWords.contains(valStr1);
        softAssert.assertTrue(validationStringFound1, "Derived Word was found in Dictionary");

        boolean validationStringFound2 = derivedWords.contains(valStr2);
        softAssert.assertTrue(validationStringFound2,"Derived Word was found in Dictionary");

        boolean validationStringFound3 = derivedWords.contains(valStr3);
        softAssert.assertTrue(validationStringFound3, "Derived Word was found in Dictionary");
    }

    @Test(description = "Validate a lowercase String gets converted into a UpperCase string", priority = 10,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void validateLowercaseToUppercaseConversion(String wordTest) throws HandlerException {
        dictionary.getEnglishWordsFromString(wordTest);

        boolean upperCaseValidation1 = dictionary.containsString(UPPERCASE_TEST_WORD);
        softAssert.assertTrue(upperCaseValidation1, "KING Word was found in Dictionary");

        boolean upperCaseValidation2 = dictionary.containsString(CAPITALIZE_TEST_WORD);
        softAssert.assertTrue(upperCaseValidation2, "King Word was found in Dictionary");

        boolean upperCaseValidation3 = dictionary.containsString(LOWERCASE_TEST_WORD);
        softAssert.assertTrue(upperCaseValidation3, "king Word was found in Dictionary");
    }

    @Test(description = "Validate Maximum String length based of the memory capacity available", priority = 11)
    public void longStringToTestMemoryCapacityValidation () throws HandlerException {
        int maxStringLength = dictionary.getMaxStringLengthInMemory();
        String maxValidString = dictionary.getRandomStringOfSize(maxStringLength);

        List<String> maxDictionary = dictionary.getEnglishWordsFromString(maxValidString);

        boolean isMaxDictionaryValid = !maxDictionary.isEmpty();
        softAssert.assertTrue(isMaxDictionaryValid);
    }

    @Test(description = "Validate Overpassed String length based of the memory capacity available",
            timeOut = 5000, expectedExceptions = ThreadTimeoutException.class, priority = 12)
    public void stringOverpassedMemoryCapacityValidation() throws HandlerException {
        int maxStringLength = dictionary.getMaxStringLengthInMemory();
        String overpassedString = dictionary.getRandomStringOfSize(maxStringLength + 4);

        List<String> overpassedDictionary = dictionary.getEnglishWordsFromString(overpassedString);

        boolean isOverpassedDictionaryValid = !overpassedDictionary.isEmpty();

        softAssert.assertTrue(isOverpassedDictionaryValid);
    }
}
