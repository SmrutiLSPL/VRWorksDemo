package com.qa.vrwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.vrwork.utils.VRUtils;

public class ReservationGridwithreservationPage {
	private WebDriver driver;

	private VRUtils vrutil;

	// 1. private By Locators - page locators
	

	// 2. public page Constructor

	public ReservationGridwithreservationPage(WebDriver driver) {
		this.driver = driver;
		// eleUtil = new ElementUtil(driver);
		vrutil = new VRUtils(driver);

	}
}
