package com.qa.vrwork.pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.vrwork.utils.VRUtils;

public class InvoicePage {
	private WebDriver driver;
	private VRUtils vrutil;
	WebDriverWait wait; // Wait for a maximum of 10 seconds
	Long sum;
	private JavascriptExecutor executor;
	// 1. private By Locators - page locators
	By MaintenanceMenu = By.xpath("//a[@id='mainmenua8 ']");
	By sideMenu = By.xpath("//li[@id='menu_item_80']//a[contains(@class,'menu-item')]");
	By enterTaskName = By.xpath("//input[@placeholder='Task Name']");
	By vendorCharge = By.xpath("//input[@class='invoiceamount my-form-control form-control']");
	By clickonInvoiceTo = By.xpath("//a[@class='addextrabill_to ms-auto']");
	By billTo = By.xpath("//div[contains(@class,'extrabillto_single')][last()]/descendant::select[@name='exp_notes']");
	// By billTo=By.xpath("//input[contains(@value,'00')or contains(@value,'$')or
	// not(@vlaue='1.00')]/parent::span/preceding-sibling::span/select[@name='exp_notes']");
	By amountEnter = By.xpath("//div[contains(@class,'extrabillto_single')]//input[@name='exp_amount']");
	By close = By.cssSelector("button.notdisabled");
	By Approved = By.cssSelector(".invoicestatus.approved");
	
	// 2. public page Constructor

	public InvoicePage(WebDriver driver) {
		this.driver = driver;
		vrutil = new VRUtils(driver);
		executor = (JavascriptExecutor) this.driver;
	}

	// 3. public page actoin/Methos
	
	public void writeJobNumber(Sheet sheet,int counter,String billto)
	{
		// Find the input box element
        WebElement inputBox = driver.findElement(By.xpath("//input[@class='invoicenumber form-control my-form-control']")); // Replace with the actual element locator
        WebElement TasknameBox = driver.findElement(By.xpath("//div[@class='titleoftask']")); // Replace with the actual element locator
        
        
        // Get the input box value
        String inputValue = inputBox.getAttribute("value");
        String name = TasknameBox.getText();
      
     // Create a list of HashMaps to store the data
        List<HashMap<String, String>> dataList = new ArrayList<>();
        
        // Add data to the list (you can add more data sets as needed)
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("Service No", inputValue);
        data1.put("Task name", name);
 
        dataList.add(data1);

        // Create data rows for each data set
		for (HashMap<String, String> data : dataList) {
		    Row dataRow = sheet.createRow(counter);
		    dataRow.createCell(0).setCellValue(data.get("Task name"));
		    dataRow.createCell(1).setCellValue(data.get("Service No"));
		    dataRow.createCell(2).setCellValue(billto);
		    System.out.println(counter);
		    
		}

		// Save the Excel file
		

		System.out.println("Add data  to Excel successfully.");
        }
	
	public void clickmainmenu() throws InterruptedException {
		vrutil.doClick(MaintenanceMenu);

	}

	public void clickonsidemenu() {
		vrutil.clickElementWhendrpReady(sideMenu, 10);
	}

	public String getinvoicePageTitle() {
		String invoicepagetitle = driver.findElement(By.xpath("//h4[normalize-space()='Invoices']")).getText();
		System.out.println("Task page title: " + invoicepagetitle);
		return invoicepagetitle;
	}

	// not appied
	public void selectInvoiceTask(String TaskName) {
//		List<WebElement> invoiceTaskName = driver.findElements(
//				By.xpath("//table[contains(@class,'invoicetable')]//td[contains(@class,'searchbytaskname')]"));
//		for (WebElement e : invoiceTaskName) {
//			String taskName = e.getText();
//			System.out.println(taskName);
//			if (taskName.equals(TaskName)) {
//				e.click();
//				break;
//			}
//
//		}
		vrutil.getElement(By.xpath("//td[contains(text(),'"+TaskName+"')]/parent::tr")).click();
	}

	public void openInvoiceTo() {
		vrutil.doClick(clickonInvoiceTo);
	}

	public void selectStatus(String value, int timeOut) {
		driver.findElement(By.xpath("//a[@class='btn-sm btn btn-light dropdown-toggle']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//div[@class='dropdown-menu text-left show']/child::div//ul[contains(@class,'status-list')]/descendant::label[contains(.,'"
						+ value + "')]"))))
				.click();
	}

	public void selectBillTo(String BillValue, String amount) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("$('.extrabillto_single').last().find('[name=\"exp_notes\"]:contains(Bill Unit)').val('"
				+ BillValue + "').blur();");
		Thread.sleep(5000);
	//	jse.executeScript("select[name='acc_number'] >option[value='"+valueNo+"']).blur();");
		jse.executeScript("$('.extrabillto_single').last().find('[name=\"exp_amount\"]').val('" + amount + "').blur();");
		Thread.sleep(5000);
	}

	public void sumOfBillToAmount() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String sumScript = "var elements = document.querySelectorAll('.extrabillto_single [name=\"exp_amount\"]');"
				+ "var sum = 0;" + "for (var i = 0; i < elements.length; i++) {"
				+ "    var amount = parseFloat(elements[i].value.replace('$',''));"
				+ "    sum += isNaN(amount) ? 0 : amount;" + "}" + "return sum;";
		// Get the sum of 'amount' values using JavaScript
		sum = (Long) jse.executeScript(sumScript);
		System.out.println("Sum of amount values: " + sum);

	}

	public void vendorAmount() throws InterruptedException {
		WebElement totalamount = driver
				.findElement(By.xpath("//input[@class='invoiceamount my-form-control form-control']"));
		totalamount.clear();
		Thread.sleep(10000);
		totalamount.sendKeys(sum.toString());
		// VRUtils vrUtils = new VRUtils(driver);
		vrutil.executeJS("document.querySelector('.invoiceamount').blur()");
	}

	public void closeBilltoScrn() throws InterruptedException {
		WebElement m = driver.findElement(By.cssSelector("button.notdisabled"));
		m.click();

	}

	public void ClickApprove() throws InterruptedException {
	
		String javascript = "document.querySelector('.invoicestatus.approved').click()";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(javascript);

		// vrutil.clickElementWhendrpReady(Approved, 10);
	}

	public void ClickOnPOst() {

		String javascript = "document.querySelector('a.billingstatus.completed').click()";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(javascript);

		// vrutil.clickElementWhendrpReady(Approved, 10);
	}
}
