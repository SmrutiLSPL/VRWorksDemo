package com.qa.vrwork.pages;

import java.time.Duration;
import java.util.List;

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
	Long sum;
	
	// 1. private By Locators - page locators
	private By MaintenanceMenu = By.xpath("//a[@id='mainmenua8 ']");
	private By sideMenu = By.xpath("//li[@id='menu_item_80']//a[contains(@class,'menu-item')]");
	private By enterTaskName = By.xpath("//input[@placeholder='Task Name']");
	private By vendorCharge=By.xpath("//input[@class='invoiceamount my-form-control form-control']");
	private By clickonInvoiceTo=By.xpath("//a[@class='addextrabill_to ms-auto']");
	private By billTo=By.xpath("//div[contains(@class,'extrabillto_single')][last()]/descendant::select[@name='exp_notes']");
	//By billTo=By.xpath("//input[contains(@value,'00')or contains(@value,'$')or not(@vlaue='1.00')]/parent::span/preceding-sibling::span/select[@name='exp_notes']");
	private By amountEnter=By.xpath("//div[contains(@class,'extrabillto_single')]//input[@name='exp_amount']");
	private By close=By.xpath("//button[contains(@class,'notdisabled')]");
	private By Approved=By.xpath("//a[contains(@class,'invoicestatus approved')]");
	// 2. public page Constructor

	public InvoicePage(WebDriver driver) {
		this.driver = driver;
		vrutil = new VRUtils(driver);
	}

	// 3. public page actoin/Methos
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
	//not appied 
	public void selectInvoiceTask(String TaskName)
	{
		List<WebElement> invoiceTaskName=driver.findElements(By.xpath("//table[contains(@class,'invoicetable')]//td[contains(@class,'searchbytaskname')]"));
		for(WebElement e:invoiceTaskName)
		{
			String taskName=e.getText();
			System.out.println(taskName);
			if(taskName.equals(TaskName))
			{
				e.click();
				break;
			}
			
		}
	}
	
	public void openInvoiceTo()
	{
		vrutil.doClick(clickonInvoiceTo);
	}

	
	public void selectStatus(String value, int timeOut) {
		driver.findElement(By.xpath("//a[@class='btn-sm btn btn-light dropdown-toggle']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='dropdown-menu text-left show']/child::div//ul[contains(@class,'status-list')]/descendant::label[contains(.,'"+value+"')]")))).click();
	}
	public void selectBillTo(String BillValue,String amount) throws InterruptedException
	{
		
//		vrutil.doSelectDropDownByVisibleText(billTo, BillValue);
		
//		Thread.sleep(10000);
//		driver.findElement(By.xpath("//option[contains(text(),'"+BillValue+"')][@selected]/ancestor::div[@class='d-flex align-items-center']/child::span[@class='d-flex align-items-center me-2']//input[@name='exp_amount']")).sendKeys(amount);
//		WebElement element = driver.findElement(By.xpath("//option[contains(text(),'"+BillValue+"')][@selected]/ancestor::div[@class='d-flex align-items-center']/child::span[@class='d-flex align-items-center me-2']//input[@name='exp_amount']"));
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
//		 jse.executeScript("arguments[0].value='"+BillValue+"';", vrutil.getElement(billTo));
//		 jse.executeScript("arguments[0].value='"+amount+"';", element);
		 
		// Execute JavaScript to get the sum of 'amount' values
		
		 
		 jse.executeScript("$('.extrabillto_single').last().find('[name=\"exp_notes\"]:contains(Bill Unit)').val('"+BillValue+"');");
		 jse.executeScript("$('.extrabillto_single').last().find('[name=\"exp_amount\"]').val('"+amount+"');");
		 
		
	}
	
	public void sumOfBillToAmount()
	{
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		 String sumScript = "var elements = document.querySelectorAll('.extrabillto_single [name=\"exp_amount\"]');" +
                 "var sum = 0;" +
                 "for (var i = 0; i < elements.length; i++) {" +
                 "    var amount = parseFloat(elements[i].value.replace('$',''));" +
                 "    sum += isNaN(amount) ? 0 : amount;" +
                 "}" +
                 "return sum;";
		// Get the sum of 'amount' values using JavaScript
		  sum = (Long) jse.executeScript(sumScript);
		 System.out.println("Sum of amount values: " + sum);
		 
		 
		
	}
	public void vendorAmount()
	{
		driver.findElement(By.xpath("//input[@class='invoiceamount my-form-control form-control']")).sendKeys(sum.toString());
	}
	public void closeBilltoScrn()
	{
		vrutil.doClick(close);
	}
	public void ClickApprove()
	{
		vrutil.doClick(Approved);
	}
}
