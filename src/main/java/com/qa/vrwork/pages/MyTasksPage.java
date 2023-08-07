package com.qa.vrwork.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.vrwork.utils.VRUtils;

public class MyTasksPage {
	
	private WebDriver driver;
	//private ElementUtil eleUtil;
	private VRUtils vrutil;
	WebDriverWait wait;

	// 1. private By Locators - page locators
	By MaintenanceMenu = By.xpath("//a[@id='mainmenua8 ']");
	By sideMenu = By.xpath("//ul[contains(@class,'contentmenu')]//li");
	By taskname=By.xpath("//input[@placeholder='Write A Service Name']");
	By markComplete=By.xpath("//div[@class='d-flex align-items-center me-auto pb-3']//a[@class='btn btn-sm btn-secondary']");
	By closeIcon=By.xpath("//button[@type='button']//i[@class='fal fa-times']");
	// 2. public page Constructor
	
	public MyTasksPage(WebDriver driver) {
		this.driver = driver;
		// eleUtil = new ElementUtil(driver);
		vrutil = new VRUtils(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	// 3. public page actoin/Methos
	
	public void clickmainmenu() {
		vrutil.doClick(MaintenanceMenu);
	}


	
	
	public void doSelectMyTask(String myTaskName)
	{
		By task=By.xpath("//a[contains(text(),'"+myTaskName+"')]");
		if(myTaskName==null)
		{
			System.out.println("There is no task,Please create task ");
		}
		vrutil.clickElementWhendrpReady( task,10);

	}
	
	public String getTaskName()
	{
		String taskvalue=vrutil.waitForElementVisible(taskname, 10, 5).getAttribute("value");
		System.out.println(taskvalue);
		
		return taskvalue;
	}
	public void doclickonMarkComplete()
	{
		vrutil.clickElementWhendrpReady(markComplete,10);
	}
	public void doclickClose()
	{
		vrutil.clickElementWhendrpReady(closeIcon,10);
	}
	
	
}
