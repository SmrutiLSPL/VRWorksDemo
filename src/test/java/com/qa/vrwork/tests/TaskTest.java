package com.qa.vrwork.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class TaskTest extends BasePage {
	
	@BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }
	
    @Test
    public void mainmenuTest()
    {
    	taskPage.clickmainmenu();
    	String accPage=taskPage.getmainPageTitle();
        Assert.assertEquals(accPage,"VR WORKS - Dispatch");
    }
    
    @DataProvider
	public Object[][] gettaksdata() {
		return new Object[][] {
			{"Broken Stair", ".BL121","Nishit Shah","Test Work"},
			{"Broken light ", ".BL301","Nishit Shah","Test Work"},
			{"Broken fridge ", ".MIST","Nishit Shah","Test Work"},
		};
	}
    @Test(dataProvider = "gettaksdata")
    public void sidemenuTest(String taskname,String unitname,String vendorname,String description) throws InterruptedException
    {
    	taskPage.clickonsidemenu();
    	String accPage=taskPage.gettaskPageTitle();
        Assert.assertEquals(accPage,"VR WORKS Newservice");
        taskPage.gotoNewTask();
        String validateTitle=taskPage.validatepage();
        Assert.assertEquals(validateTitle,"Reservation #  ");
        taskPage.addTaskName(taskname);
        taskPage.selectUnit(unitname);
        taskPage.selectvendor(vendorname);
        taskPage.addDescription(description);
        taskPage.clickOnTask();
        String actualServiceText=taskPage.validateServiceText();
        Assert.assertEquals(actualServiceText,"Did you read service notes?");
        taskPage.clickonYes();
       
    }
    
   
}
