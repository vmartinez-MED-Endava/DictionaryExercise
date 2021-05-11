package com.endava.workshop.services;

import com.endava.workshop.data.dictionary.EnglishDictionary;
import com.endava.workshop.utils.exceptions.EmptyInputParameterException;
import com.endava.workshop.utils.exceptions.HandlerException;
import com.endava.workshop.utils.helper.StringManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Dictionary Class Implements the required methods defined in DictionaryImp
 * In overall validates using a limited dictionary if a certain word belongs to English language
 * Also returns a dictionary, in this context a set of strings, with some of the English words
 */
public class Dictionary implements DictionaryImp{

    private EnglishDictionary englishDictionary;
    private StringManager stringManager;
    private Set<String> englishWordsList;
    private Logger logger;


    /**
     *  A Dictionary
     * @param englishDictionary
     * @param stringManager
     * @param englishWordsList
     * @param logger
     */
    public Dictionary(EnglishDictionary englishDictionary, StringManager stringManager, Set<String> englishWordsList, Logger logger) {
        this.englishDictionary = englishDictionary;
        this.stringManager = stringManager;
        this.englishWordsList = englishWordsList;
        this.logger = logger;
    }

    /**
     *
     * @param englishDictionary
     * @param stringManager
     */
    public Dictionary(EnglishDictionary englishDictionary, StringManager stringManager) {
        this.englishDictionary = englishDictionary;
        this.stringManager = stringManager;
    }

    public Dictionary(Set<String> englishWordsList) {
        this.englishWordsList = englishWordsList;
    }

    public Dictionary(EnglishDictionary englishDictionary, StringManager stringManager, Set<String> englishWordsList) {
        this.englishDictionary = englishDictionary;
        this.stringManager = stringManager;
        this.englishWordsList = englishWordsList;
    }



    /**
     * Method  to Initialize English Dictionary and String Manager. The latter will provide custom methods for
     * Strings processing.
     */
    public Dictionary(){
        logger = LogManager.getLogger(Dictionary.class);
        this.englishDictionary = new EnglishDictionary();
        this.stringManager = new StringManager();
        this.englishWordsList = new HashSet<>();
    }

    /**
     * Method to return the List of valid English words derived from the Original String
     * @param originalString
     * @return
     */
    public Set<String> getEnglishWordsFromString(String originalString) throws HandlerException {
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
    private Set<String> filterEnglishWords(List<String> lst) {
        Set<String> uniqueWordsSet = new HashSet<String>(lst);

        return uniqueWordsSet
                .stream()
                .filter(  word-> isEnglishWord(word) )
                .collect(Collectors.toSet());
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
    public boolean isEnglishWord(String word) {
        return englishDictionary.isValidWord(word);
    }


    public EnglishDictionary getEnglishDictionary() {
        return englishDictionary;
    }

    public void setEnglishDictionary(EnglishDictionary englishDictionary) {
        this.englishDictionary = englishDictionary;
    }

    public StringManager getStringManager() {
        return stringManager;
    }

    public void setStringManager(StringManager stringManager) {
        this.stringManager = stringManager;
    }

    public Set<String> getEnglishWordsList() {
        return englishWordsList;
    }

    public void setEnglishWordsList(Set<String> englishWordsList) {
        this.englishWordsList = englishWordsList;
    }

}
