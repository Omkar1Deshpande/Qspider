package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pom_addProduct {
	WebDriver driver;

	public pom_addProduct(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="productName")
	private WebElement text_PName;
	
	@FindBy(name="quantity")
	private WebElement text_quantity;
	
	@FindBy(name="price")
	private WebElement text_price;
	
	@FindBy(name="productCategory")
	private WebElement dropdown_SelectCategory;
	
	@FindBy(name="vendorId")
	private WebElement dropdown_SelectVendor;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	private WebElement button_Add;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getText_PName() {
		return text_PName;
	}

	public WebElement getText_quantity() {
		return text_quantity;
	}

	public WebElement getText_price() {
		return text_price;
	}

	public WebElement getDropdown_SelectCategory() {
		return dropdown_SelectCategory;
	}

	public WebElement getDropdown_SelectVendor() {
		return dropdown_SelectVendor;
	}

	public WebElement getButton_Add() {
		return button_Add;
	}
	
}
