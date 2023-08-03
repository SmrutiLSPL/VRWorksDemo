package com.qa.vrwork.base;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.vrwork.factory.DriverFactory;
import com.qa.vrwork.pages.InvoicePage;
import com.qa.vrwork.pages.LoginPage;
import com.qa.vrwork.pages.MyTasksPage;
import com.qa.vrwork.pages.TaskPage;
import com.qa.vrwork.utils.ElementUtil;
import com.qa.vrwork.utils.SessionManager;
import com.qa.vrwork.utils.VRUtils;
public class BasePage {

    public static WebDriver driver;
   
    protected Properties prop;
  
    protected withoutloginPage wlogin;
    DriverFactory df;
    public SessionManager sessionManager;
    public boolean isLoggedin = false;
    protected VRUtils vrutil;
    protected LoginPage loginPage;
    protected TaskPage taskPage;
    protected MyTasksPage mytaskPage;
    protected InvoicePage invoicePage;
    
    @BeforeTest
    public void setup() {
    df=new DriverFactory();
    prop=df.initprop();
    driver= df.initDriver(prop);
    loginPage=new LoginPage(driver);
    wlogin=new withoutloginPage(driver);
    taskPage=new TaskPage(driver);
    mytaskPage=new MyTasksPage(driver);
    invoicePage=new InvoicePage(driver);
    }



    @AfterTest
    public void tearDown() {
        //driver.quit();
    }


}
