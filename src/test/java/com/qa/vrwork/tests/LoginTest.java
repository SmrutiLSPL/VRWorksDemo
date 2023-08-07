package com.qa.vrwork.tests;

import com.qa.vrwork.base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {

    @Test
    public void LoginPageTitleTest()
    {
       String loginTitle= loginPage.getLoginPageTitle();
        System.out.println(loginTitle);
    }

    @Test
    public void LoginTest() throws InterruptedException {
        String accPage = loginPage.VRLogin(prop.getProperty("username"), prop.getProperty("password"),prop.getProperty("selectView"));
        Assert.assertEquals(accPage,"VR WORKS - Dispatch");
    }	
}
