package com.endava.workshop.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnglishDictionary implements BaseDictionary {

    private Set<String> localDictionary;

    public EnglishDictionary() {
        this.localDictionary = createDictionary();
    }

    /**
     * Method to create an English Dictionary based of some predefined words;
     * @return
     */
    public Set<String> createDictionary(){

        Set<String> newDictionary = new HashSet<String>(
                Arrays.asList(
                    "RIG", "OR", "OK", "ORG", "WIN", "KING",
                    "WORKING", "WON", "ONG", "WORK", "IN", "RING"
                ));

       return newDictionary;
    }

    /**
     * Method to valida if a word belongs to the English Dictionary
     * @param word
     * @return
     */
    public boolean isValidWord(String word){
        String wordUpperCase = word.toUpperCase();
        return this.localDictionary.contains(wordUpperCase);
    }

    /**
     * Method to update the current dictionary instance
     * @param newDictionary
     */
    public void updateDictionary(Set<String> newDictionary){
        this.localDictionary = newDictionary;
    }

    /**
     * Method to remove the actual dictionary instance
     */
    public void removeDictionary(){
        this.localDictionary.clear();
    }

    /**
     * Method to obtain the current instance of the dictionary.
     * @return
     */
    public Set<String> getDictionary(){
        return this.localDictionary;
    }
}
