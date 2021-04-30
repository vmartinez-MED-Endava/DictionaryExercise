
import org.testng.Assert;
import org.testng.annotations.Test;

import static algorithms.Dictionary.stringToStringArray;

public class DictionaryTest {

    @Test
    public void StringToStringArrayValidation() {
        String str1  = "hello";
        String str2  = "";
        String str3  = "%$#%#&/&";
        String str4  = "hh ll";

        String[]arr1 = stringToStringArray(str1);
        Assert.assertTrue(arr1.length == str1.length());


        String[]arr2 = stringToStringArray(str2);
        Assert.assertTrue(arr2.length == 0);

        String[]arr3 = stringToStringArray(str3);
        Assert.assertTrue(arr3.length == 0);

        String[]arr4 = stringToStringArray(str4);
        Assert.assertTrue(arr4.length == str4.length());
    }

}
