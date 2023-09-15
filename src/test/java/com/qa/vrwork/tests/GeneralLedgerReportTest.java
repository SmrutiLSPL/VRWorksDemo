package com.qa.vrwork.tests;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;
import com.qa.vrwork.constants.AppConstants;
import com.qa.vrwork.utils.ExcelUtil;

public class GeneralLedgerReportTest extends BasePage {
	public int counter = 1;
	public Workbook workbook;
	public Sheet sheet;

	@BeforeTest
	public void login() {
		wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));

	}

	@Test(priority = 1)
	public void OpenMainMenu() {
		accountPage.clickmainmenu();

	}

	@Test(priority = 2)
	public void scrollDown() {

		try {
			generalReportAcc.scrollDown();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void sidemenu() {
		generalReportAcc.clickonsidemenu();
	}

	@Test(priority = 4)
	public void scrollDownsidemenu() {
		try {
			generalReportAcc.scrollDownsubmenu();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public void submenu() throws InterruptedException {
		generalReportAcc.submenu();
	}

	@Test(priority = 6)
	public void selectStartingAccount() {
		generalReportAcc.selectStartingAccount();
	}

	@Test(priority = 7)
	public void selectEndingAccount() {
		generalReportAcc.selectEndingAccount();
	}

	@Test(priority = 8)
	public void selectstartDate() {
		generalReportAcc.selectStartDate(10);
		generalReportAcc.selectpastDate(10);
		generalReportAcc.SearchButton();

	}

	@DataProvider
	public Object[][] getServiceOrderNo() {

		return ExcelUtil.getTestData(AppConstants.write_DATA_SV_SHEET_PATH, AppConstants.CreateServiceNo_Sheet);
	}

	@Test(priority = 9, dataProvider = "getServiceOrderNo")
	public void allMemoData(String Task, String Service,String billtos) throws InterruptedException {
		
		generalReportAcc.memoData1(Task, Service,billtos);
	}

}
