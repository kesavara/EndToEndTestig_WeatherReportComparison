package steps;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ResultsStorage.ResultsStorage;
import pages.actions.HomePageActions;
import utils.SeleniumDriver;

import static io.restassured.RestAssured.given;


public class WeatherSteps extends ResultsStorage {


	public Response resp;
	public static double ApiTemperature;
	public static  double TemperatureFromNDTVWeather;
	public Boolean ComaprisonOutput;

	public static double getphase1Temp() {
		return ApiTemperature ;
	}

	public static double getphase2Temp() {
		return TemperatureFromNDTVWeather ;
	}


	HomePageActions homePageActions = new HomePageActions();

	@Given("^I am on the Home Page \"([^\"]*)\" of ndtv Website$")
	public void i_am_on_the_Home_Page_of_ndtv_Website(String webSiteURL)  {
	    SeleniumDriver.openPage(webSiteURL);
	    
	}



	@Given("^I hit the get endpoint of weather report$")
	public void I_hit_the_get_endpoint_of_weather_report(List<String> city) {
		resp = given().
				queryParam("q", city.get(0)).
				queryParam("appid", city.get(1)).
				when().
				get("https://samples.openweathermap.org/data/2.5/weather")
				.andReturn();

	}

	@Then("^get the temperature from result and store$")
	public void get_the_temperature_from_result_and_store() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("resp is ---" + resp.getBody().asString());
		JsonPath jsonPathEvaluator = resp.jsonPath();
		String[] getTempearureFromResponse = jsonPathEvaluator.get("main").toString().split(",");
		String[] getApiTemperature = getTempearureFromResponse[0].replace("{","").toString().split("=");
		double getTempinInt = Double.parseDouble(getApiTemperature[1].trim());
		ApiTemperature = getTempinInt;
		System.out.println("api temp -----"+getTempinInt);
	}


	@When("^I move to Car For Sale Menu$")
	public void i_move_to_Car_For_Sale_Menu() throws InterruptedException {
		homePageActions.moveToWeatherMenu();

	}

	@When("^click on city$")
	public void click_on_city(List<String> city) throws InterruptedException {
		homePageActions.clickOnCity(city.get(0));

	}


	@When("^get the temperature of selected city$")
	public void get_the_temperature_of_selected_city() throws InterruptedException {
		String tempInDegree = homePageActions.getTempOfCity();
//		String covert_tempInDegree_to_Kelvin = tempInDegree + 273.15;
		String[] getTemperatureFromNDTVWeather =tempInDegree.split(":");
		TemperatureFromNDTVWeather = Integer.parseInt(getTemperatureFromNDTVWeather[1].trim())+273.15;
		System.out.println("TemperatureFromNDTVWeather ----"+TemperatureFromNDTVWeather);



		//*[@id="map_canvas"]/div[1]/div[6]/div/div[1]/div/div/span[4]/b
	}


	@Given("^I compare the result of two scenario outputs$")
	public Boolean I_compare_the_result_of_two_scenario_outputs()  {
		double phase1OutputFronNDTVWeather = getphase1Temp();
		double phase1OutputFromAPI = getphase2Temp();

		System.out.println("phase1 and phase2 result is "+ phase1OutputFromAPI +"-----"+phase1OutputFronNDTVWeather);
		if(phase1OutputFronNDTVWeather == phase1OutputFromAPI){
			return ComaprisonOutput = true;
		}
		else {
			return ComaprisonOutput = false;
		}

	}

	@Then("^Verify the results are same$")
	public void Verify_the_results_are_same()  {

		if(ComaprisonOutput){
			Assert.assertTrue(ComaprisonOutput);
			System.out.println("Weather report from both Phases are same");
		}
		else {
			Assert.assertFalse(ComaprisonOutput);
			System.out.println("Weather report from both Phases are different");
		}

	}


}
