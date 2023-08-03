package com.qa.vrwork.factory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author parek
 *
 */

public class DriverFactory {
	static WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
//	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This is used to initiliza the driver
	 * 
	 * @return it returns driver
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");

		System.out.println("Browser name is : " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase()) {
		case "chrome":
			driver=new ChromeDriver(optionsManager.getChromeOption());
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("Plz pass the right browser...." + browserName);
			break;
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return getDriver();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * This method is used to init the Properties
	 * 
	 * @return Properties
	 */
	public Properties initprop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Take Screenshot
	 * 
	 * @param methodName
	 * @return
	 */

	public static String getScreenshot(String methodName) {
		{
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_"
					+ System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileHandler.copy(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}

	}
}
