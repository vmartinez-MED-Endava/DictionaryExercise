package com.endava.workshop.data.dataprovider;


import com.endava.workshop.utils.logger.MLogger;
import org.testng.annotations.DataProvider;

/**
 * Data provider to deliver Search keys to be checked on Dictionary
 */
public class SearchDataProvider {

    @DataProvider(name="StringProvider")
    public Object[] getDataProviderParam(){
        MLogger.info("Instance of String Provider - Locating word for next Dictionary Search");

        return new Object[][]
                {
                        { "Working"},
                };

    }
}
