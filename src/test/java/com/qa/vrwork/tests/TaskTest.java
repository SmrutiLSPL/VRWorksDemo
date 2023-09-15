package com.qa.vrwork.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.vrwork.base.BasePage;
import com.qa.vrwork.constants.AppConstants;
import com.qa.vrwork.utils.ExcelUtil;

public class TaskTest extends BasePage {

	@BeforeTest
	public void login() {
		wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
	}

	@Test
	public void mainmenuTest() {
		taskPage.clickmainmenu();
		taskPage.clickonsidemenu();
		try {
			taskPage.gotoNewTask();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] gettaksdata() {

		return ExcelUtil.getTestData(AppConstants.Test_DATA_SV_SHEET_PATH,AppConstants.Task_Invoice_Sheet);
	}

	@Test(dataProvider = "gettaksdata")
	public void sidemenuTest(String taskname, String unitname, String vendorname, String description, String billto,
			String amount) throws InterruptedException {
		taskPage.clickonsidemenu();
		String accPage = taskPage.gettaskPageTitle();
		// Assert.assertEquals(accPage,"VR WORKS Newservice");
		taskPage.gotoNewTask();
		String validateTitle = taskPage.validatepage();
		// Assert.assertEquals(validateTitle,"Reservation # ");
		taskPage.addTaskName(taskname);
		taskPage.selectUnit(unitname);
		taskPage.selectvendor(vendorname);
		taskPage.addDescription(description);
		taskPage.clickOnTask();
		String actualServiceText = taskPage.validateServiceText();
		// Assert.assertEquals(actualServiceText,"Did you read service notes?");
		taskPage.clickonYes();
		//taskPage.clickClose();
	}

}
