package com.qa.vrwork.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;
    private Properties prop;

    public OptionsManager(Properties prop)
    {
        this.prop=prop;
    }
    public ChromeOptions getChromeOption()
    {
        co=new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("incognito")))
        {
            co.addArguments("--incognito");
        }
        return co;
    }

}
