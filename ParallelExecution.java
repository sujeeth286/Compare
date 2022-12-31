package Sam;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ParallelExecution
{
	public static String url1="https://flipkart.com/";
	public static String url2="https://meesho.com";
	public static String closebutton_path="//button[@class='_2KpZ6l _2doB4z']";
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String product_searchtextfield_path="//input[@placeholder ='Search for products, brands and more']";
	public static String productname="sneakers men";
	public static String searchbutton_path="//button[@type='submit']";
	public static String price_sorting_path="//*[@id='container']/div/div[3]/div[1]/div[2]/div[1]/div/div/div[2]/div[3]";
	public static int flip_low;
	
	//meesho
	public static String meesho_prices_path="//div[@class='NewProductCardstyled__ProductHeaderWrapper-sc-6y2tys-30 gspQJ']/span/div/h5";
	public static String meesho_lowest_price;
	public static String meesho_url="https://meesho.com";
	public static String meesho_search_path="//*[@id='__next']/div[2]/div[1]/div/div[2]/div[1]/input";
	public static String meesho_sort_path="//*[@id='__next']/div[3]/div/div[3]/div[1]/div/div[1]/ul/li/div[1]/div/p";
	public static String meesho_price_low_high_path="//*[@id='__next']/div[3]/div/div[3]/div[1]/div/div[1]/ul/li/div[2]/a[4]/div/span";
	public static int meesho_low;
			
	
	//public static String searchedproducts_price_path="//div[@class='_2WkVRV']/../a/div/div[@class='_30jeq3']";
	public static String price_path="//div[@class='_30jeq3']";
	public static String pagination_path="//nav[@class='yFHi8N']/a";
	
	@Test
	public static void open_Browser1() throws InterruptedException, NumberFormatException
	{
		System.setProperty("webdriver.chrome.driver", "./Software/chromedriver.exe");
		driver=new ChromeDriver();
		wait= new WebDriverWait(driver,5);
		driver.manage().window().maximize();
		
		driver.get(url1);
	
		

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(closebutton_path)))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(product_searchtextfield_path)))).sendKeys(productname);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(searchbutton_path)))).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(price_sorting_path)).click();
		List<WebElement> pagination = driver.findElements(By.xpath(pagination_path));
		int psize = pagination.size();
		Thread.sleep(2000);
		List<WebElement> prices = driver.findElements(By.xpath(price_path));
		
			for(int j=0;j<prices.size();j++)
			{
				String price = prices.get(j).getText();
				Thread.sleep(1000);
				String sl = Integer.toString(j+1);
				System.out.println("Price of "+sl+ " is " + price);
			}
			String flip_lowest = prices.get(0).getText();
			String f_lowest = flip_lowest.substring(1);
		  flip_low = Integer.parseInt(f_lowest);
		  System.out.println("flipkart lowest is "+flip_low );
			
			
		
	
	}
	
	/*public static String meesho_prices_path="//div[@class='NewProductCardstyled__ProductHeaderWrapper-sc-6y2tys-30 gspQJ']/span/div/h5";
	public static String meesho_lowest_price;
	public static String meesho_url="https://meesho.com";
	public static String meesho_search_path="//*[@id='__next']/div[2]/div[1]/div/div[2]/div[1]/input";
	public static String meesho_sort_path="//*[@id='__next']/div[3]/div/div[3]/div[1]/div/div[1]/ul/li/div[1]/div/p";
	public static String meesho_price_low_high_path="//*[@id='__next']/div[3]/div/div[3]/div[1]/div/div[1]/ul/li/div[2]/a[4]/div/span";*/
	
	@Test
     public static void open_browser1() throws InterruptedException, AWTException
     	{
			System.setProperty("webdriver.chrome.driver", "./Software/chromedriver.exe");
			 driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			driver.get(meesho_url);
     	
			driver.findElement(By.xpath(meesho_search_path)).sendKeys("sneakers men");
			Thread.sleep(2000);
			driver.findElement(By.xpath(meesho_search_path)).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath(meesho_sort_path)).click();
			Robot copy= new Robot();
			Thread.sleep(2000);
			copy.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			copy.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			copy.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			copy.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			copy.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			driver.findElement(By.xpath(meesho_price_low_high_path)).click();
			Thread.sleep(4000);
			List<WebElement> prices = driver.findElements(By.xpath(meesho_prices_path));
			int size = prices.size();
			System.out.println(prices.size());
			Thread.sleep(4000);
			for(int i=0;i<size;i++)
			{
				meesho_lowest_price=prices.get(0).getText();
				String price = prices.get(i).getText();
				
				System.out.println(price);
				Thread.sleep(2000);
			}
			System.out.println("Meesho's lowest price is "+meesho_lowest_price);
			String m_lowest = meesho_lowest_price.substring(1, 4);
			meesho_low = Integer.parseInt(m_lowest);
			System.out.println("meesho lowest is "+meesho_low);
			
			if(meesho_low<flip_low)
			{
				System.out.println("Meesho offers the lowest price range");
			}
			else
			{
				System.out.println("Flipkart offers the lowest price range");
			}
			
			
			
	
	
     	}



}
