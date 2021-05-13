package com.endava.workshop.suites;

import com.endava.workshop.data.dataprovider.SearchDataProvider;
import com.endava.workshop.data.dataprovider.ValidationDataProvider;
import com.endava.workshop.services.Dictionary;
import com.endava.workshop.utils.exceptions.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test methods for validating the Letter-Frequency Approach
 * This method does not calculate all the combinations of the original word but the required letters from each dictionary-word to replicate it.
 * Test cases include Input parameter validation, Big input validation and Time execution validation
 */
public class HistogramDictionaryTest extends BaseTest {

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
    private final String NUMBERS_STRING = "12345678";
    private final String REPEATED_VALID_STRINGS = "RORO";
    private final String BIG_TEST_WORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int MAX_VALID_STRING_LENGTH = 25;


    @BeforeClass(description = "Initialize the Dictionary for executing our Tests")
    public void loadDictionary() {
        dictionary = new Dictionary();
    }

    @Test(description = "Validate Dictionary output when null is typed as an input parameter", expectedExceptions = NullMethodParameterException.class, priority = 1)
    public void nullInputDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(NULL_OBJECT);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter",
            expectedExceptions = EmptyInputParameterException.class, priority = 2)
    public void noInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter",
            expectedExceptions = IncorrectInputParameterException.class, priority = 3)
    public void spaceCharInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_SPACED_EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Three empty space Input is passed by parameter",
            expectedExceptions = EmptyInputParameterException.class, priority = 4)
    public void threeEmptySpaceCharInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter",
            expectedExceptions = IncorrectInputParameterException.class, priority = 5)
    public void specialCharacterInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(SPECIAL_CHARACTER);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter",
            expectedExceptions = IncorrectInputParameterException.class, priority = 6)
    public void multipleSpecialCharactersInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(TWO_SPECIAL_CHARACTERS);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input", priority = 7)
    public void twoValidCharsInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(TWO_VALID_CHARACTERS);

        softAssert.assertTrue(derivedWords.isEmpty(), "Not words could be inferred from just two valid characters");
    }

    @Test(description = "Validate Dictionary output with long random input with spaces in it ",
            expectedExceptions = IncorrectInputParameterException.class, priority = 8)
    public void invalidLongCharsInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_RANDOM_SENTENCE_WITH_SPACES);

        softAssert.assertTrue(!derivedWords.isEmpty(), "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with long random input without spaces in it ", priority = 9)
    public void longCharsInputOnDictionaryValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();

        softAssert.assertTrue(derivedWords.containsAll(dictionaryWords), "The algorithm could inferred all of the dictionary words");
    }

    @Test(description = "Validate English Dictionary Words could be inferred from the algorithm output ", priority = 10)
    public void inferenceEnglishDictionaryDatasetValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis("WORKING");
        Set<String> testDataSet = new HashSet<>(Set.of("WORK", "KING", "ROW", "RING", "KNOW"));

        softAssert.assertTrue(derivedWords.containsAll(testDataSet), "Recursive method was not able to replicate all of the WORDS");
    }

    @Test(description = "Validate Dictionary output with default wordTest", priority = 11,
            dataProvider = "StringProvider", dataProviderClass = SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest) throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(wordTest);

        softAssert.assertTrue(derivedWords.containsAll(dictionary.getEnglishDictionary().getDictionary()), "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ", priority = 12,
            dataProvider = "ValidationProvider", dataProviderClass = ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3, String valStr4) throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(wordTest);

        softAssert.assertTrue(dictionary.containsString(valStr1), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr2), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr3), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr4), "Test Word was found in Dictionary");
    }

    @Test(description = "Validate all of the contained words in English Dictionary were derived via algorithm ", priority = 13)
    public void inferenceWordsValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();

        softAssert.assertEquals(derivedWords, dictionaryWords);
    }

    @Test(description = "Validate a lowercase String gets converted into a UpperCase string", priority = 14,
            dataProvider = "StringProvider", dataProviderClass = SearchDataProvider.class)
    public void validateLowercaseToUppercaseConversion(String wordTest) throws HandlerException {
        dictionary.getEnglishWordsFromStringHis(wordTest);

        softAssert.assertTrue(dictionary.containsString(UPPERCASE_TEST_WORD), "KING Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(CAPITALIZE_TEST_WORD), "King Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(LOWERCASE_TEST_WORD), "king Word was found in Dictionary");
    }

    @Test(description = "Validate Maximum String length based of the memory capacity available", priority = 15)
    public void longStringToTestMemoryCapacityValidation() throws HandlerException {
        int maxStringLength = dictionary.getMaxStringLengthInMemory();
        String maxValidString = dictionary.getRandomStringOfSize(maxStringLength);
        Set<String> maxDictionary = dictionary.getEnglishWordsFromStringHis(maxValidString);

        softAssert.assertTrue(
                maxDictionary.containsAll(dictionary.getEnglishDictionary().getDictionary()),
                "Max dictionary contains all of the words from the English dictionary"
        );
    }

    @Test(description = "Validate Overpassed String length based of the memory capacity available", priority = 16)
    public void stringOverpassedMemoryCapacityValidation() throws HandlerException {
        String overpassedString = dictionary.getRandomStringOfSize(MAX_VALID_STRING_LENGTH);
        Set<String> overpassedDictionary = dictionary.getEnglishWordsFromStringHis(overpassedString);

        softAssert.assertTrue(!overpassedDictionary.isEmpty(), "Overpassed Dictionary was created as expected");
    }

    @Test(description = "Validate if Numbers passed by parameter belong to English Dictionary",
            expectedExceptions = IncorrectInputParameterException.class, priority = 17)
    public void inputStringContainNumbersValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(NUMBERS_STRING);

        softAssert.assertNull(derivedWords, "The collection of English words is not empty");
    }

    @Test(description = " Validate whether two repeated valid words can be recognized as one valid word in the Dictionary", priority = 18)
    public void twoRepeatedValidStringsValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(REPEATED_VALID_STRINGS);

        softAssert.assertTrue(!derivedWords.isEmpty(), "The collection of English words is not empty");
    }

    @Test(description = " Validate whether a really big String can be passed to the algorithm - 20 characters", priority = 19, timeOut = 5000)
    public void reallyBigStringOutputValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(BIG_TEST_WORD);

        softAssert.assertEquals(derivedWords, dictionary.getEnglishDictionary().getDictionary());
    }

    @Test(description = " Validate a String can not be longer thant 26-characters String ", priority = 20, expectedExceptions = InputExcededLengthLimitException.class)
    public void exceedInputStringLegalLengthOutputValidation() throws HandlerException {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(dictionary.getRandomStringOfSize(27));

        softAssert.assertEquals(derivedWords, dictionary.getEnglishDictionary().getDictionary());
    }
}

