package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



public class HomePageLocators {


	@FindBy(how=How.LINK_TEXT,using="WEATHER")
	public WebElement weatherSection;

	@FindBy(how=How.ID,using="h_sub_menu")
	public WebElement clickMoreMenu;

	
	@FindBy(how=How.LINK_TEXT,using="Search Cars")
	public WebElement searchCarsLink;


	@FindBy(how=How.XPATH,using="//*[@id='map_canvas']/div[1]/div[6]/div/div[1]/div/div/span[4]/b")
	public WebElement getTempofCity;
}
