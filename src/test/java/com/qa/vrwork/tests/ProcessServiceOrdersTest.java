package com.qa.vrwork.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class ProcessServiceOrdersTest extends BasePage {

	@BeforeTest
	public void login() {
		wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
	}

	@Test()
	public void mainmenuTest() throws InterruptedException {
		accountPage.clickmainmenu();
		accountPage.clickonsidemenu();
		accountPage.selectfuturdate(1, "7", "SEPTEMBER", "2023");
		accountPage.selectenddate(1, "30", "SEPTEMBER", "2023");
		accountPage.search();
	}
	@Test(dataProvider = "gettaksdata",dataProviderClass=TaskTest.class)
	public void serviceOrder(String taskname, String unitname, String vendorname, String description, String billto,
			String amount) throws InterruptedException
	{
		serviceOderPage.selectServiceOrder(taskname);
		//serviceOderPage.scrolldown();
	}
	@Test(dependsOnMethods = {"serviceOrder"})
	public void scrollDown() throws InterruptedException
	{
		System.out.println("scroll");
		serviceOderPage.scrolldown();
	}
	@Test(dependsOnMethods ={"scrollDown"})
	public void clickNextTest() throws InterruptedException 
	{
		
		serviceOderPage.scrolldown();
		serviceOderPage.clicknext();
		
	}
}
