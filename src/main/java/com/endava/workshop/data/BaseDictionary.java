package com.endava.workshop.data;

import java.util.Set;

public interface BaseDictionary {

    /**
     * Create a Dictionary Instance
     * Instatiate proper drivers for returning the Dictionary Collection
     * @return
     */
    public abstract Set<String> createDictionary();

    /**
     * Update current dictionary by passing a new Collection
     * @param newDictionary
     */
    public abstract void updateDictionary(Set<String> newDictionary);

    /**
     * Remove current Dictionary instance
     */
    public abstract void removeDictionary();

    /**
     * Returns the current Dicionary instance
     * @return
     */
    public abstract Set<String> getDictionary();

    /**
     * Validate if the current word belongs to the current dictionary
     * @param word
     * @return
     */
    public abstract boolean isValidWord(String word);

}
