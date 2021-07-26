package webdriver;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.WebElement;








public class Topic_05_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String editEmail,FirstName,LastName,CompanyName,day,month,year;
	Select select;
	List<String> expectedItemstext;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		editEmail = "tuantu" + generateEmail();
		FirstName = "Tuan";
		LastName = "Tu";
		CompanyName = "Viet Nam LLC" ;
		day = "10";
		month = "August";
		year = "1986";
		expectedItemstext = new ArrayList<>(Arrays.asList("Month","January","February","March","April","May","June","July","August","September","October","November","December"));
	
	}

	@Test
	public void TC_01_() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(LastName);
		
		//Select item
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText(day);
		assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(editEmail);
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(CompanyName);
		
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		//Kiem tra xem chon dung item hay chua
		//assertEquals(select.getFirstSelectedOption().getText(), "10");
		
		//Verify trong dropdown co bao nhiu item
		//assertEquals(select.getOptions().size(),32);
		
		//Verify dropdown cho chon nhieu item cung luc
		//assertFalse(select.isMultiple());
		
		assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		assertEquals(driver.findElement(By.xpath("//input[@value='Tuan']")).getAttribute("value"), FirstName);
		assertEquals(driver.findElement(By.xpath("//input[@value='Tu']")).getAttribute("value"), LastName);
		assertEquals(driver.findElement(By.xpath("//input[@value='"+editEmail+"']")).getAttribute("value"), editEmail);
		assertEquals(driver.findElement(By.xpath("//input[@value='Viet Nam LLC']")).getAttribute("value"), CompanyName);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		assertEquals(select.getFirstSelectedOption().getText(), year);
	}
	
	@Test
	public void TC_02_() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		
		List<WebElement> allItems = select.getOptions();
		List<String> allItemsText = new ArrayList<>(); 
		
		//Duyệt qua tất cả item đang có trong list
		for (WebElement item : allItems) {
			System.out.println(item.getText());
			allItemsText.add(item.getText());
		}
		assertEquals(expectedItemstext, allItemsText);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String generateEmail() {
		Random rand= new Random();
		return rand.nextInt(9999) + "@mail.net";
		}
	public void sleepInSecond(long timeoutInSecond)  {
	try {
		Thread.sleep(timeoutInSecond *1000);
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}
