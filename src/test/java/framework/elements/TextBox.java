package framework.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(By locator) {
        super(locator);
    }

    public void send(String text) {
        getElement().sendKeys(text);
    }
}
