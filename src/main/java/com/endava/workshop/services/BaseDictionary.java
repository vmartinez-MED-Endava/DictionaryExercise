package com.endava.workshop.services;

import java.util.Set;

/**
 * Dictionary interface for declaring the main methods of a Dictionary service for connecting with external entities
 *
 * getEnglishWordsFromString(String) : should return a Set<String> which is a dictionary representation of a local instance
 * isEnglishWord(String) : Returns true if the passed String parameter belongs to the Local dictionary
 */
public interface BaseDictionary {
    Set<String> getEnglishWordsFromStringRec(String originalString);
    Set<String> getEnglishWordsFromStringHis(String originalString);
    boolean isEnglishWord(String word);
}
