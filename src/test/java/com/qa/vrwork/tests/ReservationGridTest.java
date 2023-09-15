package com.qa.vrwork.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;

public class ReservationGridTest extends BasePage{
	@BeforeTest
    public void login()
    {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }
	@Test
	public void validateReservationwithregistredOrAlreadyRegistred() throws InterruptedException
	{
		reservationMenu.clickmainmenu();
		reservationMenu.clickOnListOfMenu();
		reservationMenu.selectActiveGride();
	}
}
