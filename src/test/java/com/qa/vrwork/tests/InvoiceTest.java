package com.qa.vrwork.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class InvoiceTest extends BasePage{
	
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
		public Object[][] clickonTaskName() {
			return new Object[][] {
				
				{"light issue1","Bill Unit .BL121","1" },
				{"fridge not working","Bill Damage Waiver","2"},
			};
		}
	  
	@Test(dataProvider = "clickonTaskName")
	public void getInvoiceTasknameTest(String invoicetaskname,String billto,String amount) throws InterruptedException
	{
	
		invoicePage.selectStatus("Waiting Tech", 2);
		
		invoicePage.selectInvoiceTask(invoicetaskname);
		
		invoicePage.openInvoiceTo();
		//By billTo=By.xpath("//input[contains(@value,'00')or contains(@value,'$')]/parent::span/preceding-sibling::span/select[@name='exp_notes']");
		
		//invoicePage.openInvoiceTo();
		invoicePage.selectBillTo(billto,amount);
		
		invoicePage.sumOfBillToAmount();
		invoicePage.vendorAmount();
		Thread.sleep(5000);
		invoicePage.ClickApprove();
		Thread.sleep(5000);
		invoicePage.closeBilltoScrn();
	}
	 
	

}
