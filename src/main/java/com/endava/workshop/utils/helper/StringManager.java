package com.endava.workshop.utils.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringManager {

    public StringManager(){ }


    public List<String> getStringSubsets(String str){

        String []stringArr = stringToStringArray(str);
        List<String[]>  stringSubsets = getStringSubsetsList(stringArr, 0, stringArr.length-1);
        List<String> subStrings = stringArraysToStrings(stringSubsets);

        return subStrings;
    }


    private List<String[]> getStringSubsetsList(String[]arr, int low, int high){
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

        String number = matcher
                .replaceAll("")
                .replace(" ","");

        return number;
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
