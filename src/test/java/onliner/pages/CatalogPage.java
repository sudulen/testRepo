package onliner.pages;

import framework.elements.Button;
import org.openqa.selenium.By;

public class CatalogPage {
    private static String selectedElementTitle;

    public void selectCatalogElementByIndex(int index){
        new Button(By.xpath("//ul[@class='catalog-bar__list']//li[" + index + "]")).click();
    }

    public String getCatalogElementTitle(int index){
        selectedElementTitle = new Button(By.xpath("//ul[@class='catalog-bar__list']//li[" + index + "]/a")).getText();
        return selectedElementTitle; }
}
