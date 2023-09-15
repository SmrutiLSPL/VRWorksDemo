package com.qa.vrwork.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.vrwork.utils.VRUtils;

public class ProcessServiceOrdersPage {
	private WebDriver driver;

	private VRUtils vrutil;

	// 1. private By Locators - page locators
	By nextbtm = By.xpath("//a[contains(@class,'btn next')]");

	// 2. public page Constructor

	public ProcessServiceOrdersPage(WebDriver driver) {
		this.driver = driver;
		// eleUtil = new ElementUtil(driver);
		vrutil = new VRUtils(driver);

	}

	// 3. public page actoin/Methos
	public void selectServiceOrder(String taskname) throws InterruptedException {
//		By selectCheckBox=By.xpath("//td[contains(text(),'"+taskname+"')]/parent::tr/child::td/child::input[@type='checkbox']");
//		vrutil.clickElementWhendrpReady(selectCheckBox, 15);

//		new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(2))
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'" + taskname
//						+ "')]/parent::tr/child::td/child::input[@type='checkbox']")))
//				.click();
		
		new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(2))
		.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='check_all form-check-input']")))
		.click();
	}

	public void scrolldown() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Element = driver.findElement(By.linkText("Cancel"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView()", Element);
		
	}

	public void clicknext() throws InterruptedException {
		System.out.println("Hoiiii");
		Thread.sleep(3000);
		vrutil.executeJS("document.querySelector('.btn.next').click()");
//		WebElement clickNext=driver.findElement(nextbtm);
//		clickNext.click();
		
	}

}
