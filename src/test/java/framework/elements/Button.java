package framework.elements;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Button extends BaseElement {

    public Button(By locator) {
        super(locator);
    }

    public void click() { getElement().click(); }

    public void actionsClick() {
        Actions ob = new Actions(Browser.getInstance());
        ob.click(getElement());
        Action actions = ob.build();
        actions.perform();
    }
}
