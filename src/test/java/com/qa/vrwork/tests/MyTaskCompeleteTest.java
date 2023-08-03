package com.qa.vrwork.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class MyTaskCompeleteTest extends BasePage {

	
	@BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }
	
	
	 @DataProvider
		public Object[][] gettaksdata() {
			return new Object[][] {
				{"AC Not Working"},
				{"light issue" },
				{"fridge not working"},
			};
		}
	  
	@Test(dataProvider = "gettaksdata")
    public void mainmenuTest(String taskname)
    {
    	taskPage.clickmainmenu();
    	taskPage.clickonsidemenu();
    	mytaskPage.doSelectMyTask(taskname);
    	mytaskPage.doclickonMarkComplete();
    	String actTitle=mytaskPage.getTaskName();
    	Assert.assertEquals(actTitle, taskname);
    }
}
