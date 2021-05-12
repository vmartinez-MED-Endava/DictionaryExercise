package com.endava.workshop.suites;

import com.endava.workshop.data.dataprovider.SearchDataProvider;
import com.endava.workshop.data.dataprovider.ValidationDataProvider;
import com.endava.workshop.services.Dictionary;
import com.endava.workshop.utils.exceptions.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import java.util.HashSet;
import java.util.Set;


public class DictionaryTest extends BaseTest{

    Dictionary dictionary;
    private final String NULL_OBJECT = null;
    private final String EMPTY_STRING = "";
    private final String ONE_SPACED_EMPTY_STRING = " ";
    private final String THREE_SPACED_EMPTY_STRING = " ";
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

    @BeforeClass(description = "Initialize the Dictionary for executing our Tests")
    public void loadDictionary(){
        dictionary = new Dictionary();
    }


    @Test(description = "Validate Dictionary output when null is typed as an input parameter", expectedExceptions = NullMethodParameterException.class, priority = 0)
    public void nullInputDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(NULL_OBJECT);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter",
            expectedExceptions = EmptyInputParameterException.class, priority = 1)
    public void noInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter",
            expectedExceptions = IncorrectInputParameterException.class, priority = 2)
    public void spaceCharInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_SPACED_EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Three empty space Input is passed by parameter",
            expectedExceptions = EmptyInputParameterException.class, priority = 2)
    public void threeEmptySpaceCharInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter",
            expectedExceptions = IncorrectInputParameterException.class, priority = 3)
    public void specialCharacterInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(SPECIAL_CHARACTER);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter",
            expectedExceptions = IncorrectInputParameterException.class, priority = 4)
    public void multipleSpecialCharactersInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(TWO_SPECIAL_CHARACTERS);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input", priority = 5)
    public void twoValidCharsInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(TWO_VALID_CHARACTERS);

        softAssert.assertTrue(derivedWords.isEmpty(), "Not words could be inferred from just two valid characters" );
    }

    @Test(description = "Validate Dictionary output with long random input with spaces in it ",
            expectedExceptions = IncorrectInputParameterException.class, priority = 6)
    public void invalidLongCharsInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE_WITH_SPACES);

        softAssert.assertTrue( !derivedWords.isEmpty(), "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with long random input without spaces in it ", priority = 7)
    public void longCharsInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();

        softAssert.assertTrue(derivedWords.containsAll(dictionaryWords), "The algorithm could inferred all of the dictionary words");
    }

    @Test(description = "Validate English Dictionary Words could be inferred from the algorithm output ", priority = 7)
    public void inferenceEnglishDictionaryDatasetValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> testDataSet = new HashSet<>(Set.of("WORK", "KING", "ROW", "RING"));

        softAssert.assertTrue(derivedWords.containsAll(testDataSet));
    }

    @Test(description = "Validate Dictionary output with default wordTest", priority = 8,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest) throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        softAssert.assertTrue(derivedWords.containsAll(dictionary.getEnglishDictionary().getDictionary()), "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ", priority = 9,
            dataProvider= "ValidationProvider", dataProviderClass= ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3, String valStr4) throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(wordTest);

        softAssert.assertTrue(dictionary.containsString(valStr1), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr2),"Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr3), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr4), "Test Word was found in Dictionary");
    }

    @Test(description = "Validate all of the contained words in English Dictionary were derived via algorithm ", priority = 9)
    public void inferenceWordsValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();


        softAssert.assertEquals(derivedWords, dictionaryWords);
    }

    @Test(description = "Validate a lowercase String gets converted into a UpperCase string", priority = 10,
            dataProvider= "StringProvider", dataProviderClass= SearchDataProvider.class)
    public void validateLowercaseToUppercaseConversion(String wordTest) throws HandlerException {
        dictionary.getEnglishWordsFromString(wordTest);

        softAssert.assertTrue( dictionary.containsString(UPPERCASE_TEST_WORD), "KING Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(CAPITALIZE_TEST_WORD), "King Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(LOWERCASE_TEST_WORD), "king Word was found in Dictionary");
    }

    @Test(description = "Validate Maximum String length based of the memory capacity available", priority = 11)
    public void longStringToTestMemoryCapacityValidation () throws HandlerException {
        int maxStringLength = dictionary.getMaxStringLengthInMemory();
        String maxValidString = dictionary.getRandomStringOfSize(maxStringLength);
        Set<String> maxDictionary = dictionary.getEnglishWordsFromString(maxValidString);

        softAssert.assertTrue(
                maxDictionary.containsAll(dictionary.getEnglishDictionary().getDictionary()),
                "Max dictionary contains all of the words from the English dictionary"
        );
    }

    @Test(description = "Validate Overpassed String length based of the memory capacity available",
            timeOut = 5000, expectedExceptions = ThreadTimeoutException.class, priority = 12)
    public void stringOverpassedMemoryCapacityValidation() throws HandlerException {
        int maxStringLength = dictionary.getMaxStringLengthInMemory() * 2;
        String overpassedString = dictionary.getRandomStringOfSize(maxStringLength );
        Set<String> overpassedDictionary = dictionary.getEnglishWordsFromString(overpassedString);

        softAssert.assertTrue( !overpassedDictionary.isEmpty(), "Overpassed Dictionary was created as expected");
    }

    @Test(description = "Validate if Numbers passed by parameter belong to English Dictionary",
            expectedExceptions = IncorrectInputParameterException.class, priority = 13)
    public void inputStringContainNumbersValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(NUMBERS_STRING);

        softAssert.assertNull(derivedWords, "The collection of English words is not empty");
    }

    @Test(description =" Validate whether two repeated valid words can be recognized as one valid word in the Dictionary", priority = 14)
    public void twoRepeatedValidStringsValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromString(REPEATED_VALID_STRINGS);

        softAssert.assertTrue(!derivedWords.isEmpty(), "The collection of English words is not empty");
    }
}
