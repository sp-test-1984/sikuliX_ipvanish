package sp;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import sp.util.AppHelper;
import sp.util.Configuration;
import sp.util.Images;

import java.util.Iterator;

public class StepDefinitions {

    private static final Configuration CONFIGURATION = Configuration.getCONFIGURATION();


    @Given("^IPVanish is accessible$")
    public void ipvanish_is_accessible()  {
        Assert.assertTrue("application is not accessible", AppHelper.launchApp());
    }

    @And("^I am logged$")
    public void i_am_logged() throws Throwable {
        CONFIGURATION.getScreen().type(Images.USERNAME, CONFIGURATION.getUserName());
        CONFIGURATION.getScreen().type(Images.USERNAME, CONFIGURATION.getPassword());
        CONFIGURATION.getScreen().click(Images.LOGINBUTTON);
    }

    @When("^I select \"([^\"]*)\"$")
    public void i_select(String protocol) throws Throwable {
        // open preferences
        CONFIGURATION.getScreen().type(",", Key.META);
        takeANap();
        CONFIGURATION.getScreen().click(Images.PROTOCOL_SELECTOR);
        takeANap();

        switch (protocol.toLowerCase()){
            case "ikev2":
                CONFIGURATION.getScreen().click(Images.IKEV2);
                break;
            case "ipsec":
                CONFIGURATION.getScreen().click(Images.IPSEC);
                break;
            case "l2tp":
                CONFIGURATION.getScreen().click(Images.L2TP);
                break;
            case "tcp":
                CONFIGURATION.getScreen().click(Images.OPEN_TCP);
                break;
            case "udp":

                break;

        }
    }

    @When("^attempt connection$")
    public void attempt_connection() throws Throwable {
        CONFIGURATION.getScreen().click(Images.QUICKCONNECT);
        CONFIGURATION.getScreen().click(Images.CONNECT);
        takeANap();
        CONFIGURATION.getScreen().click(Images.ALLOW);
        CONFIGURATION.getScreen().type(Images.KEYPASSWORD, "vico2018");
        CONFIGURATION.getScreen().click(Images.ALLOW);
        CONFIGURATION.getScreen().type(Images.NEAGENT, "vico2018");
        takeANap();
        CONFIGURATION.getScreen().click(Images.ALLOW);
    }

    @Then("^I should successfully connect$")
    public void i_should_successfully_connect() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    private void takeANap() {
        try{
            Thread.sleep(4000);
        }  catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        try {
            CONFIGURATION.getScreen().click(Images.ACCOUNT);
            CONFIGURATION.getScreen().click(Images.LOGOUTBUTTON);
            CONFIGURATION.getScreen().click(Images.CONFIRM);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    void allMatches(String image){
        try {
            Iterator<Match> all = CONFIGURATION.getScreen().findAll(image);
            Match el = null;
            while (all.hasNext()){
                el = all.next();
                System.out.println(">>>>>>>" + el.getScore());
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }
}
