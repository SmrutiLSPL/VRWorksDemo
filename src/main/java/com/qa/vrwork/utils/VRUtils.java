package com.qa.vrwork.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VRUtils {
	
	private WebDriver driver;
	private Actions act;
	
	public VRUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public void doclicksideMenu(String value)
	{
		driver.findElement(By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='"+value+"']")).click();
	}

	public  void doSendKeys(By locator,String value)
	{
		getElement(locator).sendKeys(value);
		
	}
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	
	public  WebElement getElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	public void clickElementWhenReady(String value, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@class='nav contentmenu']/descendant::span[text()='"+value+"']")))).click();
	}
	
	public void clickElementWhendrpReady(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
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
	

    
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
}
