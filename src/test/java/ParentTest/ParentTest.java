package ParentTest;

import Utils.Utils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;

@RunWith(value = Parameterized.class)
public class ParentTest {

    protected WebDriver driver;
    private Logger log = Logger.getLogger(getClass());
    private Utils utils = new Utils();

    private boolean isTestPass = false;

    private String pathToScreenShot;
    private String browser;//WHERE DO WE GET THIS VALUE to constructor

    //constructor
    public ParentTest(String browser) {
        this.browser = browser;
    }

    //parameter list of browser are being used for test runs
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//              {"fireFox"}
//                ,
                {"chrome"}
        });
    }

    //?? what is this for
    @Rule
    public TestName testName = new TestName();

    @Before
    public void SetUp() {
        File file = new File("");

        if ("fireFox".equals(browser)) {
            log.info("FireFox will be started");
            File fileFF = new File("./drivers/geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", fileFF.getAbsolutePath());
            driver = new FirefoxDriver();
            log.info(" FireFox is started");
        } else if ("chrome".equals(browser)) {
            log.info("Chrome will be started");
            File fileFF = new File("./drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
            driver = new ChromeDriver();
            log.info(" Chrome is started");
        }

        pathToScreenShot = file.getAbsolutePath() + "\\target\\screenshot\\" + this.getClass().getPackage().getName()
                + "\\" + this.getClass().getSimpleName() + "\\" + this.testName.getMethodName() + "-" + browser + ".jpg";

        //to maximize a window
        driver.manage().window().maximize();
        //time 10 secs to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //loginPage = new LoginPage(driver);
        /*homePage = new HomePage(driver);
        workersPage = new WorkersPage(driver);*/
    }

        @After
        public void tearDown() {
            if (!(driver == null)) {
                if (!isTestPass) {
                    utils.screenShot(pathToScreenShot, driver);
                }
                driver.quit();
            }
        }

       private void setTestPass() {
            isTestPass = true;
        }
}
