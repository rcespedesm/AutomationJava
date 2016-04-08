package fundacionjala.org;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Location {
	private WebDriver driver;

	  @BeforeClass
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //preCondition
	    common.login(driver);   
	  }

	  @Test
	  public void testCreateLocation() throws Exception {
	    driver.findElement(By.linkText("Locations")).click();
	    driver.findElement(By.xpath("//div[4]/div/button")).click();
	    driver.findElement(By.id("location-add-name")).clear();
	    driver.findElement(By.id("location-add-name")).sendKeys("Floor2");
	    driver.findElement(By.id("location-add-display-name")).clear();
	    driver.findElement(By.id("location-add-display-name")).sendKeys("Floor2");
	    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	    driver.findElement(By.cssSelector("b.ng-binding")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    driver.findElement(By.id("location-add-description")).clear();
	    driver.findElement(By.id("location-add-description")).sendKeys("Optional");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    //(new WebDriverWait(driver, 5))
	    //.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ngViewport.ng-scope")));
	    Assert.assertEquals("Floor2\n\n \n  \n Floor2\n\n \n  \n x0", driver.findElement(By.cssSelector("div.ngViewport.ng-scope")).getText());
	  }

	  @AfterClass
	  public void tearDown() throws Exception {
	    driver.quit();
	  }

}
