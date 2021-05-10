package com.endava.workshop.services;

import com.endava.workshop.data.dictionary.EnglishDictionary;
import com.endava.workshop.utils.exceptions.EmptyInputParameterException;
import com.endava.workshop.utils.exceptions.HandlerException;
import com.endava.workshop.utils.helper.StringManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Dictionary Class
 * Validates if a certain String belongs to English grammar.
 */
public class Dictionary extends BasePage{

    EnglishDictionary englishDictionary;
    StringManager stringManager;
    List<String> englishWordsList;
    Logger logger;

    /**
     * Method  to Initialize English Dictionary and String Manager. The latter will provide custom methods for
     * Strings processing.
     */
    public Dictionary(){
        logger = LogManager.getLogger(Dictionary.class);
        this.englishDictionary = new EnglishDictionary();
        this.stringManager = new StringManager();
        this.englishWordsList = new ArrayList<String>();
    }

    /**
     * Method to return the List of valid English words derived from the Original String
     * @param originalString
     * @return
     */
    public List<String> getEnglishWordsFromString(String originalString) throws HandlerException {
        String filteredStr = cleanString(originalString);

        List<String> stringSubsetsList = stringManager.getStringSubsets(filteredStr);
        this.englishWordsList = filterEnglishWords(stringSubsetsList);

        return englishWordsList;
    }

    /**
     * Method to clean the String passed by parameter from
     * special characters and numbers
     * @param str
     * @return
     */
    private String cleanString(String str) throws HandlerException {
        String filteredWord = stringManager.getOnlyAlphabetLetters(str);

        if (filteredWord == "" || filteredWord.equals(null)) {
            throw new EmptyInputParameterException();
        }

        return filteredWord;
    }

    /**
     * Method to validate if a List contains the Specified String passed by parameter
     * @param str
     * @return
     */
    public boolean containsString(String str){
        String toUpper = str.toUpperCase();
        logger.info("Validating if " + str + " belongs to dictionary");
        return this.englishWordsList.contains(toUpper);
    }

    /**
     * Method to filter out duplicates and not English words
     * @param lst
     * @return
     */
    private List<String> filterEnglishWords(List<String> lst) {
        Set<String> uniqueWordsSet = new HashSet<String>(lst);

        return uniqueWordsSet
                .stream()
                .filter(  word-> isEnglishWord(word) )
                .collect(Collectors.toList());
    }

    /**
     * Method to return the max string length allowed in a JVM based on the memory available
     * The calculation is gained after applying logarithm in base 2 to the current memory space.
     *
     * @return Max String length permitted in JVM before throwing a Memory Exception
     */
    public int getMaxStringLengthInMemory(){
        long availableMemory = Runtime.getRuntime().maxMemory();
        logger.info(" Available memory :" + availableMemory + " bytes.");

        int maxString = (int) Math.floor(Math.log(availableMemory));
        logger.info(" Estimated max String length " + maxString);

        return maxString;
    }

    /**
     * Method to return a Random String of a given size
     * This method uses the String Manager method for creating the string.
     * No Special nor numeric characters are included in the returned string.
     *
     * @param stringSize (int) : Size of the expected output random string
     * @return a random string of size stringSize
     */
    public String getRandomStringOfSize(int stringSize){
        String generated = stringManager.generateRandomStringOfSize(stringSize);
        logger.info(" - Generating a random String  - output : " + generated);

        return  generated;
    }

    /**
     * Method to validate if the String parameters corresponds to an English word
     * Validation is carried out by a quick search over an English dictionary.
     * @param word
     * @return
     */
    @Override
    boolean isEnglishWord(String word) {
        return englishDictionary.isValidWord(word);
    }
}
