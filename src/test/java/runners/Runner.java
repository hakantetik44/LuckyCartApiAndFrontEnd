package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (


       strict = true,


        plugin={"html:target/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"},

        features="src/test/resources/features",
        glue="stepdefinitions" ,
        tags=  "@200",


        dryRun= false



)



public class Runner {

}