package com.endava.workshop.utils.helper;

import com.endava.workshop.utils.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * General class for handling customized operations over Strings objects
 * This class contains specific application filters, methods for String combinations and String generators
 */
public class StringManager {

    /**
     * Logger for tracking the actual flow of the methods called along the program.
     * Loggers the normal flow by info tags and error tags accordingly
     */
    private static Logger logger = LogManager.getLogger(StringManager.class);

    /**
     * String Manager Constructor
     * Initializes an Logger Instance if there is none previously set up
     *
     */
    public StringManager(){ }

    /**
     * Method to retrieve a List of SubStrings obtained from a root string
     * A root String is a word from which multiple subStrings can be obtained of
     * For instance "and" is a root String as the subStrings{"a", "an", "and", "na", "da"} can be derived after some combinations
     *
     * @param str (String) : root String, a String limited up to a length of 22 chars to avoid performance issues
     * @return (List<String>) : A lists holding all of the possible combinations of a given root String, a list of derived subStrings.
     *
     * If no valid String or null object is passed by parameter, the method returns a null List.
     */
    public List<String> getStringSubsetsRecursively(String str) throws OutOfMemoryException {
        if(str.equals(null) || str.equals("")|| str.equals(" ")) return null;

        List <String> stringArr = Arrays.asList(str.split(""));
        List<String> stringSubsets =  getStringSubsetsList(stringArr, 0, stringArr.size() - 1);

        return stringSubsets;
    }

    /**
     * Method to recursively collect the different combinations derived form a List of Strings
     * This specific method follows a divide and conquer approach
     * Initially arr List is split in half, returning two subList, the left subList and the right subList
     * The left subList is split in half, producing two new subList. This process is repeated up to a char level
     * The process is repeated by the right subList. The process will be stable when all of the possible characters are isolated from each other.
     * Finally, a combination process is executed over these items to produce unique subSets of Strings.
     * Arr = {A,B,C}, Result_Before_Combination-> {{A},{B}, {C}}, Result_After_Combination = {A,B,C,AB,AC,BC,ABC}
     * The combination takes into account the order as the assumption made at this point consist in producing subStrings following the String order.
     *
     * @param arr (List<String>) A List of Strings from which all of the possible combinations will be extracted
     * @param low (Int) low Index, Index for exploring the lower bound of the arr List
     * @param high (Int) high Index, Index for exploring the upper bound of the arr List
     * @return (List<String>) List of all of the possible combinations preserving the ascendant order of the initial List.
     */
    public static List<String> getStringSubsetsList (List<String> arr, int low, int high){
        List<String> result = new ArrayList<>();

        if (low == high) {
            result.add(arr.get(low));
        } else {
            int mid = (high + low) / 2;
            List<String> left = getStringSubsetsList(arr, low, mid);
            List<String> right = getStringSubsetsList(arr, mid + 1, high);

            left.forEach(leftElement->result.add(leftElement));
            right.forEach(rightElement->result.add(rightElement));
            sumLists(left, right).forEach(combinedElement-> result.add(combinedElement));
        }
        return result;
    }


    /**
     * Method for returning a compound List from summing two List
     * Returns a List which is composed by the distinct combinations of Two List
     * A = [a] B=[b,c], returned_list = [ab,ac]
     * @param listA (List<String>): A String List different from null
     * @param listB (List<String>): A String List different from null
     * @return List<String>: A composed List among the distinct items passed stored in the list passed by  parameter
     */
    public static List<String> sumLists(List<String> listA, List<String> listB){
        List<String> res = new ArrayList<>();
        if(listA.size()==0 ) return listB;
        if(listB.size()==0 ) return listA;

        for(String strA : listA){
            for(String strB : listB){
                String strC = strA + strB;
                String strD = strB + strA;
                res.add(strC);
                res.add(strD);
            }
        }
        return res;
    }

    /**
     * Method to filter out numbers and special characters on a String passed by parameter
     * The String passed by parameter must satisfy certain conditions to avoid Exceptions
     * - String parameter should not be null : Null Exception will be thrown otherwise
     * - String parameter should not contain special chacracters : IncorrectInputParameterException will be thrown otherwise
     * - String parameter should not be equal to empty or space char : IncorrectInputParameterException will be thrown otherwise
     * In other words, the set of forbidden chars for this method input parameters is:  null \.[]{}()<>*+-=!?^$|1234567890
     * @param str (String): Any String to be filtered - It is recommended a max length of 22 characters to avoid performance latencies
     * @return (String): A String that follows the given conditions : No Special Character nor Numeric Values
     * @throws IncorrectInputParameterException : Exception thrown whenever the String holds special characters
     * @throws NullMethodParameterException : Exception whenever the user typed a null object as a parameter method
     */
    public String getOnlyAlphabetLetters(String str) throws HandlerException {

        if(str == null) throw new NullMethodParameterException();
        if(str.length()>26) throw new InputExcededLengthLimitException();

        String filteredStr = str
                .replaceAll("[^a-zA-Z]","")
                .toUpperCase();

        if(filteredStr.length() != str.length()){
            logger.error("Wrong input parameter was typed - Special Character exception");
            logger.error("Input parameter was : -" + str + "-");
            throw new IncorrectInputParameterException();
        }

        return filteredStr;
    }

    /**
     * Method to generate a random string of a given size without alphabetic and numeric chars
     * @param stringSize (int) : Expected String length as an output
     * @return a String of length stringSize composed of random (A to Z) characters.
     */
    public String generateRandomStringOfSize(int stringSize){
        String seeds = "WORKING"; // Base String from which a new String will be produce randomly
        int wTimes = stringSize / seeds.length();
        int residual = stringSize % seeds.length();

        return seeds.repeat(wTimes) + seeds.substring(0, residual);
    }


}
