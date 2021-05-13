package com.endava.workshop.services;

import com.endava.workshop.data.dictionary.EnglishDictionary;
import com.endava.workshop.utils.exceptions.EmptyInputParameterException;
import com.endava.workshop.utils.exceptions.HandlerException;
import com.endava.workshop.utils.helper.StringManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Dictionary Class Implements the required methods defined in DictionaryImp
 * In overall validates using a limited dictionary if a certain word belongs to English language
 * Also returns a dictionary, in this context a set of strings, with some of the English words
 */
public class Dictionary implements BaseDictionary {

    private static  Logger logger = LogManager.getLogger(Dictionary.class);
    private EnglishDictionary englishDictionary;
    private StringManager stringManager;
    private Set<String> englishWordsSet;

    /**
     * A Dictionary Constructor with the signing of some of the Attributes passed as parameters for a semi-custom class instance
     * @param englishDictionary (EnglishDictionary) : Instance of a BaseDictionary Interface implementation
     * @param stringManager (String Manager) : General purpose String class for handling customized operations for the application
     * @AttributeInitialization englishWordsList (Set<String>) : A set of String which is a limited representation of a dictionary : Limited up to twenty strings
     * @AttributeInitialization logger (Logger) : Logger instance apache.logging.log4j.Logger for tracking the program flow execution
     */
    public Dictionary(EnglishDictionary englishDictionary, StringManager stringManager) {
        this.englishDictionary = englishDictionary;
        this.stringManager = stringManager;
        this.englishWordsSet = new HashSet<>();
    }

    /**
     * A Dictionary Constructor with the signing of some of the Attributes passed as parameters for a semi-custom class instance
     * @param englishWordsSet (Set<String>) : A set of String which is a limited represention of a dictionary : Limited up to twenty strings
     * @AttributeInitialization englishDictionary (EnglishDictionary) : Instance of a BaseDictionary Interface implementation
     * @AttributeInitialization stringManager (String Manager) : General purpose String class for handling customized operations for the application
     * @AttributeInitialization logger (Logger) : Logger instance apache.logging.log4j.Logger for tracking the program flow execution
     */
    public Dictionary(Set<String> englishWordsSet) {
        this.englishDictionary = new EnglishDictionary();
        this.stringManager = new StringManager();
        this.englishWordsSet = englishWordsSet;
    }

    /**
     *  A Dictionary Constructor with the signing of all of the Attributes passed as parameters for a custom class instance
     * @param englishDictionary (EnglishDictionary) : Instance of a BaseDictionary Interface implementation
     * @param stringManager (String Manager) : General purpose String class for handling customized operations for the application
     * @param englishWordsSet (Set<String>) : A set of String which is a limited represention of a dictionary : Limited up to twenty strings
     * @AttributeInitialization logger (Logger) : Logger instance apache.logging.log4j.Logger for tracking the program flow execution
     */
    public Dictionary(EnglishDictionary englishDictionary, StringManager stringManager, Set<String> englishWordsSet) {
        this.englishDictionary = englishDictionary;
        this.stringManager = stringManager;
        this.englishWordsSet = englishWordsSet;
    }

    /**
     * Dictionary Constructor with no method arguments
     * A Dictionary Constructor with no signing of all of the Attributes passed as parameters. All of the Attributes are initialized under default conditions
     * A Dictionary Constructor with the signing of all of the Attributes passed as parameters for a custom class instance
     * @AttributeInitialization englishDictionary (EnglishDictionary) : Instance of a BaseDictionary Interface implementation
     * @AttributeInitialization stringManager (String Manager) : General purpose String class for handling customized operations for the application
     * @AttributeInitialization englishWordsList (Set<String>) : A set of String which is a limited represention of a dictionary : Limited up to twenty strings
     * @AttributeInitialization logger (Logger) : Logger instance apache.logging.log4j.Logger for tracking the program flow execution
     */
    public Dictionary(){
        this.englishDictionary = new EnglishDictionary();
        this.stringManager = new StringManager();
        this.englishWordsSet = new HashSet<>();
    }

    /**
     * Method to return a Collection with all valid English words derived from root String
     * A Collection of derived words consist in a set of all of the subStrings obtained from a base or root String
     * For "And" word, the set of subStrings for this word would be set={"A", "AN", "AND"} assuming these are valid words for the dictionary
     * The process consist in subtracting all of the possible combinations of the subStrings into a List and filtering out the repeated Strings
     * Using Set as a collection prevents the use of repeated Strings
     * ** Note: This method implements a recursive method following the Divide and Conque approach - Performance issue are identified for big input string
     * @param originalString (String): Root String, Any String limited up two 22 chacracters to avoid performance latencies
     * @return (Set<String>): Returns a Set of Valid English Strings
     * @throws HandlerException
     */
    public Set<String> getEnglishWordsFromStringRec(String originalString) throws HandlerException {

        return getEnglishWordsFromString(originalString, false);
    }

