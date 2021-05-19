package com.endava.workshop.suites;

import com.endava.workshop.data.dataprovider.SearchDataProvider;
import com.endava.workshop.data.dataprovider.ValidationDataProvider;
import com.endava.workshop.services.Dictionary;
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
    private final String THREE_SPACED_EMPTY_STRING = "   ";
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

    @Test(description = "Validate Dictionary output when null is typed as an input parameter", expectedExceptions = NullPointerException.class)
    public void nullInputDictionaryValidation() {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(NULL_OBJECT);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when an Empty Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class)
    public void noInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a space Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class)
    public void spaceCharInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_SPACED_EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Three empty space Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class)
    public void threeEmptySpaceCharInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(THREE_SPACED_EMPTY_STRING);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a unique special character Input is passed by parameter",
            expectedExceptions = IllegalArgumentException.class)
    public void specialCharacterInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(SPECIAL_CHARACTER);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output when a Two special characters Input are passed by parameter",
            expectedExceptions = IllegalArgumentException.class)
    public void multipleSpecialCharactersInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(TWO_SPECIAL_CHARACTERS);

        softAssert.assertNull(derivedWords, "The list of English Words is null");
    }

    @Test(description = "Validate Dictionary output with random valid input")
    public void twoValidCharsInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(TWO_VALID_CHARACTERS);

        softAssert.assertTrue(derivedWords.isEmpty(), "Not words could be inferred from just two valid characters");
    }

    @Test(description = "Validate Dictionary output with long random input with spaces in it ",
            expectedExceptions = IllegalArgumentException.class)
    public void invalidLongCharsInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_RANDOM_SENTENCE_WITH_SPACES);

        softAssert.assertTrue(!derivedWords.isEmpty(), "The list of English Words is not empty");
    }

    @Test(description = "Validate Dictionary output with long random input without spaces in it ")
    public void longCharsInputOnDictionaryValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();

        softAssert.assertTrue(derivedWords.containsAll(dictionaryWords), "The algorithm could inferred all of the dictionary words");
    }

    @Test(description = "Validate English Dictionary Words could be inferred from the algorithm output ")
    public void inferenceEnglishDictionaryDatasetValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis("WORKING");
        Set<String> testDataSet = new HashSet<>(Set.of("WORK", "KING", "ROW", "RING", "KNOW"));

        softAssert.assertTrue(derivedWords.containsAll(testDataSet), "Recursive method was not able to replicate all of the WORDS");
    }

    @Test(description = "Validate Dictionary output with default wordTest",
            dataProvider = "StringProvider", dataProviderClass = SearchDataProvider.class)
    public void predeterminedWordInputOnDictionaryValidation(String wordTest)  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(wordTest);

        softAssert.assertTrue(derivedWords.containsAll(dictionary.getEnglishDictionary().getDictionary()), "The list of English Words is not empty");
    }

    @Test(description = "Validate expected derived words were stored in Dictionary ",
            dataProvider = "ValidationProvider", dataProviderClass = ValidationDataProvider.class)
    public void expectedDerivedWordsOnDictionaryValidation(String wordTest, String valStr1, String valStr2, String valStr3, String valStr4)  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(wordTest);

        softAssert.assertTrue(dictionary.containsString(valStr1), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr2), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr3), "Test Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(valStr4), "Test Word was found in Dictionary");
    }

    @Test(description = "Validate all of the contained words in English Dictionary were derived via algorithm ")
    public void inferenceWordsValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(ONE_RANDOM_SENTENCE_WITHOUT_SPACES);
        Set<String> dictionaryWords = dictionary.getEnglishDictionary().getDictionary();

        softAssert.assertEquals(derivedWords, dictionaryWords);
    }

    @Test(description = "Validate a lowercase String gets converted into a UpperCase string",
            dataProvider = "StringProvider", dataProviderClass = SearchDataProvider.class)
    public void validateLowercaseToUppercaseConversion(String wordTest)  {
        dictionary.getEnglishWordsFromStringHis(wordTest);

        softAssert.assertTrue(dictionary.containsString(UPPERCASE_TEST_WORD), "KING Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(CAPITALIZE_TEST_WORD), "King Word was found in Dictionary");

        softAssert.assertTrue(dictionary.containsString(LOWERCASE_TEST_WORD), "king Word was found in Dictionary");
    }

    @Test(description = "Validate Maximum String length based of the memory capacity available")
    public void longStringToTestMemoryCapacityValidation()  {
        int maxStringLength = dictionary.getMaxStringLengthInMemory();
        String maxValidString = dictionary.getRandomStringOfSize(maxStringLength);
        Set<String> maxDictionary = dictionary.getEnglishWordsFromStringHis(maxValidString);

        softAssert.assertTrue(
                maxDictionary.containsAll(dictionary.getEnglishDictionary().getDictionary()),
                "Max dictionary contains all of the words from the English dictionary"
        );
    }

    @Test(description = "Validate Overpassed String length based of the memory capacity available")
    public void stringOverpassedMemoryCapacityValidation()  {
        String overpassedString = dictionary.getRandomStringOfSize(MAX_VALID_STRING_LENGTH);
        Set<String> overpassedDictionary = dictionary.getEnglishWordsFromStringHis(overpassedString);

        softAssert.assertTrue(!overpassedDictionary.isEmpty(), "Overpassed Dictionary was created as expected");
    }

    @Test(description = "Validate if Numbers passed by parameter belong to English Dictionary",
            expectedExceptions = IllegalArgumentException.class)
    public void inputStringContainNumbersValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(NUMBERS_STRING);

        softAssert.assertNull(derivedWords, "The collection of English words is not empty");
    }

    @Test(description = " Validate whether two repeated valid words can be recognized as one valid word in the Dictionary")
    public void twoRepeatedValidStringsValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(REPEATED_VALID_STRINGS);

        softAssert.assertTrue(!derivedWords.isEmpty(), "The collection of English words is not empty");
    }

    @Test(description = " Validate whether a really big String can be passed to the algorithm - 20 characters", timeOut = 5000)
    public void reallyBigStringOutputValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(BIG_TEST_WORD);

        softAssert.assertEquals(derivedWords, dictionary.getEnglishDictionary().getDictionary());
    }

    @Test(description = " Validate a String can not be longer thant 26-characters String ",
            expectedExceptions = IllegalArgumentException.class)
    public void exceedInputStringLegalLengthOutputValidation()  {
        Set<String> derivedWords = dictionary.getEnglishWordsFromStringHis(dictionary.getRandomStringOfSize(27));

        softAssert.assertEquals(derivedWords, dictionary.getEnglishDictionary().getDictionary());
    }
}

