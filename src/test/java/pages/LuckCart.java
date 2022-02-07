package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LuckCart {

    public  LuckCart(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (tagName = "h4")
    public WebElement playNow;
    @FindBy(xpath = "//span[text()='Spin the wheel!']")
    public WebElement gamePage;
    @FindBy(xpath = "//span[text()='Congrats, you won nothing!']")
    public WebElement CongratsyouwonNothing;







}
