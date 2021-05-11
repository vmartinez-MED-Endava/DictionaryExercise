package com.endava.workshop.data.dictionary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Dictionary Implementation for an English Dictionary instance
 * This class implements BaseDictionary interface in order to provide a minimum set of methods inherent to a Dictionary scheme
 * Dictionary in this context refers to a limited set of words
 */
public class EnglishDictionary implements BaseDictionary {

    private Set<String> localDictionary;


    /**
     * Constructor for creating a Dictionary based on a local Dictionary formed of a Set of Strings
     * Dictionary in this case  is composed by a set of Strings
     *
     * @param localDictionary
     */
    public EnglishDictionary(Set<String> localDictionary){
        this.localDictionary = localDictionary;
    }


    /**
     * Constructor for initilizing a Dictionary
     * High coupling constructor for initializing a local Dictionary based on a default set of Strings
     * The default set of Strings is very limited as no more than 20 strings were defined for it
     */
    public EnglishDictionary() {
        this.localDictionary = createDictionary();
    }

    /**
     * Method to create and return an English Dictionary represented with a set of predefined strings
     * The dictionary is composed by a set of strings limited up to 20 object instances
     * All instances in local dictionary are derived words from the root word, i.e. Working, for this particular example
     * @return Set<String>
     */
    public Set<String> createDictionary(){

        Set<String> newDictionary = new HashSet<String>(
                Arrays.asList(
                    "RIG", "OR", "OK", "ORG", "WIN", "KING",
                    "WORKING", "WON", "ONG", "WORK", "IN", "RING", "ROW"
                ));

       return newDictionary;
    }

    /**
     * Method to validate if a word belongs to the English Dictionary
     * The validation is carried out after checking if the string passed by parameter is in the local dictionary which
     * is a set instance of predefined strings
     *
     * @param word (String) : String to be validated inside the dictionary
     * @return boolean : Returns true if the word is in the local dictionary (represented by a set of strings)
     * Returns false if the "word" does not belong to the local dictionary
     */
    public boolean isValidWord(String word){
        String wordUpperCase = word.toUpperCase();
        return this.localDictionary.contains(wordUpperCase);
    }

    /**
     * Method to update the current dictionary instance
     * The local dictionary, represented by a set of strings, is updated after passing a new set of strings by parameter
     *
     * @param newDictionary (Set<String>) Set of Strings to replace the old local dictionary instance
     */
    public void updateDictionary(Set<String> newDictionary){
        this.localDictionary = newDictionary;
    }

    /**
     * Method to remove the actual dictionary instance
     * The current local dictionary instance, which is represented by a set of strings,  will be wiped out after calling this method
     * All of his elements will be removed from memory
     */
    public void removeDictionary(){
        this.localDictionary.clear();
    }

    /**
     * Method to return the current instance of the dictionary,
     * @return set<Strings> : A set of Strings instance, representing the local dictionary will be returned
     * If no local dictionary has been set up, a null object will be returned
     */
    public Set<String> getDictionary(){
        return this.localDictionary;
    }
}