    public Set<String> getEnglishWordsFromStringHis(String originalString) throws HandlerException {

        return getEnglishWordsFromString(originalString, true);
    }

    /**
     * Method to return a Collection with all valid English words derived from root String
     * A Collection of derived words consist in a set of all of the subStrings obtained from a base or root String
     * For "And" word, the set of subStrings for this word would be set={"A", "AN", "AND"} assuming these are valid words for the dictionary
     * The process consist in subtracting all of the possible combinations of the subStrings into a List and filtering out the repeated Strings
     * Using Set as a collection prevents the use of repeated Strings
     * ** Note : This method implements a Character Histogram approach for validating which are the derived and valid words.
     * A histogram character comparison is done for each individual word in the dictionary against the subString histogram. If both histograms match
     * Then it is a correct english word.
     *
     * @param originalString
     * @param noRecursion
     * @return
     * @throws HandlerException
     */
    private Set<String> getEnglishWordsFromString(String originalString, boolean noRecursion) throws HandlerException{
        String filteredStr = cleanString(originalString);

        List<String> stringSubsetsList = (noRecursion)?
                getHistogramSubsetsList(originalString, englishDictionary):
                stringManager.getStringSubsetsRecursively(filteredStr);

        this.englishWordsSet = filterEnglishWords(stringSubsetsList);

        return englishWordsSet;
    }

    /**
     * Method to clean the String passed by parameter
     * This first filters wipes out special characters and numbers as these are not valid for a word Dictionary
     * List of invalid characters is : null \.[]{}()<>*+-=!?^$|1234567890
     * If the String parameter includes any special character, then a Handler Exception will be thrown
     *
     * @param str (String) : String parameter limited up to 22 characters length
     * @return (String): A cleaned String
     * @throws HandlerException : Exception thrown whenever the method parameter included any invalidad character.
     */
    private String cleanString(String str) throws HandlerException {
        String filteredWord = stringManager.getOnlyAlphabetLetters(str);

        if (filteredWord == "" || filteredWord.equals(null)) {
            logger.info("String had invalid values - " +str);
            throw new EmptyInputParameterException();
        }

        logger.info("String followed the specified requirements : " + filteredWord);
        return filteredWord;
    }

    /**
     * Method to validate if the Dictionary contains the Specified String passed by parameter
     * The String is search in the local EnglishWords dictionary which is a set of Strings
     * If no match is performed, a false boolean is returned
     * EnglishWords dictionary is assumed to be a small dictionary, bargain up to 20 strings.
     * @param str (String) : item to be searched in the dictionary
     * @return (boolean) : true if the str String belongs to the dictionary
     */
    public boolean containsString(String str){
        String toUpper = str.toUpperCase();
        logger.info("Validating if " + str + " belongs to dictionary");
        return this.englishWordsSet.contains(toUpper);
    }

    /**
     * Method to filter out duplicates and not English words
     * A Set collection is used to avoid duplicates and isEnglishWords is a predicated
     * used to preserve English words only
     * @param lst (List<String) : Collection of Strings to be filtered
     * @return (Set<String>) : Collection of unique and English Strings
     */
    private Set<String> filterEnglishWords(List<String> lst) {
        Set<String> uniqueWordsSet = new HashSet<String>(lst);

        return uniqueWordsSet
                .stream()
                .filter(  word-> isEnglishWord(word) )
                .collect(Collectors.toSet());
    }

    /**
     * Method to return the max string length allowed in a JVM environment based on the memory available
     * The estimation is performed after applying logarithm in base 2 to the current memory space.
     * This is not a strict calculation but an approximation : Strings larger than this estimated limit will produce a wrong performance
     * as high latency, slow processing and in some cases the execution crashing
     *
     * @return (int) Max String length permitted in JVM before noticing performance latency
     */
    public int getMaxStringLengthInMemory(){
        long availableMemory = Runtime.getRuntime().freeMemory();
        logger.info(" Available memory :" + availableMemory + " bytes.");

        double maxString = Math.floor(Math.log(availableMemory))-5;
        logger.info(" Estimated max String length " + maxString);

        return (int) maxString;
    }

    /**
     * Method to return a Random String of a given size
     * This method uses the String Manager method for creating the string.
     * No Special nor numeric characters are included in the returned string.
     *
     * @param stringSize (int) : Size of the expected output random string
     * @return (String) : a random string of size stringSize
     */
    public String getRandomStringOfSize(int stringSize){
        String generated = stringManager.generateRandomStringOfSize(stringSize);
        logger.info(" - Generating a random String  - output : " + generated);

        return  generated;
    }

