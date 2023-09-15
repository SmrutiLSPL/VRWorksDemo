package com.qa.vrwork.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class VRUtils {

	private WebDriver driver;
	private Actions act;

	public VRUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void doclicksideMenu(String value) {
		driver.findElement(By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='" + value + "']")).click();

	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	//Select reservation Grid

	public void selectReservationGrid(String Unitname,String value,int nights) throws InterruptedException
	{

		//Actions act = new Actions(driver);
		int singleDayWidth = 24;

		WebElement recervationpoint = driver.findElement(By.xpath("//div[@class='reservationgridholderholder']"));
		//String Unitname = "SMR001";
		WebElement unit = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-striped unitscode-holder']//td[contains(@unit_code,'"
						+ Unitname + "')]"));// 240,
		// 277
		WebElement quote = driver.findElement(By.xpath("//a[@id='quickquotemodelanchor']"));
		// Search unit
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Unitname);


		// this will find all matching nodes in calendar
		List<WebElement> allDates = driver.findElements(By.xpath(
				"//div[@class='day-header-holder']/table[@class='table']//tr[contains(@class,'daynumberheader')]//div"));// (453,
		// 201)

		// to do MAKE DATE COUNTER DYNAMIC
		int dateCounter = 0;
		// now we will iterate all values and will capture the text. We will select when
		// date is 28

		for (WebElement ele : allDates) {

			String date = ele.getText();
			dateCounter++;
			// System.out.println(date); // once date is 28 then click and break
			if (date.equals(value)) {
				break;
			}
		}

		List<WebElement> allReservationConfirm = driver.findElements(By.xpath("//div[contains(@class,'reservationgridholderholder')]//div[contains(@class,'reservationdivinner')]"));

		// System.out.println(dateCounter);



		Actions act2 = new Actions(driver);

		// System.out.println(recervationpoint.getRect().x+" - "+
		// recervationpoint.getRect().y);
		act2
				// initially mouse will be at (0,0)
				.moveByOffset(recervationpoint.getRect().x + (singleDayWidth * dateCounter) + 2,
						unit.getLocation().y + 2)
				// click and hold on current location, will be res div
				.click()

				// move by days + width
				.moveByOffset(singleDayWidth * nights, 5).contextClick().click(quote).perform();

		try {
			Thread.sleep(5000);


			Alert alert = driver.switchTo().alert(); // switch to alert

			String alertMessage= driver.switchTo().alert().getText(); // capture alert message
			System.out.println(alertMessage); // Print Alert Message
			Assert.assertEquals(alertMessage, "Unit Not Available For Dates Provided.");
			alert.accept();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class='uk-modal-dialog uk-modal-body modal-content modal-dialog-scrollable']//div[@class='modal-header']//button[@type='button']")).click();
		} catch (NoAlertPresentException e) {
			// Handle the case when no alert is present

		}



	}

	//--------Menu List--------------

	public void clickMaintenanceMenuWhenReady(String value, int timeOut) {
		boolean maintenanceMenuClicked = false;
		try {
			if (!maintenanceMenuClicked) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				WebElement MaintenanceMenu = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
						By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='" + value + "']"))));
				MaintenanceMenu.click();
				maintenanceMenuClicked = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickAccountingMenuWhenReady(String value, int timeOut) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			WebElement accountMenu = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
					"//div[@id='main_menu_tab_10']/child::ul[@class='nav contentmenu']/child::li/child::a/child::span[text()='"
							+ value + "']"))));
			accountMenu.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSubMenu(String value, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement SubMenu = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//ul[@id='collapsemenu135']//span[text()='"+value+"']"))));
		SubMenu.click();

	}

	public void clickElementWhendrpReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	public void clickElementWhendrpReady(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(2));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void executeJS(String js) {
//		String javascript = "document.querySelector('.invoiceamount').blur()";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(js);
	}

	public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void clickOnElement(By locator, String linkText) {
		List<WebElement> linksList = getElements(locator);
		System.out.println("total number of links = " + linksList.size());

		for (WebElement e : linksList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(linkText)) {
				e.click();
				break;
			}
		}
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		if (visibleText == null) {
			System.out.println("please pass the right visible text and it can not be null");
			return;
		}
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

//	public List<String> getElementTextList(By locator)
//	{
//		List<WebElement> eleList=getElements(locator);
//		List<String> eleTextList=new ArrayList<String>();
//		for(WebElement e:eleList)
//		{
//			String text=e.getText();
//			if(text.isEmpty())
//			{
//				eleTextList.add(text);
//			}
//		}
//		return eleTextList;
//	}

	public int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public static String[] getMonthYear(String MonthYearval) {
		return MonthYearval.split(" ");
	}

	public void selectDate(String exDay, String ExMonth, String ExYear) {

		if (ExMonth.equals("February") && Integer.parseInt(exDay) > 29) {
			System.out.println("Wrong Date : " + ExMonth + " : " + exDay);
			return;
		}
		if (Integer.parseInt(exDay) > 31) {
			System.out.println("Wrong Date : " + ExMonth + " : " + exDay);
			return;
		}
		String MonthYearval = driver.findElement(By.className("ui-datepicker-title")).getText();

		while (!(getMonthYear(MonthYearval)[0].equals(ExMonth) && getMonthYear(MonthYearval)[1].equals(ExYear))) {
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			MonthYearval = driver.findElement(By.className("ui-datepicker-title")).getText();

		}
		try {
			driver.findElement(By.xpath("//a[text()='" + exDay + "']")).click();
		} catch (Exception e) {
			System.out.println("Wrong Date : " + ExMonth + " : " + exDay);
			e.printStackTrace();
		}
	}
	public void selectpastDate(String exDay, String ExMonth, String ExYear) {

		if (ExMonth.equals("February") && Integer.parseInt(exDay) > 29) {
			System.out.println("Wrong Date : " + ExMonth + " : " + exDay);
			return;
		}
		if (Integer.parseInt(exDay) > 31) {
			System.out.println("Wrong Date : " + ExMonth + " : " + exDay);
			return;
		}
		String MonthYearval = driver.findElement(By.className("ui-datepicker-title")).getText();

		while (!(getMonthYear(MonthYearval)[0].equals(ExMonth) && getMonthYear(MonthYearval)[1].equals(ExYear))) {
			driver.findElement(By.xpath("//a[@title='Prev']")).click();
			MonthYearval = driver.findElement(By.className("ui-datepicker-title")).getText();

		}
		try {
			driver.findElement(By.xpath("//a[text()='" + exDay + "']")).click();
		} catch (Exception e) {
			System.out.println("Wrong Date : " + ExMonth + " : " + exDay);
			e.printStackTrace();
		}
	}

}
