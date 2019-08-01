package framework.browser;

import com.google.common.base.Strings;
import framework.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Browser {
    private static WebDriver driver;
    private static PropertiesResourceManager manager = new PropertiesResourceManager();
    private static String browserName = initBrowserName();
    private static String startPage = manager.getSystemProperty("startPage");
    private static String timeoutTime = manager.getSystemProperty("timeout");
    private static String accountName = manager.getSystemProperty("accountName");
    private static String accountPassword = manager.getSystemProperty("accountPassword");
    private static String isRemote = initGrid();

    private Browser() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = initDriver();
        }
        return driver;
    }

    public static void getStartPage() {
        driver.get(startPage);
    }

    private static WebDriver initDriver() {
        Logger.getLogger().info(browserName);
        switch (browserName.toUpperCase()) {
            case "CHROME":
                return /*isRemote.equals("true") ? initChromeRemote() : */initChrome();
            case "FIREFOX":
                return isRemote.equals("true") ? initFFRemote() : initFF();
            default:
                Logger.getLogger().info(String.format("This [%s] browser not implemented. Available browsers are: FireFox, Chrome", browserName));
                throw new NotImplementedException();
        }
    }

    private static String initGrid() {
        if (!Strings.isNullOrEmpty(System.getProperty("grid"))) {
            return System.getProperty("grid");
        }
        return manager.getSystemProperty("grid");
    }

    private static String initBrowserName() {
        if (!Strings.isNullOrEmpty(System.getProperty("browser"))) {
            return System.getProperty("browser");
        }
        return manager.getSystemProperty("browser");
    }

    private static WebDriver initChrome() {
        System.out.println(1);
        URL myTestURL = ClassLoader.getSystemResource("chromedriver");
        File myFile = null;
        try {
            myFile = new File(myTestURL.toURI());
        } catch (URISyntaxException e1) {
        }
        System.out.println((myFile.getAbsolutePath()));
        System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());
        System.out.println(12);
        return new ChromeDriver(getChromeOptions());
    }

    private static WebDriver initFF() {
        return new FirefoxDriver(getFFOptions());
    }

    private static RemoteWebDriver initChromeRemote() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());

        RemoteWebDriver remoteWebDriver = null;
        try {
            remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
        return remoteWebDriver;
    }

    private static RemoteWebDriver initFFRemote() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFFOptions());

        RemoteWebDriver remoteWebDriver = null;
        try {
            remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
        return remoteWebDriver;
    }

    private static ChromeOptions getChromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1920,1080");
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("safebrowsing.enabled", "true");
        chromeOptions.addArguments();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;
    }

    private static FirefoxOptions getFFOptions() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package");
        firefoxOptions.addArguments();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setProfile(profile);
        return firefoxOptions;
    }

    public static String getAccountName() {
        return accountName;
    }

    public static String getAccountPassword() {
        return accountPassword;
    }

    static int getTimeoutTime() {
        return Integer.parseInt(timeoutTime);
    }
}
