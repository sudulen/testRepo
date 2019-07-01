package onliner.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class StartPage {
    private Button btnOpenLoginPopup = new Button(By.xpath("//*[@id=\"userbar\"]/div/div/div/div[1]"));
    private Button btnCatalog = new Button(By.xpath("//*[@id=\"container\"]/div/div/header/div[2]/div/nav/ul[1]/li[1]/a/span"));
    private Label lblUserAuthorizated = new Label(By.xpath("//div[contains(@class,'js-header-user-avatar')]"));

    public void openLoginPopup(){
        btnOpenLoginPopup.click();
    }

    public void navigateToCatalogPage(){
        btnCatalog.click();
    }

    public void isUserAuthorizated(){ lblUserAuthorizated.implicitWaitForElement(); }
}
