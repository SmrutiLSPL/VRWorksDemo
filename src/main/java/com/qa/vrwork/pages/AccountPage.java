package com.qa.vrwork.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.vrwork.utils.ElementUtil;
import com.qa.vrwork.utils.VRUtils;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private VRUtils vrutil;

	

	// 1. private By Locators - page locators
	By AccountMenu = By.xpath("//a[contains(@id,'mainmenua10')]");
	By selectDatePicker = By.xpath("//input[@name='start_date']");
	By selectEndDate=By.xpath("//input[@name='end_date']");
	By searchBox=By.xpath("//a[contains(@class,'searchorders')]");

	// 2. public page Constructor

		public AccountPage(WebDriver driver) {
			this.driver = driver;
			// eleUtil = new ElementUtil(driver);
			vrutil = new VRUtils(driver);
			
		}
		// 3. public page actoin/Methos
		public void clickmainmenu() {
			vrutil.doClick(AccountMenu);
		}

		public String getmainPageTitle() {
			String mainpagetitle = driver.getTitle();
			System.out.println("Account page title: " + mainpagetitle);
			return mainpagetitle;
		}

		public void clickonsidemenu() {
			vrutil.clickAccountingMenuWhenReady("Process Service Orders", 10);
		}
		
		public String gettaskPageTitle() {
			String taskpagetitle = driver.getTitle();
			System.out.println("Account page title: " + taskpagetitle);
			return taskpagetitle;
		}
		
		public void selectfuturdate(int timeOut , String date,String month,String year) throws InterruptedException
		{
			vrutil.doClick(selectDatePicker);
			 new WebDriverWait(driver, Duration.ofSeconds(timeOut))           
			 .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
			 Thread.sleep(5000);
			 vrutil.selectDate(date, month,year);
			
		}
		public void selectenddate(int timeOut , String date,String month,String year) throws InterruptedException
		{
			vrutil.doClick(selectEndDate);
			 new WebDriverWait(driver, Duration.ofSeconds(timeOut))           
			 .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
			 Thread.sleep(5000);
			 vrutil.selectDate(date, month,year);
			
		}
		public void search()
		{
			 vrutil.doClick(searchBox);
		}
		
		
}
