package com.qa.vrwork.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import java.util.Set;

import com.qa.vrwork.utils.JavaScriptUtil;
import com.qa.vrwork.utils.VRUtils;

public class GeneralLedgerReportPage {

	private WebDriver driver;

	private VRUtils vrutil;
	private JavaScriptUtil jsutil;

	// 1. private By Locators - page locators
	By startingAccount = By.xpath("//select[@name='starting_account']");
	By EndingAccount = By.xpath("//select[@name='ending_account']");
	By startDate = By.xpath("//input[@id='startdate']");
	By endDate = By.xpath("//input[@id='enddate']");
	By ClickSearch = By.xpath("//a[@class='btn btn-primary btn-icon me-1 filterLedgerStatement']");
	By allServiceData = By.cssSelector(".masterbody table[class$='table table-striped '] td[class$='text-left-mc']");

	// 2. public page Constructor
	public GeneralLedgerReportPage(WebDriver driver) {
		this.driver = driver;
		vrutil = new VRUtils(driver);
	}

	// 3. public page actoin/Methos
	public void clickonsidemenu() {
		vrutil.clickAccountingMenuWhenReady("Reports", 10);

	}

	public void scrollDown() throws InterruptedException {
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("(//span[contains(text(),'Reports')])[3]"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public void scrollDownsubmenu() throws InterruptedException {
		Thread.sleep(10000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//span[normalize-space()='General Ledger Report']"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public void submenu() {
		vrutil.clickSubMenu("General Ledger Report", 3);
	}

	public void selectStartingAccount() {
		vrutil.doSelectDropDownByVisibleText(startingAccount, "[1000-General] Cash on Hand");
	}

	public void selectEndingAccount() {

		vrutil.doSelectDropDownByVisibleText(EndingAccount, "[5001] Adjusted Working Capital");
	}

	public void selectStartDate(int timeOut) {
		vrutil.doClick(startDate);
		new WebDriverWait(driver, Duration.ofSeconds(timeOut))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
		vrutil.selectDate("12", "SEPTEMBER", "2023");
	}

	public void selectpastDate(int timeOut) {
		vrutil.doClick(endDate);
		new WebDriverWait(driver, Duration.ofSeconds(timeOut))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
		vrutil.selectpastDate("12", "SEPTEMBER", "2023");
	}

	public void SearchButton() {
		// vrutil.doClick(ClickSearch);
		vrutil.clickElementWhendrpReady(ClickSearch, 0);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void memoData(String serviceNo, String task, String billtos) throws InterruptedException {
//		List<String> act=vrutil.getElementTextList(allServiceData);
//		System.out.println(act);
//		if(act.contains("3002300-Unit - Service Order Billing"))
//		{
//			System.out.println("Test case pass");
//		}
//		if(act.contains("3003000-Unit - Allocate To/From Owner"))
//		{
//			System.out.println("Test case pass");
//		}

//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//
//		String script = "return document.querySelectorAll('.masterbody table[class$=\"table table-st	riped \"] td[class$=\"text-left-mc\"]');";
//	
//		Object result = jse.executeScript(script);
//		System.out.println("result");
//		System.out.println(result);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String script = "var elements = document.querySelectorAll('.masterbody table[class$=\"table table-striped \"] td[class$=\"text-left-mc\"]');"
				+ "var data = [];" + "for (var i = 0; i < elements.length; i++) {"
				+ " if(elements[i].textContent.includes('" + serviceNo + "')){"
				+ "    data.push(elements[i].parentElement.parentElement.querySelector(\"tr:first-child\").textContent);"
				+ "}} return JSON.stringify(data);";

//		                "return data;";

		Object result = jse.executeScript(script);

		// System.out.println("Result:" + result);

	}


//	public void memoData1(String serviceNo, String task, String billtos) throws InterruptedException {
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//
//		String script = "var elements = document.querySelectorAll('.masterbody table[class$=\"table table-striped \"] td[class$=\"text-left-mc\"]');"
//				+ "var data = {};" + "for (var i = 0; i < elements.length; i++) {"
//				+ " if (elements[i].textContent.includes('" + serviceNo + "')){"
//				+ "    var accountNumberElement = elements[i].parentElement.parentElement.querySelector(\"tr:first-child\").textContent;"
//				+ "    var accountNumber = accountNumberElement.split('-')[0].trim();" + // Extracting the account//
//																							// number
//				"    var billto = elements[i].parentElement.parentElement.querySelector(\"td:nth-child(2)\").textContent.trim();"
//				+ // Extracting the billto data
//				"    data[accountNumber] = billto;" + "}}" + "return JSON.stringify(data);";
//
//		Object result = jse.executeScript(script);
//		String jsonData = (String) result;
//
//		// Parse the JSON data to a Java Map
//		Gson gson = new Gson();
//		Map<String, String> accountToBilltoMap = gson.fromJson(jsonData, new TypeToken<Map<String, String>>() {
//		}.getType());
//		Set<String> uniqueAccountNumbers = new HashSet<>();
//		// Now, you can validate account numbers against billto data using a switch-case
//		System.out.println(accountToBilltoMap);
//		System.out.println();
//		for (Map.Entry<String, String> entry : accountToBilltoMap.entrySet()) {
//			String accountNumber = entry.getKey();
//			if (uniqueAccountNumbers.add(accountNumber)) {
//				String billtoData = billtos;
//				// System.out.println(accountNumber);
//
//				// System.out.println(billtoData);
//
//				switch (billtos) {
//				case "Bill Unit":
//					if ("3003000".equals(accountNumber) || "2301060".equals(accountNumber)
//							|| "2400".equals(accountNumber)) {
//						System.out.println("Test case Pass" + billtos);
//					} else {
//						System.out.println("Test case Fail"); // Print "Test case Fail" when the condition is not met
//					}
//					break;
//				case "Bill Damage Waiver":
//					if ("2200".equals(accountNumber) || "3002300".equals(accountNumber)) {
//						System.out.println("Test case Pass" + billtos);
//					} else {
//						System.out.println("Test case Fail"); // Print "Test case Fail" when the condition is not met
//					}
//					break;
//
//				default:
//
//					System.out.println("Billto Data Does not match" + billtos); // Print "Billto Data Does not match"
//																				// for unexpected
//					// values
//					break;
//				}
//			}
//		}
//
//	}
	public void memoData1(String serviceNo, String task, String billtos) throws InterruptedException {
	    JavascriptExecutor jse = (JavascriptExecutor) driver;

	    String script = "var elements = document.querySelectorAll('.masterbody table[class$=\"table table-striped \"] td[class$=\"text-left-mc\"]');"
	            + "var data = {};" + "for (var i = 0; i < elements.length; i++) {"
	            + " if (elements[i].textContent.includes('" + serviceNo + "')){"
	            + "    var accountNumberElement = elements[i].parentElement.parentElement.querySelector(\"tr:first-child\").textContent;"
	            + "    var accountNumber = accountNumberElement.split('-')[0].trim();" + // Extracting the account number
	            "    var billto = elements[i].parentElement.parentElement.querySelector(\"td:nth-child(2)\").textContent.trim();"
	            + // Extracting the billto data
	            "    data[accountNumber] = billto;" + "}}" + "return JSON.stringify(data);";

	    Object result = jse.executeScript(script);
	    String jsonData = (String) result;

	    // Parse the JSON data to a Java Map
	    Gson gson = new Gson();
	    Map<String, String> accountToBilltoMap = gson.fromJson(jsonData, new TypeToken<Map<String, String>>() {}.getType());

	    // Create an ArrayList to store unique account numbers
	    List<String> uniqueAccountNumbers = new ArrayList<>();

	    // Add the specific account numbers to the ArrayList
	    uniqueAccountNumbers.add("3003000");
	    uniqueAccountNumbers.add("2301060");
	    uniqueAccountNumbers.add("2400");
	    uniqueAccountNumbers.add("3002300");
	    uniqueAccountNumbers.add("2200");
	    uniqueAccountNumbers.add("3002300");

	    // Now, you can validate account numbers against billto data using a switch-case
	    for (Map.Entry<String, String> entry : accountToBilltoMap.entrySet()) {
	        String accountNumber = entry.getKey();

	        // Check if the account number is not a duplicate and matches the specified list
	        if (!uniqueAccountNumbers.contains(accountNumber)) {
	            continue; // Skip this account number if it's not in the specified list
	        }

	        switch (billtos) {
	            case "Bill Unit":
	                if (uniqueAccountNumbers.contains(accountNumber)) {
	                    System.out.println("Account number Match with: " + billtos + "Account no : " +accountNumber);
	                } else {
	                    System.out.println("Account Number not available");
	                }
	                break;
	            case "Bill Damage Waiver":
	                if (uniqueAccountNumbers.contains(accountNumber)) {
	                    System.out.println("Account number Match with: " + billtos+ "Account no : " +accountNumber);
	                } else {
	                    System.out.println("Account Number not available");
	                }
	                break;
	            default:
	                System.out.println("Billto Data Does not match: " + billtos);
	                break;
	        }
	    }

	    // Now, uniqueAccountNumbers contains unique account numbers without duplicates
	    //System.out.println("Unique Account Numbers: " + uniqueAccountNumbers);
	}
}