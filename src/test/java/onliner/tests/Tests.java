package onliner.tests;

import framework.BaseTest;
import framework.Logger;
import framework.browser.Browser;
import onliner.pages.CatalogElementPage;
import onliner.pages.CatalogPage;
import onliner.pages.LoginPopup;
import onliner.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Random;

public class Tests extends BaseTest {

    private int index = new Random().nextInt(11 - 1 + 1) + 1;
    private StartPage startPage = new StartPage();
    private CatalogPage catalogPage = new CatalogPage();
    private CatalogElementPage catalogElementPage = new CatalogElementPage();
    private LoginPopup loginPopup = new LoginPopup();
    private String catalogElementTitle;

    @BeforeTest
    public void setUp() {
        Browser.getInstance().manage().window().maximize();
    }

    @AfterTest
    public void end() {
        Browser.getInstance().quit();
    }

    @Override
    public void run() {
        Browser.getStartPage();

        log("Starting login");
        startPage.openLoginPopup();
        loginPopup.fillLoginName(Browser.getAccountName());
        loginPopup.fillLoginPassword(Browser.getAccountPassword());
        loginPopup.clickLoginButton();
        startPage.isUserAuthorizated();
        log("Authorization finished");

        log("Clicking on Catalog button");
        startPage.navigateToCatalogPage();

        log("Selecting random catalog element");
        catalogElementTitle = catalogPage.getCatalogElementTitle(index);
        catalogPage.selectCatalogElementByIndex(index);
    }

    private void checkTitles() {
        String messageThatTitleNotTheSame = "Titles not equals. On catalog page title =" + catalogElementTitle + " on element page title =" + catalogElementPage.getTabLabel();
        Assert.assertEquals(catalogElementTitle, catalogElementPage.getTabLabel(), messageThatTitleNotTheSame);
    }
}
