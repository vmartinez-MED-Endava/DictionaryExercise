package com.endava.workshop.data.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;


/**
 * Data provider to deliver Search keys to be checked on Dictionary
 * In case of testing new words to be tested, append the required words next to Working inside the new Object[][]
 * This DataProvider is being called uniquely at DictionaryTest suite
 */
public class SearchDataProvider {
    private static Logger logger = LogManager.getLogger(SearchDataProvider.class);


    @DataProvider(name="StringProvider")
    public Object[] getDataProviderParam(){

        logger.info("Instance of String Provider - Locating word for next Dictionary Search");
        return new Object[][]
                {
                        { "WORKING"},
                };
    }
}
