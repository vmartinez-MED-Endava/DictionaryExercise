package com.endava.workshop.services;

import com.endava.workshop.utils.exceptions.EmptyInputParameterException;
import com.endava.workshop.utils.exceptions.HandlerException;

import java.util.List;

/**
 * BasePage containing the minimum requirement from Client
 */
public abstract class BasePage {
    abstract List<String> getEnglishWordsFromString(String originalString) throws EmptyInputParameterException, HandlerException;
    abstract  boolean isEnglishWord(String word);
}
