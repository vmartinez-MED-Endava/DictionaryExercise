package com.endava.workshop.services;

import com.endava.workshop.data.dictionary.EnglishDictionary;
import com.endava.workshop.utils.exceptions.EmptyInputParameterException;
import com.endava.workshop.utils.helper.StringManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary {

    EnglishDictionary englishDictionary;
    StringManager stringManager;


    public Dictionary(){
        this.englishDictionary = new EnglishDictionary();
        this.stringManager = new StringManager();
    }

    /**
     * Method to return the List of valid English words derived from the Original String
     * @param originalString
     * @return
     */
    public List<String> getEnglishWordsFromString(String originalString) { //Unique Public

        String filteredStr = cleanString(originalString);
        if(filteredStr==null) return null;

        List<String> stringSubsetsList = stringManager.getStringSubsets(filteredStr);
        List<String> englishWords = filterEnglishWords(stringSubsetsList);

        return englishWords;
    }

    /**
     * Method to clean the String passed by parameter from
     * special characters and numbers
     * @param str
     * @return
     */
    private String cleanString(String str){

        String filteredWord = stringManager.getOnlyAlphabetLetters(str);

        try {
            if (filteredWord == "" || filteredWord.equals(null)) {
                throw new EmptyInputParameterException(new Exception());
            }
        }catch (EmptyInputParameterException exception){
            return null;
        }

        return filteredWord;
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
     * Method to validate if the String parameters corresponds to an English word
     * Validation is carried out by a quick search over an English dictionary.
     * @param word
     * @return
     */
    private boolean isEnglishWord(String word) {
        return englishDictionary.isValidWord(word);
    }









}
