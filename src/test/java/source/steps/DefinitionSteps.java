package source.steps;

import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import source.steps.serenity.TemplateSteps;

public class DefinitionSteps {

    @Steps
    TemplateSteps template;

    @Given("^the user is on the home page$")
	public void theUserIsOnTheHomePage() {
        template.is_the_home_page();
    }

    @When("^the user login in the page$")
	public void theUserLoginInThePage() throws Throwable {
        template.enters();
    }

  
	@Then("^the user setting template mapping$")
	public void theUserSettingTemplateMapping() throws Throwable {
		template.starts_search();
	}

	
	
	
}
