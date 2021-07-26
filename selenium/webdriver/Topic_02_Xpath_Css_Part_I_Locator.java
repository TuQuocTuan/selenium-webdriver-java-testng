package webdriver;
 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I_Locator {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		sleepInSecond(1);
		driver.findElement(By.id("gender-male")).click();
		sleepInSecond(1);
	}

	@Test
	public void TC_02_class() {
         driver.navigate().refresh();
		
		driver.findElement(By.className("search-box-text")).sendKeys("MacBook");
		sleepInSecond(1);
		driver.findElement(By.className("search-box-button")).click();
		sleepInSecond(1);
	}

	@Test
	public void TC_03_name() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.name("NewsletterEmail")).sendKeys("tuan.tuquoc1502@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.name("Email")).sendKeys("tuan.tuquoc1502@gmail.com");
		sleepInSecond(3);
		driver.close();
	}
	
    @Test
    public void TC_04_tagname() {
    	System.out.println("Sum link=" + driver.findElements(By.tagName("a")).size());
	
		System.out.println("Sum input=" + driver.findElements(By.tagName("input")).size());
    }
    
    @Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Log in")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		 driver.navigate().refresh();
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Automation");
		sleepInSecond(3);
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[contains(@href,'recentlyviewedproducts')]")).click();
	}
  
	@AfterClass
	public void afterclass() {	
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
