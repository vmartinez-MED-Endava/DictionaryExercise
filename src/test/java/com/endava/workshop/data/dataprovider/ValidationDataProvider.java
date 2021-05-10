package com.endava.workshop.data.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

/**
 * Validation Data Provider provides PreDefined words to validate the Dictionary was able to find them.
 */
public class ValidationDataProvider {

    /**
     * Method to return words for validation.
     * A set of SubStrings will be produce based on the first word ("working" for this example)
     * while the rest words will be checked on Dictionary to assert if these were inferred appropriately
     * @return
     */
    @DataProvider(name="ValidationProvider")
    public Object[] getInfoProviderParam(){
        Logger.getLogger(ValidationDataProvider.class).info("Instance of Validation Provider - Returning next set of derived words to contrast with Dictionary ");

        return new Object[][]
                {
                        { "Working","WON", "KING","OR"}
                };

    }
}
