package com.endava.workshop.data.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

/**
 * Data provider to deliver Search keys to be checked on Dictionary
 */
public class SearchDataProvider {

    @DataProvider(name="StringProvider")
    public Object[] getDataProviderParam(){
        Logger.getLogger(SearchDataProvider.class).info("Instance of String Provider - Locating word for next Dictionary Search");

        return new Object[][]
                {
                        { "Working"},
                };

    }
}
