package com.endava.workshop.data.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

/**
 * Validation Data Provider provides PreDefined words to validate that Dictionary was able to find them
 * DataProvider used to provide Test Words for validating the Dictionary performance
 *
 */
public class ValidationDataProvider {

    private static Logger logger = LogManager.getLogger(ValidationDataProvider.class);

    /**
     * Method to return words for validation.
     * A set of SubStrings will be produce based on the first word ("working" for this example)
     * while the rest words will be checked on Dictionary to assert if these were inferred appropriately
     * @return
     */
    @DataProvider(name="ValidationProvider")
    public Object[] getInfoProviderParam(){
        logger.info("Instance of Validation Provider - Returning next set of derived words to validate against the Dictionary ");

        return new Object[][]
                {
                        { "Working","WON", "KING","OR", "ROW"}
                };
    }
}
