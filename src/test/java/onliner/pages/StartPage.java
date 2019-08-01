package onliner.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class StartPage {
    private Button btnOpenLoginPopup = new Button(By.xpath("//div[contains(text(),'Вход')]"));
    private Button btnCatalog = new Button(By.xpath("//nav[@class='b-top-navigation']//span[contains(text(),'Каталог')]"));
    private Label lblUserAuthorizated = new Label(By.xpath("//div[contains(@class,'js-header-user-avatar')]"));

    public void openLoginPopup(){
        btnOpenLoginPopup.click();
    }

    public void navigateToCatalogPage(){
        btnCatalog.click();
    }

    public void isUserAuthorizated(){ lblUserAuthorizated.implicitWaitForElement(); }
}
