package com.everc.automation.page.login;

import com.everc.automation.model.Product;
import com.everc.automation.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductModalPage extends AbstractPage {

    private By image = By.xpath("//mat-dialog-content/div/div[1]/div[1]/img");
    private By name =  By.xpath("//mat-dialog-content/div/div[1]/div[2]/h1");
    private By description =  By.xpath("//mat-dialog-content/div/div[1]/div[2]/div[1]");
    private By price =  By.xpath("//mat-dialog-content/div/div[1]/div[2]/div[2]/p");
    private By reviews =  By.xpath("//mat-panel-title[starts-with(@class,'mat-expansion-panel-header')]");
    private By closeButton =  By.xpath("//mat-dialog-actions");

    public ProductModalPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver,webDriverWait);
    }

    public WebElement getImage() {
        return driver.findElement(image);
    }

    public WebElement getName() {
        return driver.findElement(name);
    }

    public WebElement getDescription() {
        return driver.findElement(description);
    }

    public WebElement getPrice() {
        return driver.findElement(price);
    }

    public WebElement getReviews() {
        return driver.findElement(reviews);
    }

    public WebElement getCloseButton() {
        return driver.findElement(closeButton);
    }

    public Product getProduct(){
        Product product = new Product();
        product.setImage(getImage().getAttribute("src"));
        product.setName(getName().getText());
        product.setDescription(getDescription().getText());
        product.setPrice(getPrice().getText());
        product.setReviews(getReviews().getText());
        return product;
    }

}