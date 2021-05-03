package com.endava.workshop.data.dataprovider;

import com.endava.workshop.utils.logger.MLogger;
import org.testng.annotations.DataProvider;

/**
 * Validation Data Provider provides PreDefined words to validate the Dictionary was able to find them.
 */
public class ValidationDataProvider {

    @DataProvider(name="ValidationProvider")
    public Object[] getInfoProviderParam(){
        MLogger.info("Instance of Validation Provider - Returning next set of derived words to contrast with Dictionary ");

        return new Object[][]
                {
                        { "Working","WON", "KING","OR"}
                };

    }
}
