package com.qa.vrwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.vrwork.utils.ElementUtil;
import com.qa.vrwork.utils.VRUtils;

public class ProcessServiceOrdersPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private VRUtils vrutil;

	// 1. private By Locators - page locators
	By selectDatePicker = By.xpath("//input[@name='start_date']");
	// 2. public page Constructor

	public ProcessServiceOrdersPage(WebDriver driver) {
		this.driver = driver;
		// eleUtil = new ElementUtil(driver);
		vrutil = new VRUtils(driver);

	}

	// 3. public page actoin/Methos
	public void date()
	{
		
	}

}
