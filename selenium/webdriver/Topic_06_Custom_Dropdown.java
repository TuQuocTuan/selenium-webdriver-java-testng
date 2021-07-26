package webdriver;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Custom_Dropdown {
	WebDriver driver;
	//Wait
	WebDriverWait explicitWait ;
	String projectPath = System.getProperty("user.dir");
	//Inject 1 javascript code
	JavascriptExecutor jsExcutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
		//Ép kiểu tường minh
		jsExcutor = (JavascriptExecutor)driver;
		//Ép kiểu ngầm định
		//nhỏ -> lớn
		int price = 156000;
		float size = price ;
		//lớn -> nhỏ (tường minh)
		short sPrice = (short) price;

}
	
	public void TC_01_JQuery() {
		 driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		 
		 selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","5");
		 sleepInSecond(3);		
		 assertTrue( driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()= '5']")).isDisplayed());
		 
		 selectItemInCustomDropdown("//span[@id='number-button']","//div[@id='ui-id-15']","15");
		 sleepInSecond(3);
		 assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text']")).isDisplayed());
	}
	
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']","//span[@class='text' and text()='Stevie Feliciano']","Stevie Feliciano");
		sleepInSecond(3);
		assertTrue(	driver.findElement(By.xpath("//div[@class='divider text']")).isDisplayed());
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']","//span[@class='text' and text()='Christian']","Christian");
		sleepInSecond(3);
		assertTrue(driver.findElement(By.xpath("//div[@class='divider text']")).isDisplayed());
	}
	
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//a[@href='javascript:void(0)']","Second Option");
		sleepInSecond(3);
		assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).isDisplayed());
		
	}
	@Test
	public void TC_04_Angular() {
		driver.get("https://valor-software.com/ng2-select/");
		
		selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']","//a[@class='dropdown-item']","Stockholm");
		sleepInSecond(3);
		assertTrue(driver.findElement(By.xpath("//tab[@heading='Single']//span[@tabindex=\"-1\"]")).isDisplayed());
		
		assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']")).getText(),"Select a single city");
	}
	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem){
		//click vào 1 element để xổ hết ra các items trong dropdown -> Parent Element
		driver.findElement(By.xpath(parentXpath)).click();
		
		//chờ các items load ra thành công -> ChildElement
		//lấy hết các items này lưu vào 1 list element
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyệt qua từng items  
		for (WebElement item : allItems) {
			//kt xem có bằng item mình mong muốn ko(ko hiển thị thì scroll đến rồi click)
			if (item.getText().trim().equals(expectedItem)) {
				if(!item.isDisplayed()) {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
				}
				item.click();
				break;	
			}
		}
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
