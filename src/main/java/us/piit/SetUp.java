package us.piit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class SetUp {
    Logger log= LogManager.getLogger(SetUp.class.getName());
    WebDriver driver;

    //this method is to run in the cloud
    public void getCloudDriver(String envName,String os,String osVersion,String browserName,String browserVersion,String username,String password) throws MalformedURLException {
        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setCapability("os",os);
        cap.setCapability("os_version",osVersion);
        cap.setCapability("browser",browserName);
        cap.setCapability("browser_version",browserVersion);
        if(envName.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resulution","1024x768");
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"),cap);
        }else if(envName.equalsIgnoreCase("saucelabs")){
        driver=new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.saucelabs.com:80/wd/hub"),cap);
        }
    }

    //this method is to run it locally
    public void getLocalDriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            log.info("chrome browser open success");
        }else if(browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            log.info("firefox browser open success");
        } else if(browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            log.info("edge browser open success");
        }
    }
    @Parameters({"useCloudEnv","envName","os","osversion","browserName","browservesion","url"})
    @BeforeMethod
    public void setup(@Optional("false")String useCloudEnv,@Optional("browserstack") String envName,@Optional("windows")String os,
       @Optional("11") String osversion,@Optional("chrome") String browserName,
        @Optional("111") String browservesion,@Optional("https://www.google.com") String url) throws MalformedURLException {
        if(useCloudEnv.equalsIgnoreCase("true")){
            getCloudDriver(envName,os,osversion,browserName,browservesion,"sofianesehad_ClW76g","2f3G7QUqgScR9c7bbahr");
        } else if (useCloudEnv.equalsIgnoreCase("false")) {
           getLocalDriver(browserName);
        }
        //open the browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
    }
    @AfterMethod
    public void teardown(){
        //close browser
        driver.quit();
        log.info("browser close success");
    }

  //-------------------------------------------------------------------------------------------------------------------
  //                                         Selenium methods
  //-------------------------------------------------------------------------------------------------------------------
public String getCurrentTtile(){
        return driver.getTitle();
}
    public String getElementText(String locator){
    try{
       return driver.findElement(By.cssSelector(locator)).getText();
    }catch (Exception e){
        return  driver.findElement(By.xpath(locator)).getText();
    }
}
  public void clickOn(String locator){
        try{
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).click();
        }
  }
    public void type(String locator,String text){
        try{
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }
    }
    public void hoverOver(String locator){
        Actions action=new Actions(driver);
        try{
           action.moveToElement(driver.findElement(By.cssSelector(locator))).build().perform(); ;
        }catch (Exception e){
            action.moveToElement(driver.findElement(By.xpath(locator))).build().perform(); ;
        }
    }

    //we handle the throw with try and catch to avoid another mistake in the class where we are going to use it
    public void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isVisible(String locator){
        try{
           return driver.findElement(By.cssSelector(locator)).isDisplayed();
        }catch (Exception e){
           return driver.findElement(By.xpath(locator)).isDisplayed();
        }
    }
    public boolean isInteractible(String locator){
        try{
            return driver.findElement(By.cssSelector(locator)).isEnabled();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).isEnabled();
        }
    }
    public boolean isChecked(String locator){
        try{
            return driver.findElement(By.cssSelector(locator)).isSelected();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).isSelected();
        }
    }
}
