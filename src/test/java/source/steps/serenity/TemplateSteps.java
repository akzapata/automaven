package source.steps.serenity;

import source.pages.TemplatePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import source.pages.TemplatePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class TemplateSteps {

    TemplatePage templatePage;
    
    @Step
    public void is_the_home_page() {
        templatePage.open();
    }

    @Step
    public void enters() {
        templatePage.enter_login();
    }

    @Step
    public void starts_search() throws Exception {
        templatePage.lookup_terms();
    }

  
}