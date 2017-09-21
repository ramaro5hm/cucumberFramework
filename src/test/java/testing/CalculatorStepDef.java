package testing;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CalculatorStepDef {

	WebDriver driver;
	String absolutePath = new File("").getAbsolutePath();
	@Given("^go to google$")
	public void go_to_google() throws Throwable {
	System.setProperty("webdriver.chrome.driver", absolutePath + "/src//test//resources//chromedriver.exe");
	driver= new ChromeDriver();  
	driver.navigate().to("http://www.google.com");
	}

	@When("^type addition of two numbers, (\\d+) and (\\d+)$")
	public void type_addition_of_two_numbers_and(int arg1, int arg2) throws Throwable {
		WebElement txtSearch=driver.findElement(By.id("lst-ib"));
		txtSearch.sendKeys(String.valueOf(arg1)+"+"+String.valueOf(arg2));
		txtSearch.submit();
	}

	@Then("^the result should be (\\d+)$")
	public void the_result_should_be(int arg1) throws Throwable {
	   Thread.sleep(1500);
	    WebElement txtRes=driver.findElement(By.id("cwos"));
	    int res=Integer.valueOf(txtRes.getText());
	    Assert.assertEquals(arg1,res);
	}

	private boolean isDriverExist(){
		try {
			driver.getCurrentUrl();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	 @After()
	 public void after(Scenario scenario){
		 if(isDriverExist()){
			 if (scenario.isFailed()) {
					scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
				}
			 driver.close();
			 driver.quit();
		 }
	 }
}
