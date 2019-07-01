package onliner.pages;

import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class LoginPopup {
    private Button btnAuthorization = new Button(By.xpath("//button[contains(text(),'Войти')]"));
    private TextBox txbLoginName = new TextBox(By.xpath("//input[contains(@placeholder,'Ник или e-mail')]"));
    private TextBox txbLoginPassword = new TextBox(By.xpath("//input[@type='password']"));

    public void fillLoginName(String name){
        txbLoginName.send(name);
    }

    public void fillLoginPassword(String password){
        txbLoginPassword.send(password);
    }

    public  void clickLoginButton() {
        btnAuthorization.click();
    }
}
