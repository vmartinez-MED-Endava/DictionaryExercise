package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary {

    public static void main(String[] args) {
        String s = "WORKING";
        //String [] sl = stringToStringArray(s);
        //System.out.println(sl[0]);
        //System.out.println(sl.length);
        List<String> strs = getEnglishWordsFromString(s);
        strs.forEach(word-> System.out.println(word));

    }

    public static List<String> getEnglishWordsFromString(String str){ //Unique Public
        String []r = stringToStringArray(str);

        List<String[]> subSetsA = getStringSubsets(r, 0, r.length-1);
        List<String> subStrings = convertSubsetsToStrings(subSetsA);
        List<String> noDuplicates = removeDuplicates(subStrings);
        List<String> englishWords = filterEnglishWords(noDuplicates);

        return englishWords;
    }

    public static String filterNumbersSpecialChars(String str){
        return str;
    }

    public static String[] stringToStringArray(String str){
        if(str.length()==0) return null;
        return str.split("");
    }

    public static List<String> filterEnglishWords(List<String> lst) {
        return lst.stream().filter(word->
                isEnglishWord(word))
                .collect(Collectors.toList());
    }


    public static boolean isEnglishWord(String word) { //Mocking According https://gist.github.com/TheFinestArtist/2fd1b4aa1d4824fcbaef
        boolean isEnglish = true;
        for (char c : word.toCharArray()) {
            if (Character.UnicodeBlock.of(c) != Character.UnicodeBlock.BASIC_LATIN) {
                isEnglish = false;
                break;
            }
        }
        return isEnglish;
    }






    public static  List<String> removeDuplicates(List<String> arr){
        Set<String> strs = new HashSet<String>();
        arr.forEach(d->strs.add(d));
        return strs.stream().toList();
    }

    public static List<String> convertSubsetsToStrings(List<String[]> lst){
        return lst.stream().map(x->
                String.join(",",x).replace(",",""))
                .filter(Dictionary::isEnglishWord)
                .collect(Collectors.toList());
    }

    public static List<String[]> getStringSubsets(String[]arr, int low, int high){
        List<String[]> r = new ArrayList<>();

        if(low==high){
            r.add(new String[]{arr[low]});
        }else{
            int mid = (high + low)/2;
            List<String[]>left  = getStringSubsets(arr,low, mid );
            List<String[]>right = getStringSubsets(arr,mid+1, high);

            left.forEach(x->r.add(x));
            right.forEach(x->r.add(x));
            sumLists(left,right).forEach(x->r.add(x));
        }
        return r;
    }

    public static List<String[]> sumLists(List<String[]>a, List<String[]>b){
        List<String[]> res = new ArrayList<String[]>();
        if(a.size()==0 ) return new ArrayList<>();
        if(b.size()==0 ) return new ArrayList<>();
        for(String[]i : a){
            for(String[]j:b){
                String[] c = sumArrs(i,j);
                res.add(c);
            }
        }
        return res;
    }

    public static String[] sumArrs(String[]a, String[]b){
        String[] c=new String[a.length + b.length];

        for(int i=0; i<a.length;i++){
            c[i] = a[i];
        }
        for(int j=0;j<b.length;j++){
            c[j+a.length]=b[j];
        }
        return c;
    }
}
