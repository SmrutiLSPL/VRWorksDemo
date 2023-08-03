package com.qa.vrwork.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.vrwork.utils.ElementUtil;
import com.qa.vrwork.utils.VRUtils;

public class TaskPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private VRUtils vrutil;
	WebDriverWait wait;

	// 1. private By Locators - page locators
	By MaintenanceMenu = By.xpath("//a[@id='mainmenua8 ']");
	By sideMenu = By.xpath("//ul[contains(@class,'contentmenu')]//li");
	By newTask = By.xpath("//a[@class='btn btn-primary servicemodalanchor-popup']");
	By unitbtn = By.xpath("//div[@class='main-wrapper thisactive']/div[@class='page-wrapper']/div[@class='page-content']/div[@id='newservice']/form[@class='popupform form-container']/div[@class='popupbody']/div[@class='popupbodycontent']/div[@class='unitandcontractor ']/div[1]/div[1]");
	By vendorbtn=By.xpath("//a[@class='chzn-single']//span[contains(text(),'Select Tech/Vendor')]");
	By enterTaskName = By.xpath("//input[@placeholder='Task Name']");
	By addDescription = By.xpath("//textarea[@name='description']");
	By clickonYes=By.xpath("//label[normalize-space()='Yes']");
	By clickonNo=By.xpath("//label[normalize-space()='No']");
	By createTaskbtn=By.xpath("//a[@class='popup-submit']");
	
	// 2. public page Constructor

	public TaskPage(WebDriver driver) {
		this.driver = driver;
		// eleUtil = new ElementUtil(driver);
		vrutil = new VRUtils(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// 3. public page actoin/Methos
	public void clickmainmenu() {
		vrutil.doClick(MaintenanceMenu);
	}

	public String getmainPageTitle() {
		String mainpagetitle = driver.getTitle();
		System.out.println("Task page title: " + mainpagetitle);
		return mainpagetitle;
	}

	public void clickonsidemenu() {
		vrutil.clickElementWhenReady("Tasks", 10);
	}

	public String gettaskPageTitle() {
		String taskpagetitle = driver.getTitle();
		System.out.println("Task page title: " + taskpagetitle);
		return taskpagetitle;
	}

	public void gotoNewTask() throws InterruptedException {
		Thread.sleep(2000);
		vrutil.doClick(newTask);

	}

	public String validatepage() {
		String createtaskp = driver.findElement(By.xpath("//label[@class='reservationlabel mb-2']")).getText();
		System.out.println("Task page validation: " + createtaskp);
		return createtaskp;
	}

	// ************************ Create task page *********************//
	
	public void addTaskName(String taskName)
	{
		vrutil.doSendKeys(enterTaskName, taskName);
	}	

	public void selectUnit(String unitName) throws InterruptedException {

		driver.findElement(unitbtn).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'unitselect-popup')]//input")).sendKeys(unitName);
		driver.findElement(By.xpath("//div[contains(@class,'unitselect-popup')]//input")).sendKeys(Keys.ENTER);

	}
	public void selectvendor(String vendorName) throws InterruptedException {

		driver.findElement(vendorbtn).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(@class,'techvendorselect-popup')]//input")).sendKeys(vendorName);
		driver.findElement(By.xpath("//div[contains(@class,'techvendorselect-popup')]//input")).sendKeys(Keys.ENTER);

	}

	public void addDescription(String add) {
		vrutil.doSendKeys(addDescription, add);
	}
	public void clickOnTask()
	{
		vrutil.doClick(createTaskbtn);
	}
	public String validateServiceText() {
		String serviceText = driver.findElement(By.xpath("(//div[normalize-space()='Did you read service notes?'])")).getText();
		System.out.println("Popup Message : " + serviceText);
		return serviceText;
	}
	
	public void clickonYes() {
		vrutil.doClick(clickonYes);
	}
	public void clickonNo() {
		vrutil.doClick(clickonNo);
	}

}