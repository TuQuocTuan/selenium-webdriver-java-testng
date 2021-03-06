package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Run_on_browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	public void beforeClass() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TC_01_Run_on_Firefox() {
		//older firefox version(47.0.2)
		driver = new FirefoxDriver();
		
		//Latest version (>=48)
		//System.setProperty("webdriver.gecko.driver", "\\browserDrivers\\geckodriver_path");
		
		driver.get("http://live.demoguru99.com/");
		driver.quit();
	}

	@Test	
	public void TC_02_Run_on_Chrome() {
		System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		
		driver=new ChromeDriver();
		
		driver.get("http://live.demoguru99.com/");
		driver.quit();
	}

	@Test
	public void TC_03_Run_on_Edge_Chrominium() {
		System.setProperty("webdriver.edge.driver",projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver=new EdgeDriver();
		
		driver.get("http://live.demoguru99.com/");
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
	try {
		Thread.sleep(timeoutInSecond *1000);
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}


