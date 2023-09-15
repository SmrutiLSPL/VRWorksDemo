package com.qa.vrwork.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class MyTaskCompeleteTest extends BasePage {

	@BeforeTest
	public void login() {
		wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
	}

	@DataProvider
	public Object[][] gettaksdata() {
		return (new TaskTest()).gettaksdata();
	}

	@Test(dataProvider = "gettaksdata", dataProviderClass = TaskTest.class)
	public void mainmenuTest(String taskname, String unitname, String vendorname, String description, String billto,
			String amount) {
		taskPage.clickmainmenu();
		taskPage.clickonsidemenu();
		mytaskPage.doSelectMyTask(taskname);
		//mytaskPage.writeJobNumber();
		mytaskPage.doclickonMarkComplete();

	}
}
