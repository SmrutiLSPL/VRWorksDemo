package com.qa.vrwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.qa.vrwork.utils.VRUtils;

public class ReservationMenuPage {
	private WebDriver driver;
	private VRUtils vrutil;
	private JavascriptExecutor executor;
	// 1. private By Locators - page locators
	By reservationMenu=By.xpath("//a[@id='mainmenua9 ']");
	
	// 2. public page Constructor
	public ReservationMenuPage(WebDriver driver) {
		this.driver = driver;
		vrutil = new VRUtils(driver);
		executor = (JavascriptExecutor) this.driver;
	}
	// 3. public page actoin/Methos
	public void clickmainmenu() throws InterruptedException {
		vrutil.doClick(reservationMenu);
	}
	public void clickOnListOfMenu()
	{
		vrutil.clickMaintenanceMenuWhenReady("Reservation Grid", 5);
	}
	public void selectActiveGride() throws InterruptedException
	{
		vrutil.selectReservationGrid("ALER104E", "5",2);
	}
}
