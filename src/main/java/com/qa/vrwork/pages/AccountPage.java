package com.qa.vrwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.vrwork.utils.ElementUtil;
import com.qa.vrwork.utils.VRUtils;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private VRUtils vrutil;

	

	// 1. private By Locators - page locators
	By AccountMenu = By.xpath("//a[contains(@id,'mainmenua10')]");
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

}
