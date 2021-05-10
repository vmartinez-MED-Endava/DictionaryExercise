package com.endava.workshop.suites;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {



    @BeforeTest
    public void setUpEnvironment(){
        Logger MLogger = Logger.getLogger("Logger");
        MLogger.info("Setting up a Environment parameters - Logger was established");
    }

    @BeforeMethod
    public void initializingTestMethod(){
        Logger.getLogger("Logger").info("New Test Method is being initialized ");
    }

    @AfterMethod
    public void finishingTestMethod(){
        Logger.getLogger("Logger").info("Test method was completed");
    }

}
