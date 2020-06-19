package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import pages.locators.HomePageLocators;
import utils.SeleniumDriver;

public class HomePageActions {
	
	HomePageLocators HomePageLocators =null;
	public HomePageActions()
	{
		
		this.HomePageLocators =new HomePageLocators();
		PageFactory.initElements(SeleniumDriver.getDriver(), HomePageLocators);
	}
	
	public void moveToWeatherMenu()
	{
		Actions action= new Actions(SeleniumDriver.getDriver());
		action.click(HomePageLocators.clickMoreMenu).perform();
		action.click(HomePageLocators.weatherSection).perform();
	}

	public void clickOnSearchCarsMenu()
	{
		//moveToWeatherMenu();
		HomePageLocators.searchCarsLink.click();
		
	}


	public String getTempOfCity(){
		return HomePageLocators.getTempofCity.getText();
	}
	public void clickOnCity(String city) {
		SeleniumDriver.getDriver().findElement(By.xpath("//*[@id='map_canvas']//*[text()[contains(.,'"+city+"')]]")).click();
	}




}