    /**
     * Method to validate if the String parameter corresponds to an English word
     * Validation is carried out by a quick search over an English dictionary.
     * English dictinary is a Set<String> and if the word is found inside this dictionary,
     * then the function will return true, false otherwise.
     * @param word (String) : Word to be validated on a English dictionary instance
     * @return (boolean) : true if the word belongs to the English dictionary
     */
    @Override
    public boolean isEnglishWord(String word) {
        return englishDictionary.isValidWord(word);
    }

    /**
     * Method to return all of the possible subStrings derived from a root String by comparing it against a word histogram from a dictionary
     * A dictionary is represented in this context as a Set<String> and the histogram of each word of it will be represented as an array of 26 Integer positions
     * If the dictionary has 13 words, then the final set will have a size of 13 arrays, each one encoding a representation of that particular String
     * The String representation to an Array is performed through the frequency of letters inside each word : "BOOLEAN" ->A:1 B:1,E:1, L:1 N:1, O:2, [1,1,0,0,1...]
     * @param string : root string from which all subStrings are obtained
     * @param englishDictionary (Set<String>) : A Collection of valid English words
     * @return (List<String>) : A Collection of valid english words
     */
    private static List<String> getHistogramSubsetsList(String string, EnglishDictionary englishDictionary) {
        List<String> subStrings = new ArrayList<>();
        Set<String> dictionary = englishDictionary.getDictionary();
        int[] freq = toFreq( string.toUpperCase() );

        for ( String l : dictionary ) {
            int[] freqIn = toFreq( l );
            if ( matches( freq, freqIn ) ) {
                subStrings.add( l );
            }
        }
        return subStrings;
    }

    /**
     * Method to validate if two arrays holds the same exact values: Returns true if all values match.
     * This method is utilized for comparing two arrays representing the repetitions of the letters of the alphabet
     * Each array position represents a letter in the alphabet, so 26 fix positions is the length of each array
     * Inside each array position a letter frequency will be stored.
     *
     * @param freq (int[]) stores the histogram of the origin letters repetion, i.e.  [2,1,1,0,1,0,0,0,0] = "AABC"
     * @param freqIn (int[]) stores the histogram of the dictionary letters repettion, i.e. [0,2,1,1] = "BCBD"
     * @return (boolean) : True if both arrays are identical, otherwise returns false.
     */
    private static boolean matches( int[] freq, int[] freqIn ){
        for ( int i = 0; i < 26; i++ ){
            if ( freq[i] == 0 && freqIn[i]>0){
                return false;
            } else if (freq[i] < freqIn[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return the characters histogram of a given String
     *
     * @param string : Base word
     * @return (int[]): 26-length Array representing the letter alphabet repetitions
     */
    private static int[] toFreq( String string )
    {
        int[] freq = new int[26];
        int valA = Integer.valueOf('A');  // Distinction for Comparing only with UpperCase Characters
        for ( char c : string.toCharArray() )
        {
            int valC = Integer.valueOf(c);
            if ( ( valC- valA ) >= 0 && ( valC - valA ) < 26 )
            {
                freq[valC - valA] = freq[valC - valA]+ 1;
            }
        }
        return freq;
    }

    /**
     * Returns an English Dictionary instance
     * @return (EnglishDictionary)
     */
    public EnglishDictionary getEnglishDictionary() {
        return englishDictionary;
    }

    /**
     * Updates the current dictionary Instance
     * @param englishDictionary (EnglishDictionary) :  EnglishClass that holds a set of Strings to validation processes
     */
    public void setEnglishDictionary(EnglishDictionary englishDictionary) {
        this.englishDictionary = englishDictionary;
    }

    /**
     * Get a current String manager instance.
     * @return (StringManager)
     */
    public StringManager getStringManager() {
        return stringManager;
    }

    /**
     * Update current Instance of StringManager
     * @param stringManager (StringManager)
     */
    public void setStringManager(StringManager stringManager) {
        this.stringManager = stringManager;
    }

    /**
     * Get EnglishWordSet calculated from the root String
     * Unrepeated set of derived words obtained of the chars combinations of the String root
     * @return (Set<String>)
     */
    public Set<String> getEnglishWordsSet() {
        return englishWordsSet;
    }

    /**
     * Method to update the current set of English Words
     * @param englishWordsSet (Set<String>)
     */
    public void setEnglishWordsSet(Set<String> englishWordsSet) {
        this.englishWordsSet = englishWordsSet;
    }

}
