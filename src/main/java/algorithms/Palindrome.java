package algorithms;

public class Palindrome {
    public static void main(String[] args) {

        String a = "Hello";
        System.out.println(reverteStr(a));
    }
    public static String reverteStr(String r){
        String s ="";
        for (int i=0; i<r.length();i++){
            s = r.charAt(i) + s;
        }
        return s;
    }
}
