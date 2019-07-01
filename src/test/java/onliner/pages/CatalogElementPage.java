package onliner.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogElementPage {
    private Label lblTabLabel = new Label(By.xpath("//h1[@class='schema-header__title']"));
    private Button btnOpenUserBar = new Button(By.xpath("//div[contains(@class,'b-top-profile__item_arrow')]"));
    private Button btnLogutUserBar = new Button(By.xpath("//a[contains(text(),'Выйти')]"));
    private Button btnOpenLoginPopup = new Button(By.xpath("//div[contains(text(),'Вход')]"));

    public void openUserBar(){
        btnOpenUserBar.click();
    }

    public void clickLogout(){
        openUserBar();
        btnLogutUserBar.actionsClick();
    }

    public String getTabLabel(){
        return lblTabLabel.getText();
    }

    public void pageLoaded(){
        lblTabLabel.implicitWaitForElement();
    }

    public void isUserLogouted(){ btnOpenLoginPopup.implicitWaitForElement(); }
}
