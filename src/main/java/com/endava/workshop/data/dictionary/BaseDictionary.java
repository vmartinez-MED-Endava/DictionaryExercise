package com.endava.workshop.data.dictionary;

import java.util.Set;

/**
 * General scheme for dictating the common methods and attributes a dictionary should provide
 *
 * A Dictionary in this context is a very limited set of words.No definition is provided next to the word and
 * if the word in question belongs to the set, it is assummed the word belongs to the defined language. Otherwise it is
 * assumed the word does not belong to the language.
 */
public interface BaseDictionary {

    /**
     * Create a Dictionary Instance
     * Instatiate proper drivers for returning the Dictionary Collection
     *
     * @return A set of Words contained in the dictionary.
     */
    public abstract Set<String> createDictionary();

    /**
     * Update current dictionary by passing a new set of Words
     * @param newDictionary : Set<String> A new set of words that will properly update the current dictionary
     * No return statement.
     */
    public abstract void updateDictionary(Set<String> newDictionary);

    /**
     * Remove current Dictionary instance, this to say the stored words were cleared.
     * At this point the dictionary does not contain any word available.
     */
    public abstract void removeDictionary();

    /**
     * Returns the current Dictionary instance, represented via a set of words
     * If no instance of the set has been set, the null object will be returned.
     * @return
     */
    public abstract Set<String> getDictionary();

    /**
     * Validate if the current word belongs to the abstraction of a Dictionary represented by the Set of Words.
     * The validation is achieved by validating if the tested word is found inside Collection.
     * @param word (String) : A String of length up to 22 characters
     * @return boolean : true if the word belongs to the limited set, false otherwise.
     */
    public abstract boolean isValidWord(String word);

}
