package algorithms;

public class InterestingPolygon {

    public static void main(String[] args) {

        int n = 5 ;
        System.out.println(getAreaInterestingPolygon(1));
        System.out.println(getAreaInterestingPolygon(2));
        System.out.println(getAreaInterestingPolygon(3));
        System.out.println(getAreaInterestingPolygon(4));
        System.out.println(getAreaInterestingPolygon(5));
        System.out.println(getAreaInterestingPolygon(6));
        System.out.println(getAreaInterestingPolygon(7));

    }

    /**
     * A 1-interesting polygon is just a square with a side of length 1. An n-interesting
     * polygon is obtained by taking the n-1-interesting polygon and appending 1-interesting
     * polygons to its rim, side by side. You can see 1-, 2-,3- and 4- interesting polygons in the
     * picture I forgot to post. Imagine it!
     * @param n
     * @return
     */
    public static int getAreaInterestingPolygon(int n){
        if(n == 1) return 1;
        return (int) (Math.pow(oddValueInPosition(n),2) - 4 * recursiveDecreasingSum(n-1));
    }

    public static int oddValueInPosition(int pos){
        return 2 * pos -1;
    }

    public static int recursiveDecreasingSum(int n){
        if(n==1) return 1;
        return n + recursiveDecreasingSum(n-1 );
    }
}
