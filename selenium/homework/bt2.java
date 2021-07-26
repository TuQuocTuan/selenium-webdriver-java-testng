package homework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class bt2 {
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_isDisplayed() {
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='mail']")).getText(),"Email:");
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='under_18']")).getText(),"Under 18");
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='edu']")).getText(),"Education:");
	driver.findElement(By.xpath("//img[@alt='User Avatar 05']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'Name: User5')]")).getText(),"Name: User5");

	driver.findElement(By.xpath("//input[@name='user_email']")).sendKeys("Automation Testing");
	Assert.assertEquals(driver.findElement(By.xpath("//input[@name='user_email']")).getAttribute("value"),"Automation Testing");
	driver.findElement(By.xpath("//input[@id='under_18']")).click();
	}
	@Test
	public void TC_02_enable() {
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='job1']")).getText(),"Job Role 01 - Single Dropdown:");
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='job2']")).getText(),"Job Role 02 - Multiple Dropdown:");
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='development']")).getText(),"Development");
	Assert.assertEquals(driver.findElement(By.xpath("//label[@for='slider-1']")).getText(),"Slider 01:");
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
