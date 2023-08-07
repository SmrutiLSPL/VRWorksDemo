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
		List<WebElement> invoiceTaskName = driver.findElements(
				By.xpath("//table[contains(@class,'invoicetable')]//td[contains(@class,'searchbytaskname')]"));
		for (WebElement e : invoiceTaskName) {
			String taskName = e.getText();
			System.out.println(taskName);
			if (taskName.equals(TaskName)) {
				e.click();
				break;
			}

		}
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
				+ BillValue + "');");
		jse.executeScript("$('.extrabillto_single').last().find('[name=\"exp_amount\"]').val('" + amount + "');");

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
		Thread.sleep(5000);
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
