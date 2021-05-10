package com.endava.workshop.utils.helper;

import com.endava.workshop.utils.exceptions.IncorrectInputParameter;
import com.endava.workshop.utils.exceptions.NullException;
import com.endava.workshop.utils.exceptions.OutOfMemoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * General class for handling different operations over Strings
 */
public class StringManager {

    Logger logger ;

    public StringManager(){
        if(logger == null) {
            logger = LogManager.getLogger(StringManager.class);
        }
    }


    /**
     * Method to retrieve a List of SubStrings obtained from a root string
     * @param str
     * @return
     */
    public List<String> getStringSubsets(String str) throws OutOfMemoryException {
        String []stringArr = stringToStringArray(str);
        List<String[]> stringSubsets = getStringSubsetsList(stringArr, 0, stringArr.length - 1);
        List<String> subStrings = stringArraysToStrings(stringSubsets);

        return subStrings;
    }

    /**
     * Method to recursively explore an array of chars in order to get all the different possible combinations
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private List<String[]> getStringSubsetsList (String[]arr, int low, int high){
            List<String[]> r = new ArrayList<>();

            if (low == high) {
                r.add(new String[]{arr[low]});
            } else {
                int mid = (high + low) / 2;
                List<String[]> left = getStringSubsetsList(arr, low, mid);
                List<String[]> right = getStringSubsetsList(arr, mid + 1, high);

                left.forEach(x -> r.add(x));
                right.forEach(x -> r.add(x));
                sumLists(left, right).forEach(x -> r.add(x));
            }
        return r;
    }

    /**
     * Method to sum a two Lists storing arrays of Strings.
     * Both arrays cna have different lengths.
     * @param a
     * @param b
     * @return
     */
    public List<String[]> sumLists(List<String[]>a, List<String[]>b){
        List<String[]> res = new ArrayList<String[]>();
        if(a.size()==0 ) return new ArrayList<>();
        if(b.size()==0 ) return new ArrayList<>();
        for(String[]i : a){
            for(String[]j:b){
                String[] c = sumStringArrs(i,j);
                res.add(c);
            }
        }
        return res;
    }

    /**
     * Method to Sum Two Arrays of Strings
     * For this purpose only two arrays can be added at a given instant
     * @param a
     * @param b
     * @return
     */
    public String[] sumStringArrs(String[]a, String[]b){
        String[] c=new String[a.length + b.length];

        for(int i=0; i<a.length;i++){
            c[i] = a[i];
        }
        for(int j=0;j<b.length;j++){
            c[j+a.length]=b[j];
        }
        return c;
    }


    /**
     * Method to filter out numbers and special characters
     */
    public String getOnlyAlphabetLetters(String str) throws IncorrectInputParameter, NullException {

        if(str == null) throw new NullException();

        Pattern pattern = Pattern.compile("[^a-z A-Z]");
        Matcher matcher = pattern.matcher(str);

        String filteredStr = matcher
                .replaceAll("")
                .replace(" ","");

        if(filteredStr.length() != str.length()){
            logger.error("Wrong input parameter was typed - Special Character exception");
            logger.error("Input parameter was : -" + str + "-");
            throw new IncorrectInputParameter();
        }

        return filteredStr.toUpperCase();
    }

    /**
     * Method to convert a String to an Array of chars
     * @param str
     * @return
     */
    public String[] stringToStringArray(String str){
        if(str.length()==0) return null;
        return str.split("");
    }

    /**
     * Method to convert a List of arrays of Strings to a List of Strings
     * @param lst
     * @return
     */
    public List<String> stringArraysToStrings(List<String[]> lst){
        return lst.stream().map(x->
                String.join(",",x).replace(",",""))
                .collect(Collectors.toList());
    }

    /**
     * Method to generate a random string of a given size without alphabetic and numeric chars
     * @param stringSize (int) : Expected String length as an output
     * @return a String of length stringSize composed of random (A to Z) characters.
     */
    public String generateRandomStringOfSize(int stringSize){
        String seeds = "INGORKW";

        return IntStream.range(0, stringSize)
                .map(
                        i -> new SecureRandom().nextInt(seeds.length()))
                .mapToObj(randomInt -> seeds.substring(randomInt, randomInt + 1))
                .collect(Collectors.joining());
    }
}
