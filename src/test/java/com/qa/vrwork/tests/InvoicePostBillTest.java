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
	
//	 @DataProvider
//		public Object[][] clickonPostTaskName() {
//			return (new TaskTest()).gettaksdata();
//		}
	  
	@Test(dataProvider = "gettaksdata",dataProviderClass=TaskTest.class)
	public void getInvoiceTaskPostBillTest(String taskname, String unitname, String vendorname, String description, String billto,
			String amount) throws InterruptedException
	{
	
		invoicePage.selectStatus("Pending bill", 2);
		Thread.sleep(4000);
		invoicePage.selectInvoiceTask(taskname);
		Thread.sleep(4000);
		invoicePage.ClickOnPOst();
		Thread.sleep(5000);
	}

}
