package framework;

import framework.browser.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public abstract class BaseTest {

    public abstract void run();

    @BeforeTest
    public void setUp() {
        Browser.getInstance().manage().window().maximize();
    }

    @AfterTest
    public void end() {
        Browser.getInstance().quit();
    }

    @Test
    public void test() {
        Browser.getStartPage();
        run();
    }

    protected void log(String message) {
        Logger.getLogger().info(message);
    }

}
