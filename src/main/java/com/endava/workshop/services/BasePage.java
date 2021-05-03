package com.endava.workshop.services;

import java.util.List;

/**
 * BasePage containing the minimum requirement from Client
 */
public abstract class BasePage {
    abstract List<String> getEnglishWordsFromString(String originalString);
    abstract  boolean isEnglishWord(String word);
}
