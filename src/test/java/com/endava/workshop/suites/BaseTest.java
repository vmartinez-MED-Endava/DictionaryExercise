package com.endava.workshop.suites;

import com.endava.workshop.utils.logger.MLogger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {


    @BeforeTest
    public void setUpEnvironment(){
        MLogger.info("Setting up a Environment parameters - Logger was established");
    }

    @BeforeMethod
    public void initializingTestMethod(){
        MLogger.info("New Test Method is being initialized ");
    }

    @AfterMethod
    public void finishingTestMethod(){
        MLogger.info("Test method was completed");
    }

}
