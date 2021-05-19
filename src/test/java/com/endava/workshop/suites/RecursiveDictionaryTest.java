package com.endava.workshop.suites;

import com.endava.workshop.data.dataprovider.SearchDataProvider;
import com.endava.workshop.data.dataprovider.ValidationDataProvider;
import com.endava.workshop.services.Dictionary;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import java.util.HashSet;
import java.util.Set;

/**
 * Test methods for validating the Divide and Conque Approach
 * This method does calculate the combinations of the original word for later validating against an English dictionary collection
 * Test cases include Input parameter validation, Big input validation and Time execution validation
 */
public class RecursiveDictionaryTest extends BaseTest{

    Dictionary dictionary;
    private final String NULL_OBJECT = null;
    private final String EMPTY_STRING = "";
    private final String ONE_SPACED_EMPTY_STRING = " ";
    private final String THREE_SPACED_EMPTY_STRING = "   ";
    private final String SPECIAL_CHARACTER = "!";
    private final String TWO_SPECIAL_CHARACTERS = "! [";
    private final String TWO_VALID_CHARACTERS = "ab";
    private final String ONE_RANDOM_SENTENCE_WITH_SPACES = "wko rkn igw rti kng";
    private final String ONE_RANDOM_SENTENCE_WITHOUT_SPACES = "wkorknigwrong";
    private final String LOWERCASE_TEST_WORD = "king";
    private final String CAPITALIZE_TEST_WORD = "King";
    private final String UPPERCASE_TEST_WORD = "KING";
    private final String NUMBERS_STRING ="12345678";
    private final String REPEATED_VALID_STRINGS = "RORO";
    private final String BIG_TEST_WORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int MAX_String_Length = 25;


    @BeforeClass(description = "Initialize the Dictionary for executing our Tests")
    public void loadDictionary(){
        dictionary = new Dictionary();
    }

    @Test(description = "Validate Dictionary output when null is typed as an input parameter", expectedExceptions = NullPointerException.class, priority = 1)
    public void nullInputDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(NULL_OBJECT);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class, priority = 2)
    public void noInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class, priority = 3)
    public void spaceCharInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(ONE_SPACED_EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Three empty space Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class, priority = 4)
    public void threeEmptySpaceCharInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(THREE_SPACED_EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class, priority = 5)
    public void specialCharacterInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(SPECIAL_CHARACTER);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter",
            expectedExceptions = IllegalArgumentException.class, priority = 6)
    public void multipleSpecialCharactersInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(TWO_SPECIAL_CHARACTERS);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input", priority = 7)
    public void twoValidCharsInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(TWO_VALID_CHARACTERS);

        softAssert.assertTrue(derivedWords.isEmpty(), "Not words could be inferred from just two valid characters" );
    }

    @Test(description = "Validate Dictionary output with long random input with spaces in it ",
            expectedExceptions = IllegalArgumentException.class, priority = 8)
    public void invalidLongCharsInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(ONE_RANDOM_SENTENCE_WITH_SPACES);

        softAssert.assertTrue( derivedWords.isEmpty(), "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with long random input without spaces in it ", priority = 9)
    public void longCharsInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();

        softAssert.assertTrue(derivedWords.containsAll(dictionaryWords), "The algorithm could inferred all of the dictionary words");
    }

    @Test(description = "Validate English Dictionary Words could not be inferred from the algorithm output ", priority = 10)
    public void inferenceEnglishDictionaryDatasetValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec("WORKING");
        Set<String> testDataSet = new HashSet<>(Set.of("WORK", "KING", "ROW", "RING", "KNOW"));

        softAssert.assertFalse(derivedWords.containsAll(testDataSet), "Recursive method was not able to replicate all of the WORDS");
    }

    @Test(description = "Validate Dictionary output with default wordTest - Not all of the words could be inferred", priority = 11,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest)  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(wordTest);

        softAssert.assertFalse(derivedWords.containsAll(dictionary.getEnglishDictionary().getDictionary()), "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ", priority = 12,
            dataProvider= "ValidationProvider", dataProviderClass= ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3, String valStr4)  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(wordTest);

        softAssert.assertTrue(dictionary.containsString(valStr1), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr2),"Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr3), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr4), "Test Word was found in Dictionary");
    }

    @Test(description = "Validate all of the contained words in English Dictionary were derived via algorithm ", priority = 13)
    public void inferenceWordsValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();


        softAssert.assertEquals(derivedWords, dictionaryWords);
    }

    @Test(description = "Validate a lowercase String gets converted into a UpperCase string", priority = 14,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void validateLowercaseToUppercaseConversion(String wordTest)  {
        dictionary.getEnglishWordsFromStringRec(wordTest);

        softAssert.assertTrue( dictionary.containsString(UPPERCASE_TEST_WORD), "KING Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(CAPITALIZE_TEST_WORD), "King Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(LOWERCASE_TEST_WORD), "king Word was found in Dictionary");
    }

    @Test(description = "Validate Maximum String length based of the memory capacity available", priority = 15)
    public void longStringToTestMemoryCapacityValidation ()  {
        int maxStringLength = dictionary.getMaxStringLengthInMemory();
        String maxValidString = dictionary.getRandomStringOfSize(maxStringLength);
        Set<String> maxDictionary = dictionary.getEnglishWordsFromStringRec(maxValidString);

        softAssert.assertTrue(
                maxDictionary.containsAll(dictionary.getEnglishDictionary().getDictionary()),
                "Max dictionary contains all of the words from the English dictionary"
        );
    }

    @Test(description = "Validate Overpassed String length based of the memory capacity available",
            timeOut = 5000, expectedExceptions = ThreadTimeoutException.class, priority = 16)
    public void stringOverpassedMemoryCapacityValidation()  {
        int maxStringLength = dictionary.getMaxStringLengthInMemory() * 2;
        String overpassedString = dictionary.getRandomStringOfSize(MAX_String_Length);
        Set<String> overpassedDictionary = dictionary.getEnglishWordsFromStringRec(overpassedString);

        softAssert.assertTrue( !overpassedDictionary.isEmpty(), "Overpassed Dictionary was created as expected");
    }

    @Test(description = "Validate if Numbers passed by parameter belong to English Dictionary",
            expectedExceptions = IllegalArgumentException.class, priority = 17)
    public void inputStringContainNumbersValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(NUMBERS_STRING);

        softAssert.assertNull(derivedWords, "The collection of English words is not empty");
    }

    @Test(description =" Validate whether two repeated valid words can be recognized as one valid word in the Dictionary", priority = 18)
    public void twoRepeatedValidStringsValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(REPEATED_VALID_STRINGS);

        softAssert.assertTrue(!derivedWords.isEmpty(), "The collection of English words is not empty");
    }

    @Test(description =" Validate whether a really big String can be passed to the algorithm - 20 characters", priority = 19,
            timeOut = 5000, expectedExceptions = ThreadTimeoutException.class)
    public void reallyBigStringOutputValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(BIG_TEST_WORD);

        softAssert.assertEquals(derivedWords,dictionary.getEnglishDictionary().getDictionary());
    }

    @Test(description =" Validate a String can not be longer thant 26-characters String ", priority = 20, expectedExceptions = IllegalArgumentException.class)
    public void exceedInputStringLegalLengthOutputValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringRec(dictionary.getRandomStringOfSize(27));

        softAssert.assertEquals(derivedWords,dictionary.getEnglishDictionary().getDictionary());
    }
}
