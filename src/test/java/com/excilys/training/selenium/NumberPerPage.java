package com.excilys.training.selenium;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.configuration.AppSpringConfig;
import com.excilys.training.service.ComputerService;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppSpringConfig.class)
public class NumberPerPage {

	@Autowired
	private ComputerService computerService;

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeClass
	public static void setUpClass() throws IOException, SQLException {
		WebDriverManager.phantomjs().setup();

	}

	@Before
	public void setUp() {
		driver = new PhantomJSDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void NumberPerPageTest() {
		driver.get("http://localhost:8080/CDB/dashboard");
		driver.manage().window().setSize(new Dimension(1280, 960));
		
		driver.findElement(By.cssSelector(".btn-default:nth-child(1)")).click();
		assertEquals(getComputersTableLines().size(), 10);
		
		driver.findElement(By.cssSelector(".btn-group > .btn:nth-child(2)")).click();
		assertEquals(getComputersTableLines().size(), 50);
		
		driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		assertEquals(getComputersTableLines().size(), 100);

	}
	
    private List<WebElement> getComputersTableLines() {
        return driver.findElements(By.xpath("//tbody[@id='results']/tr"));
    }
}
