package com.qa.vrwork.base;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.vrwork.factory.DriverFactory;
import com.qa.vrwork.factory.OptionsManager;
import com.qa.vrwork.pages.LoginPage;
import com.qa.vrwork.utils.ElementUtil;
import com.qa.vrwork.utils.JSONUtils;
import com.qa.vrwork.utils.SessionManager;

public class withoutloginPage {
	 public static WebDriver driver;
	    protected Properties prop;

	    protected LoginPage loginPage;
	    protected withoutloginPage wlogin;
	    DriverFactory df;
	    public SessionManager sessionManager;
	    public boolean isLoggedin = false;
	    protected ElementUtil eleutil;
	    public static String username;
	    public static String password;
	    public static String selectView;

    //1. private By Locators - page locators
    By emailAddress=By.id("users-email");
    By NextBtn=By.xpath("//a[normalize-space()='Next']");
    By password1=By.id("users-password");
    By selectdrp=By.xpath("//select[@name='userlayout']");
    By submitbtn=By.id("loginSubmitBtn");


//2. public page Constructor

    public withoutloginPage(WebDriver driver)
    {
        this.driver=driver;
        eleutil = new ElementUtil(driver);
        this.prop = prop;
 
    }


    public void defaultlogin(String username,String pwd,String visibleText)
    {
        sessionManager = new SessionManager(driver);


// Get Cookies
        this.putDataToSession();
//		System.out.println(sessionManager.getCookiesData().toString());
//		System.exit(0);
        if (this.openDispatch()) {

        } else {
//			System.out.println("hre");
            try {
                this.loginUser("bestbeach","Winter-house2021","Select view or Default");
                sessionManager.storeSessionFile("VRWorks", "bestbeach");
            } catch (IOException e) {
//				
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean openDispatch() {
       // driver.get("https://vrmanaged1.com/login");
        try {
            return driver.findElement(By.xpath("//h4[@class='mb-3 mb-md-0']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loginUser(String username,String pwd,String visibleText) throws InterruptedException{
//			System.out.println("user login called");
        driver.manage().deleteCookie(driver.manage().getCookieNamed("CAKEPHP"));
        // driver.get("https://vrmanaged1.com/login");
        eleutil.doSendKeys(emailAddress,username);
        eleutil.doClick(NextBtn);
        Thread.sleep(2000);
        eleutil.doSendKeys(password1,pwd);
        eleutil.doSelectDropDownByVisibleText(selectdrp,visibleText);
        eleutil.doClick(submitbtn);
        // TODO : identify that user is successfully loggedin
//		  System.out.println("user login called finish");
        isLoggedin = true;
//		  boolean status = false;
//
        return isLoggedin;

    }

    public void putDataToSession() {
        try {
            JSONObject jo = JSONUtils.parseJsonFile("VRWorks.json");
            JSONObject session_data = jo.getJSONObject("session_data");
            JSONArray cookies = session_data.getJSONArray("cookies");
            JSONObject cookie = (cookies.getJSONObject(cookies.length() - 1));
            cookie.put("path", "/");
            cookie.put("domain", "vrmanaged1.com");
            sessionManager.setCookies(cookie);
        } catch (Exception e) {
        }
    }
}
