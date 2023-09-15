package com.qa.vrwork.tests;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;
import com.qa.vrwork.constants.AppConstants;

public class InvoiceTest extends BasePage{
	
	public int counter = 1;
	public Workbook workbook;
	public Sheet sheet;
	
	@BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
		this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("TaskServicesNO");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Task name");
        headerRow.createCell(1).setCellValue("Service No");
        headerRow.createCell(2).setCellValue("Bill TOs");
        
    }


	
	@AfterTest
	public void donetest(){
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(AppConstants.write_DATA_SV_SHEET_PATH);
			this.workbook.write(outputStream);
	        this.workbook.close();
	        outputStream.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
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
//		public Object[][] clickonTaskName() {
//			return new Object[][] {
//				
//				{"light issue1","Bill Unit .BL121","1" },
//				{"fridge not working","Bill Damage Waiver","2"},
//			};
//		}
//	  
	@Test(dataProvider = "gettaksdata",dataProviderClass=TaskTest.class)
	public void getInvoiceTasknameTest(String taskname, String unitname, String vendorname, String description, String billto,
			String amount) throws InterruptedException
	{
	
		invoicePage.selectStatus("Waiting Tech", 2);
		Thread.sleep(4000);
		invoicePage.selectInvoiceTask(taskname);
		
		invoicePage.openInvoiceTo();
		//By billTo=By.xpath("//input[contains(@value,'00')or contains(@value,'$')]/parent::span/preceding-sibling::span/select[@name='exp_notes']");
		
		//invoicePage.openInvoiceTo();
		invoicePage.selectBillTo(billto, amount);
		invoicePage.sumOfBillToAmount();
		invoicePage.writeJobNumber(sheet,counter,billto);
		   System.out.println(counter);
		counter++;
		
		invoicePage.vendorAmount();
		Thread.sleep(6000);
		invoicePage.ClickApprove();
		Thread.sleep(6000);
		invoicePage.closeBilltoScrn();
	}
	 
	

}
