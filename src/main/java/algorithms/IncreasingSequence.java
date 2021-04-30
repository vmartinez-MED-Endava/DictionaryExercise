package algorithms;

import java.util.ArrayList;
import java.util.List;

public class IncreasingSequence {

    public static void main(String[] args) {
        List<int[]> tests = getTestCases();
        evaluateAlmostIncreasingSequence(tests);
    }

    /**
     * Given a sequence of integers as an array, determine wether it is posible to obtain a
     * strictly increasing sequence by removing no more than one element from the array.
     * Note: Sequence a0, a1, ..., an is considered to be strictly increasing a0 < a1 < .... < an
     * sequence containing only one element is also strictly increasing.
     *
     * This method evaluates the sequence to determine if it can be strictly increasing after removing
     * no more than one element.
     * @param a
     * @return
     */
    public static boolean almostIncreasingSequence(int[] a) {
        int greaterCounter = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] >= a[i + 1]) {
                greaterCounter++;

                if (i < a.length - 2 && a[i] >= a[i + 2] &&  i != 0) {
                    greaterCounter++;
                    if(a[i-1]<a[i+1]){
                        greaterCounter-=1;
                    }
                }
            }
        }
        return greaterCounter <= 1;
    }

    public static void evaluateAlmostIncreasingSequence(List<int[]> lst){
        lst.forEach( arr->
                System.out.printf("%18s-> Strictly Inscreasing?  %5s%n",getStringArr(arr), String.valueOf(almostIncreasingSequence(arr)))
        );
    }

    /**
     * Method to return the available tests cases.
     * @return
     */
    public static List<int[]> getTestCases(){
        List<int[]> tests = new ArrayList<int[]>();

        tests.add(new int[] {1, 4, 3, 2});
        tests.add(new int[] {1, 3, 2});
        tests.add(new int[]{1});
        tests.add(new int[]{1, 1, 1, 1, 1});
        tests.add(new int[] {9, 8, 7, 6, 5});
        tests.add(new int[] {1, 4, 2, 5});
        tests.add(new int[]{-10, 4, 3, 2});
        tests.add(new int[]{1, 3, 2});
        tests.add(new int[]{10, 1, 2, 3, 4, 5});
        tests.add(new int[]{1, 4, -3, 2});
        tests.add(new int[]{1, 1});
        tests.add(new int[] {1, 2, 3, 4, 3, 6});
        tests.add(new int[] {0, -2, 5, 6});
        tests.add(new int[] {1, 2, 3, 99, 4, 5, 6});
        tests.add(new int[] {1, 2, -3, 3});
        tests.add(new int[] {1, 3, 2, 1});
        tests.add(new int[] {3, 2, 1});
        tests.add(new int[] {1, 2, 2, 3});
        tests.add(new int[] {10, 9, 2, 3});
        tests.add(new int[]{1, 2, -3, 2});
        tests.add(new int[] {1, 2, -3, 1});
        tests.add(new int[] {1, 4, 2, 3});
        tests.add(new int[] {1, 4, 3, 2});
        tests.add(new int[] {1, 4, -3, -4});
        tests.add(new int[] {1, 4, -4, -3});
        tests.add(new int[] {1, 4, 4, 1});
        tests.add(new int[]{1, -1, 4, 1});
        tests.add(new int[] {1, 4, 5, 2});

        return tests;
    }

    public static String getStringArr(int[] a) {
        String s = " [";
        for (int i = 0; i < a.length; i++) {
            s += a[i] + ",";
        }
        s = s.substring(0, s.length() - 1) + "]";
        return s;
    }



}
