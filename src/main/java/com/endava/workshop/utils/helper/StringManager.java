package com.endava.workshop.utils.helper;

import com.endava.workshop.utils.exceptions.IncorrectInputParameter;
import com.endava.workshop.utils.exceptions.OutOfMemoryException;
import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * General class for handling different operations over Strings
 */
public class StringManager {

    public StringManager(){ }


    /**
     * Method to retrieve a List of SubStrings obtained from a root string
     * @param str
     * @return
     */
    public List<String> getStringSubsets(String str)  {

        String []stringArr = stringToStringArray(str);

        try {

            List<String[]> stringSubsets = getStringSubsetsList(stringArr, 0, stringArr.length - 1);
            List<String> subStrings = stringArraysToStrings(stringSubsets);
            return subStrings;

        }catch (OutOfMemoryException ex){
            Logger.getLogger("Logger").error("Data Processing overpassed the amount of available memory");
            return null;
        }
    }

    /**
     * Method to recursively explore an array of chars in order to get all the different possible combinations
     * @param arr
     * @param low
     * @param high
     * @return
     * @throws OutOfMemoryException
     */
    private List<String[]> getStringSubsetsList(String[]arr, int low, int high) throws OutOfMemoryException {
        List<String[]> r = new ArrayList<>();

        if(low==high){
            r.add(new String[]{arr[low]});
        }else{
            int mid = (high + low)/2;
            List<String[]>left  = getStringSubsetsList(arr,low, mid );
            List<String[]>right = getStringSubsetsList(arr,mid+1, high);

            left.forEach(x->r.add(x));
            right.forEach(x->r.add(x));
            sumLists(left,right).forEach(x->r.add(x));
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
    public String getOnlyAlphabetLetters(String str){
        Pattern pattern = Pattern.compile("[^a-z A-Z]");
        Matcher matcher = pattern.matcher(str);

        String filteredStr = matcher
                .replaceAll("")
                .replace(" ","");

        if(filteredStr.length() != str.length()){
            try {
                throw new IncorrectInputParameter(new Exception());
            }catch (IncorrectInputParameter e){
                Logger.getLogger("Logger").error("Wrong inputs were inserted - Filtered Achieved over Original String");
            }
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
}
