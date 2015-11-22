package jp.selenium.sample.SahaginJavaSample;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.sahagin.runlib.external.adapter.webdriver.WebDriverAdapter;

public class SampleTest
{
    private WebDriver driver;

    @Before
    public void setUp() throws Exception
    {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverAdapter.setAdapter(driver);
    }

    @Test
    public void test() throws Exception
    {
        driver.get("http://example.selenium.jp/reserveApp_Renewal/");
        new Select(driver.findElement(By.id("reserve_term"))).selectByVisibleText("7");
        new Select(driver.findElement(By.id("headcount"))).selectByVisibleText("5");
        driver.findElement(By.id("breakfast_off")).click();
        driver.findElement(By.id("agree_and_goto_next")).click();
        driver.findElement(By.id("returnto_index")).click();
        // 次のテストは必ず失敗する。
        assertThat("人数が期待値と異なる", driver.findElement(By.id("headcount")).getAttribute("value"), is("4"));
    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}
