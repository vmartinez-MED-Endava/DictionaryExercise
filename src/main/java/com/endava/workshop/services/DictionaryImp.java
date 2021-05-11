package com.endava.workshop.services;

import com.endava.workshop.utils.exceptions.HandlerException;
import java.util.Set;

/**
 * Dictionary interface for declaring the main methods on a Dictionary service for communicating with external entities
 *
 * getEnglishWordsFromString(String) : should return a Set<String> which is a dictionary representation of a local instance
 * isEnglishWord(String) : Return true if the passed String belongs to the Local dictionary
 */
public interface DictionaryImp {
    Set<String> getEnglishWordsFromString(String originalString) throws HandlerException;
    boolean isEnglishWord(String word);
}
