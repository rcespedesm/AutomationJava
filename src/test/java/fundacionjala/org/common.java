package fundacionjala.org;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class common {
	public static void login(WebDriver driver) {
		driver.get(Configuration.baseUrl + "/admin/#/login");
	    driver.findElement(By.id("loginUsername")).clear();
	    driver.findElement(By.id("loginUsername")).sendKeys(Configuration.roomManagerUsername);
	    driver.findElement(By.id("loginPassword")).clear();
	    driver.findElement(By.id("loginPassword")).sendKeys(Configuration.roomManagerPassword);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
		}
	
	public static void addServer(WebDriver driver) {
		driver.findElement(By.linkText("Email Servers")).click();
		driver.findElement(By.xpath("//div[2]/button")).click();
	    driver.findElement(By.id("add-mailserver-hostname")).clear();
	    driver.findElement(By.id("add-mailserver-hostname")).sendKeys(Configuration.exchangeServer);
	    driver.findElement(By.id("add-mailserver-username")).clear();
	    driver.findElement(By.id("add-mailserver-username")).sendKeys(Configuration.domainControllerUsername);
	    driver.findElement(By.id("add-mailserver-password")).clear();
	    driver.findElement(By.id("add-mailserver-password")).sendKeys(Configuration.domainControllerPassword);
	    driver.findElement(By.id("add-mailserver-description")).clear();
	    driver.findElement(By.id("add-mailserver-description")).sendKeys("optional");
	    driver.findElement(By.cssSelector("div.modal-footer.ng-scope > button.btn.btn-primary")).click();
	    (new WebDriverWait(driver, 5))
	      .until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(Configuration.exchangeServer)));
		}
	
	public static void removeServer(WebDriver driver) {
		driver.findElement(By.linkText("Email Servers")).click();
	    driver.findElement(By.xpath("//button[2]")).click();
	    driver.findElement(By.cssSelector("div.pull-right > button.btn-clear.info")).click();
		}
}
