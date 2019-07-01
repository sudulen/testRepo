package framework.elements;

import framework.browser.Browser;
import framework.browser.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement {
    private final By locator;
    private Wait wait =  new Wait();

   protected BaseElement(By locator) {
        this.locator = locator;
    }

    WebElement getElement() {
        wait.explicitWait(getLocator());
        return Browser.getInstance().findElement(locator);
    }

    public String getText() {
        return getElement().getText();
    }

    private By getLocator(){ return this.locator;}

    public void waitElementVisibleAndClick(){
        Boolean isVisible = false;
        while(isVisible.equals(true)){
            isVisible = isClickable(getElement(),Browser.getInstance());
        }
        getElement().click();
    }

    private static boolean isClickable(WebElement el, WebDriver driver)
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void click(){
        new Wait().explicitWait(getLocator());
        getElement().click();
    }

    public void implicitWaitForElement(){
        wait.implicitWait();
    }

    public void fluentWaitForElement(){ wait.fluentWait(getLocator()); }
}
