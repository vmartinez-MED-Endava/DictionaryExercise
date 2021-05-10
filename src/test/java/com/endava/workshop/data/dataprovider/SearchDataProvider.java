package com.endava.workshop.data.dataprovider;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

/**
 * Data provider to deliver Search keys to be checked on Dictionary
 */
public class SearchDataProvider {

    @DataProvider(name="StringProvider")
    public Object[] getDataProviderParam(){
        Logger.getLogger("Logger").info("Instance of String Provider - Locating word for next Dictionary Search");

        return new Object[][]
                {
                        { "Working"},
                };

    }
}
