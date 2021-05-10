package com.endava.workshop.suites;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;



public class BaseTest {

    SoftAssert softAssert;
    public static Logger logger;

    @BeforeTest
    public void setUpEnvironment(){
        logger = LogManager.getLogger(BaseTest.class);
        logger.info("Setting up a Environment parameters - Logger was established");
    }

    @BeforeMethod
    public void initializingTestMethod(){
        logger.info("New Test Method is being initialized ");
        this.softAssert = new SoftAssert();
    }

    @AfterMethod
    public void finishingTestMethod(){
        logger.info("Test method was completed");
        this.softAssert.assertAll();
    }
}
