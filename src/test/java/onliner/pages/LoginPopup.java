package onliner.pages;

import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class LoginPopup {
    private Button btnAuthorization = new Button(By.xpath("//*[@id=\"auth-container\"]/div/div[2]/div/form/div[3]/button"));
    private TextBox txbLoginName = new TextBox(By.xpath("//*[@id=\"auth-container\"]/div/div[2]/div/form/div[1]/div/div[2]/div/div/div/div/input"));
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
