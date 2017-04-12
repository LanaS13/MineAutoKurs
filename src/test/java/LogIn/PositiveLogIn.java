package LogIn;

import org.junit.Test;
import ParentTest.ParentTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PositiveLogIn extends ParentTest {

    WebDriver driver;
    Logger logger;

    public PositiveLogIn(String browser) {
        super(browser);
        logger = Logger.getLogger(getClass());
        driver = new ChromeDriver(); //to get from system params path to our driver
    }

    @Test
    public void openHomePage (){
        String url = "http://www.lyubimka.ua/";
        try {
            driver.get(url);
        }catch (Exception e){
            logger.error("Cannot open " + url);
            Assert.fail("Cannot open " + url);
        }

        driver.quit();
        /**
         * Method for open page with url
         * @param url
         */
    /*public void open(String url){
        try {
            driver.get(url);
            logger.info("Page was opened with url " + url);
        } catch (Exception e){
            logger.error("Can not open " + url);
            Assert.fail("Can not open " + url);
        }*/
    }
}

