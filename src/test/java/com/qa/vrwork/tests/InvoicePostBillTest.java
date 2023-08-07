package com.qa.vrwork.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class InvoicePostBillTest extends BasePage {
	
	
	
	@BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }
	
	@Test
	public void OpenInvoiceMenuTest() throws InterruptedException
	{
		invoicePage.clickmainmenu();
		invoicePage.clickonsidemenu();
	}
	@Test
	public void redirectOnInvoicePageTest()
	{
		String actulTitle=invoicePage.getinvoicePageTitle();
		Assert.assertEquals(actulTitle, "Invoices");
	}
	
	 @DataProvider
		public Object[][] clickonPostTaskName() {
			return new Object[][] {
				
				{"light issue1" },
				{"fridge not working"},
			};
		}
	  
	@Test(dataProvider = "clickonPostTaskName")
	public void getInvoiceTaskPostBillTest(String invoicetaskPostname) throws InterruptedException
	{
	
		invoicePage.selectStatus("Pending bill", 2);
		invoicePage.selectInvoiceTask(invoicetaskPostname);
		Thread.sleep(4000);
		invoicePage.ClickOnPOst();
		Thread.sleep(5000);
	}

}
