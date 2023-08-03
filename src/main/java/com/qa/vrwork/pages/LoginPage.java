package com.qa.vrwork.pages;

import com.qa.vrwork.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    //1. private By Locators - page locators
    By emailAddress = By.id("users-email");
    By NextBtn = By.xpath("//a[normalize-space()='Next']");
    By password = By.id("users-password");
    By selectdrp = By.name("userlayout");
    By submitbtn = By.id("loginSubmitBtn");
    By forgotlink = By.linkText("Forgot Password?");
    By ownsignlink = By.linkText("Owner Sign In");

    //2. public page Constructor

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    //3.  public page actoin/Methos

    public String getLoginPageTitle()
    {
        String actTitle=driver.getTitle();
        System.out.println(actTitle);
        return actTitle;
    }
    public String getLoginPageURL()
    {
        String actURL=driver.getCurrentUrl();
        System.out.println(actURL);
        return actURL;
    }

    public boolean IsForgotpwdLinkExist()
    {
        return driver.findElement(forgotlink).isDisplayed();
    }

    public boolean IsOwnSignlinkExist()
    {
        return driver.findElement(ownsignlink).isDisplayed();
    }
    public String VRLogin(String username, String pwd,String visibleText) throws InterruptedException {
        System.out.println("App creds are : "+username+":"+pwd);
        eleUtil.doSendKeys(emailAddress,username);
        eleUtil.doClick(NextBtn);
        Thread.sleep(1000);
        eleUtil.doSendKeys(password,pwd);
        eleUtil.doSelectDropDownByVisibleText(selectdrp,visibleText);
        eleUtil.doClick(submitbtn);
        String acttitle=driver.getTitle();
        return acttitle;
    }



}
